import React from 'react';
import { Link } from 'react-router-dom';
import './Register.css';

function Register() {
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
              <input type="text" placeholder="Enter Student ID" />
            </div>
            <div className="input-group">
              <img src="user.png" alt="User Icon" />
              <input type="text" placeholder="Enter Student Name" />
            </div>
            <div className="input-group">
              <img src="mail.png" alt="Email Icon" />
              <input type="email" placeholder="Enter Email" />
            </div>
            <div className="input-group">
              <img src="pass.png" alt="Password Icon" />
              <input type="password" placeholder="Enter Password" />
            </div>
            <div className="input-group">
              <img src="pass.png" alt="Confirm Password Icon" />
              <input type="password" placeholder="Confirm Password" />
            </div>
          </div>
          <button className="reg-button">REGISTER</button>
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
