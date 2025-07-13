// src/api/axios.js
import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// 🔐 Automatically add JWT token from localStorage to headers
instance.interceptors.request.use((config) => {
  const token = localStorage.getItem('token'); // JWT stored here
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default instance;

