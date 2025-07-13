import React, { useEffect, useState } from 'react';
import axios from '../api/axios';
import Navbar from '../components/Navbar';
import './StudentDashboard.css';

function StudentDashboard() {
  const [jobs, setJobs] = useState([]);

  const fetchJobs = async () => {
    const res = await axios.get('/student/jobs');
    setJobs(res.data);
  };

  const applyToJob = async (jobId) => {
    try {
      await axios.post(`/student/apply/${jobId}`);
      alert('Applied successfully!');
    } catch (err) {
      alert('Already applied or error occurred!');
    }
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  return (
    <>

      <div className="container dashboard-section">
        <h2 className="text-center mb-4">Student Dashboard</h2>
        <div className="row">
          {jobs.map((job) => (
            <div className="col-md-6" key={job.id}>
              <div className="card p-4 mb-3 shadow-sm">
                <h5>{job.companyName}</h5>
                <p><strong>Eligibility:</strong> {job.eligibility}</p>
                <p><strong>Deadline:</strong> {job.deadline}</p>
                <button className="btn btn-primary" onClick={() => applyToJob(job.id)}>
                  Apply
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}

export default StudentDashboard;
