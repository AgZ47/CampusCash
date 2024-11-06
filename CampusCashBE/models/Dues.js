const mongoose = require('mongoose');

const duesSchema = new mongoose.Schema({
  studentId: { type: String, required: true },
  dueAmount: { type: Number, required: true },
  description: { type: String, required: true },
  status: { type: String, required: true, enum: ['pending', 'paid'] }, // Status of the due
});

module.exports = mongoose.model('Due', duesSchema);
