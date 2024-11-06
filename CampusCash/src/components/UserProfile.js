import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './UserProfile.css';
import { useNavigate } from 'react-router-dom';

function UserProfile() {
  const [user, setUser] = useState(null);
  const [totalDue, setTotalDue] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUser = async () => {
      const token = localStorage.getItem('token');
      if (!token) return;
      
      try {
        const res = await axios.get('http://localhost:5000/api/auth/user', {
          headers: { 'x-auth-token': token }
        });
        setUser(res.data.user);
        setTotalDue(res.data.totalDue);
      } catch (error) {
        console.error('Error fetching user data', error);
      }
    };
    fetchUser();
  }, []);

  const handleViewDetails = () => {
    navigate('/due', { state: { studentId: user.studentId, totalDue } });
  };

  if (!user) return <div>Loading...</div>;

  return (
    <div className="due-container">
      <header className="header">
        <img src="logo.png" alt="Campus Cash Logo" className="campus-cash-logo" />
        <img src="Mits.png" alt="MITS Logo" className="partner-logo" />
        <h2 className="dueTitle">DUE DETAILS</h2>
      </header>

      <div className="due-box">
        <div className="title-container">
          <h3 className="subtitle">Welcome {user.name}!</h3>
        </div>

        <div className="due-details">
          <div className="card">
            <div className="cardLeft">
              <p className="label">Name :</p>
              <p className="label">Id :</p>
              <p className="label">Total Due :</p>
            </div>

            <div className="cardRight">
              <p className="value">{user.name}</p>
              <p className="value">{user.studentId}</p>
              <p className="value">₹{totalDue}</p> {/* Display the total due amount */}
            </div>
          </div>

          <div className="buttonContainer">
            <button className="button" onClick={handleViewDetails}>View Details</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserProfile;
