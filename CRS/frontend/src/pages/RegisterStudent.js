import React, { useState } from 'react';
import axios from '../api/axios';
import './RegisterStudent.css';

function RegisterStudent() {
  const [student, setStudent] = useState({
    username: '',
    password: '',
    name: '',
    dob: '',
    cgpa: '',
    tenthPercent: '',
    twelfthPercent: '',
    diplomaPercent: '',
    internshipDetails: '',
  });

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      await axios.post('/auth/register-student', student);
      alert('Student registered successfully!');
    } catch (err) {
      alert('Registration failed!');
    }
  };

  return (
    <div className="register-bg">
      <div className="container d-flex justify-content-center align-items-center vh-100">
        <div className="card p-4 register-card">
          <h2 className="text-center mb-4">Student Registration</h2>
          {Object.entries(student).map(([key, value]) => (
            <input
              key={key}
              className="form-control my-2"
              name={key}
              placeholder={key.charAt(0).toUpperCase() + key.slice(1)}
              value={value}
              onChange={handleChange}
              type={key === 'password' ? 'password' : key === 'dob' ? 'date' : 'text'}
            />
          ))}
          <button className="btn btn-success w-100 mt-3" onClick={handleSubmit}>
            Register
          </button>
        </div>
      </div>
    </div>
  );
}

export default RegisterStudent;
