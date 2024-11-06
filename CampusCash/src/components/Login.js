import React from 'react';
import { Link } from 'react-router-dom';
import './Login.css';

function Login() {
  return (
    <div className="login-container">
      <header className="header">
      <img src="logo.png" alt="Campus Cash Logo" className="campus-cash-logo" />
      <img src="Mits.png" alt="MITS Logo" className="partner-logo" />
        
      </header>
      
      <div className="login-box">
        <div className="left-panel">
          <h2>Welcome Back!</h2>
          <br>
          </br>
          <br>
          </br>
          <p>Use Your Personal Information To Login</p>
          <br></br>
          <br></br>
          <p className="no-account-text">Don’t Have An Account?</p>
          <br></br>
          <br></br>
          <Link to="/register" className="register-button">Register</Link>
        </div>

        <div className="right-panel">
          <div className="input-container">
            <h3 className="names">Student ID</h3>
            <div className="input-group">
              <img src="id.jpg" alt="Student Icon" />
              <input type="text" placeholder="Enter Student ID" />
            </div>
            <h3 className="names">Password</h3>
            <div className="input-group">
              <img src="pass.png" alt="Password Icon" />
              <input type="password" placeholder="Enter password" />
            </div>
          </div>
          <button className="login-button">LOGIN</button>
        </div>
      </div>
    </div>
  );
}

export default Login;
