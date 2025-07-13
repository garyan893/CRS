import React, { useState } from 'react';
import axios from '../api/axios';
import { useNavigate } from 'react-router-dom';
import './Login.css'; // Custom CSS file we'll create

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const res = await axios.post('/auth/login', {
        username,
        password
      });

      localStorage.setItem('token', res.data.token);
      localStorage.setItem('role', res.data.role);

      if (res.data.role === 'ADMIN') {
        navigate('/admin/dashboard');
      } else if (res.data.role === 'STUDENT') {
        navigate('/student/dashboard');
      } else {
        setError('Unknown role.');
      }
    } catch (err) {
      setError('Invalid credentials');
    }
  };

  return (
    <div className="login-background">
      <div className="container d-flex justify-content-center align-items-center vh-100">
        <div className="card p-4 login-card">
          <h2 className="text-center mb-4">Campus Recruitment Login</h2>
          {error && <div className="alert alert-danger">{error}</div>}
          <input
            className="form-control my-2"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            className="form-control my-2"
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button className="btn btn-primary w-100 mt-2" onClick={handleLogin}>
            Login
          </button>

          <div className="mt-4 text-center">
            <p>Don't have an account?</p>
            <div className="d-flex justify-content-center">
              <a href="/register-student" className="btn btn-outline-success mx-2">Student Register</a>
              <a href="/register-admin" className="btn btn-outline-warning mx-2">Admin Register</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;


