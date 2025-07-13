import React, { useState } from 'react';
import axios from '../api/axios';
import './RegisterAdmin.css';

function RegisterAdmin() {
  const [admin, setAdmin] = useState({
    username: '',
    password: '',
    name: ''
  });

  const handleChange = (e) => {
    setAdmin({ ...admin, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      await axios.post('/auth/register-admin', admin);
      alert('Admin registered successfully!');
    } catch (err) {
      alert('Registration failed!');
    }
  };

  return (
    <div className="register-bg">
      <div className="container d-flex justify-content-center align-items-center vh-100">
        <div className="card p-4 register-card">
          <h2 className="text-center mb-4">Admin Registration</h2>
          {Object.entries(admin).map(([key, value]) => (
            <input
              key={key}
              className="form-control my-2"
              name={key}
              placeholder={key.charAt(0).toUpperCase() + key.slice(1)}
              value={value}
              onChange={handleChange}
              type={key === 'password' ? 'password' : 'text'}
            />
          ))}
          <button className="btn btn-warning w-100 mt-3" onClick={handleSubmit}>
            Register
          </button>
        </div>
      </div>
    </div>
  );
}

export default RegisterAdmin;
