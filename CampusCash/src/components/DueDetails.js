import React, { useEffect, useState } from 'react';
import './DueDetails.css';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

const DueDetails = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { studentId, totalDue } = location.state || {};
  const [dues, setDues] = useState([]);

  useEffect(() => {
    const fetchDues = async () => {
      try {
        const res = await axios.get(`http://localhost:5000/api/auth/dues/${studentId}`);
        setDues(res.data);
      } catch (error) {
        console.error('Error fetching dues:', error);
      }
    };

    if (studentId) {
      fetchDues();
    }
  }, [studentId]);

  return (
    <div className="details-containerd">
      <header className="headerd">
        <img src="logo.png" alt="Campus Cash Logo" className="campus-cash-logod" />
        <img src="Mits.png" alt="MITS Logo" className="partner-logod" />
        <h2 className="detailsTitled">DUE DETAILS</h2>
      </header>

      <div className="details-boxd">
        <div className="title-containerd">
          <p className="subtitled">Student ID: {studentId}</p>
        </div>

        <div className="dues-list">
          {dues.map((due, index) => (
            <div key={index} className="due-item">
              <p className='item-text'>Description: {due.description}</p>
              <p className='item-text'>Amount: ₹{due.dueAmount}</p>
              <p className='item-text'>Status: {due.status}</p>
            </div>
          ))}
        </div>

        <div className="total-amount">
          <h3>Total Due: ₹{totalDue}</h3>
        </div>

        <div className="buttonContainerd">
          <button className="buttond" onClick={() => navigate('/user')}>Return to Due Details</button>
        </div>
      </div>
    </div>
  );
};

export default DueDetails;
