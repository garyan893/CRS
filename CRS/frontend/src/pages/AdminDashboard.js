import React, { useState, useEffect } from 'react';
import axios from '../api/axios';
import Navbar from '../components/Navbar';
import './AdminDashboard.css';

function AdminDashboard() {
  const [jobs, setJobs] = useState([]);
  const [job, setJob] = useState({
    companyName: '',
    eligibility: '',
    deadline: ''
  });

  const fetchJobs = async () => {
     const token = localStorage.getItem('token'); // Get token
     try {
       const res = await axios.get('/admin/jobs', {
         headers: {
           Authorization: `Bearer ${token}`
         }
       });
       setJobs(res.data);
     } catch (err) {
       console.error("❌ Error fetching jobs:", err);
     }
   };
  const handleChange = (e) => {
    setJob({ ...job, [e.target.name]: e.target.value });
  };

  const handlePost = async () => {
    const token = localStorage.getItem('token'); // Get token
    try {
      await axios.post('/admin/jobs', job, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setJob({ companyName: '', eligibility: '', deadline: '' });
      fetchJobs();
    } catch (err) {
      console.error("❌ Error posting job:", err);
    }
  };


  return (
    <>
      <Navbar />
      <div className="container dashboard-section">
        <h2 className="text-center mb-4">Admin Dashboard</h2>
        <div className="row">
          <div className="col-md-6">
            <div className="card p-4 mb-4 shadow-sm">
              <h4>Post New Job</h4>
              <input className="form-control my-2" name="companyName" placeholder="Company Name" value={job.companyName} onChange={handleChange} />
              <input className="form-control my-2" name="eligibility" placeholder="Eligibility Criteria" value={job.eligibility} onChange={handleChange} />
              <input className="form-control my-2" type="date" name="deadline" value={job.deadline} onChange={handleChange} />
              <button className="btn btn-primary w-100 mt-2" onClick={handlePost}>Post Job</button>
            </div>
          </div>

          <div className="col-md-6">
            <div className="card p-4 mb-4 shadow-sm">
              <h4>Posted Jobs</h4>
              {jobs.map((j, idx) => (
                <div key={idx} className="border-bottom py-2">
                  <strong>{j.companyName}</strong><br />
                  Eligibility: {j.eligibility}<br />
                  Deadline: {j.deadline}
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AdminDashboard;

