const express = require('express');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const User = require('../models/User');
const Dues = require('../models/Dues'); // Add this line
const router = express.Router();
const JWT_SECRET = 'your_jwt_secret'; // Replace with a strong secret

// Register Route
router.post('/register', async (req, res) => {
  const { studentId, name, email, password } = req.body;
  try {
    let user = await User.findOne({ email });
    if (user) return res.status(400).json({ message: 'User already exists' });

    const hashedPassword = await bcrypt.hash(password, 10);
    user = new User({
      studentId,
      name,
      email,
      password: hashedPassword,
    });
    await user.save();
    res.status(201).json({ message: 'User registered successfully' });
  } catch (err) {
    res.status(500).json({ message: 'Server error' })
  }
});

// Login Route
router.post('/login', async (req, res) => {
  const { studentId, password } = req.body;

  try {
    const user = await User.findOne({ studentId });
    if (!user) {
      return res.status(400).json({ message: 'Invalid ID' });
    }

    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) {
      return res.status(400).json({ message: 'Invalid Password' });
    }

    const token = jwt.sign({ userId: user._id }, JWT_SECRET, { expiresIn: '1h' });
    res.json({ token, user });
  } catch (err) {
    console.error("Error during login process:", err);
    res.status(500).json({ message: 'Server error' });
  }
});

// Route to get user data along with total dues after authentication
router.get('/user', async (req, res) => {
  const token = req.headers['x-auth-token'];
  if (!token) return res.status(401).json({ message: 'No token, authorization denied' });

  try {
    const decoded = jwt.verify(token, JWT_SECRET);
    const user = await User.findById(decoded.userId).select('-password');

    // Fetch all dues for the studentId and calculate total dues
    const dues = await Dues.aggregate([
      { $match: { studentId: user.studentId } },
      { $group: { _id: "$studentId", totalDue: { $sum: "$dueAmount" } } }
    ]);

    const totalDue = dues.length > 0 ? dues[0].totalDue : 0; // Default to 0 if no dues found
    res.json({ user, totalDue });
  } catch (err) {
    res.status(401).json({ message: 'Token is not valid' });
  }
});

router.get('/user', async (req, res) => {
  const token = req.headers['x-auth-token'];
  if (!token) return res.status(401).json({ message: 'No token, authorization denied' });

  try {
    const decoded = jwt.verify(token, JWT_SECRET);
    const user = await User.findById(decoded.userId).select('-password');

    const dues = await Dues.aggregate([
      { $match: { studentId: user.studentId } },
      { $group: { _id: "$studentId", totalDue: { $sum: "$dueAmount" } } }
    ]);

    const totalDue = dues.length > 0 ? dues[0].totalDue : 0;
    res.json({ user, totalDue });
  } catch (err) {
    res.status(401).json({ message: 'Token is not valid' });
  }
});

// Route to get all dues for a specific studentId
router.get('/dues/:studentId', async (req, res) => {
  try {
    const dues = await Dues.find({ studentId: req.params.studentId });
    res.json(dues);
  } catch (error) {
    console.error('Error fetching dues:', error);
    res.status(500).json({ message: 'Server error' });
  }
});

module.exports = router;
