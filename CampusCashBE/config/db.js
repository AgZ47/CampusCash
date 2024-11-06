const mongoose = require('mongoose');

const connectDB = async () => {
  try {
    await mongoose.connect('mongodb+srv://22cs257:sonarf@cluster0.kyea5.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0', {
    });
    console.log('MongoDB connected');
  } catch (err) {
    console.error(err.message);
    process.exit(1);
  }
};

module.exports = connectDB;
