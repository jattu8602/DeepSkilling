import React from 'react';
import { Link } from 'react-router-dom';
import { trainers } from '../data/TrainersMock';

function TrainersList() {
  return (
    <div>
      <h3>Trainers List</h3>
      <ul>
        {trainers.map(t => (
          <li key={t.TrainerId}>
            <Link to={`/trainers/${t.TrainerId}`}>{t.Name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TrainersList;
