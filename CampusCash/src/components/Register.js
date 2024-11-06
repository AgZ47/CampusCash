import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './Register.css';

function Register() {
  const [formData, setFormData] = useState({
    studentId: '',
    name: '',
    email: '',
    password: '',
    confirmPassword: ''
  });

  const handleChange = (e) => setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleRegister = async () => {
    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    try {
      await axios.post('http://localhost:5000/api/auth/register', formData);
      alert('Registration successful');
    } catch (error) {
      alert('Error registering user');
    }
  };

  return (
    <div className="register-container">
      <header className="header">
        <img src="logo.png" alt="Campus Cash Logo" className="campus-cash-logo" />
        <img src="Mits.png" alt="MITS Logo" className="partner-logo" />
      </header>

      <div className="register-box">
        <div className="register-left">
          <div className="input-container">
            <div className="input-group">
              <img src="id.jpg" alt="Student Icon" />
              <input type="text" name="studentId" placeholder="Enter Student ID" onChange={handleChange} />
            </div>
            <div className="input-group">
              <img src="user.png" alt="User Icon" />
              <input type="text" name="name" placeholder="Enter Student Name" onChange={handleChange} />
            </div>
            <div className="input-group">
              <img src="mail.png" alt="Email Icon" />
              <input type="email" name="email" placeholder="Enter Email" onChange={handleChange} />
            </div>
            <div className="input-group">
              <img src="pass.png" alt="Password Icon" />
              <input type="password" name="password" placeholder="Enter Password" onChange={handleChange} />
            </div>
            <div className="input-group">
              <img src="pass.png" alt="Confirm Password Icon" />
              <input type="password" name="confirmPassword" placeholder="Confirm Password" onChange={handleChange} />
            </div>
          </div>
          <button onClick={handleRegister} className="register-buttonr">REGISTER</button>
        </div>

        <div className="register-right">
          <h2>Hello! Welcome</h2>
          <br></br>
          <br></br>
          <p>Enter Your Details To Start Up Your Dashboard</p>
          <br></br>
          <br></br>
          <p className="switch-prompt">Already Have An Account?</p>
          <br></br>
          <br></br>
          <Link to="/login" className="switch-button">Login</Link>
        </div>
      </div>
    </div>
  );
}

export default Register;
