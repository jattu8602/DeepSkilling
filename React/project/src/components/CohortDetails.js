import React from 'react';
import { cohortData } from '../data/CohortData';
import './CohortDetails.module.css';

function CohortDetails({ cohort }) {
  const c = cohort || cohortData[0];
  const statusColor = c.status === 'ongoing' ? 'green' : 'blue';

  return (
    <div className="box">
      <h3 style={{ color: statusColor }}>{c.cohortCode}</h3>
      <dl>
        <dt>Cohort Code</dt>
        <dd>{c.cohortCode}</dd>
        <dt>Status</dt>
        <dd>{c.status}</dd>
        <dt>Start Date</dt>
        <dd>{c.startDate}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
