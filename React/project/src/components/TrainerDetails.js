import React from 'react';
import { useParams } from 'react-router-dom';
import { trainers } from '../data/TrainersMock';

function TrainerDetails() {
  const { id } = useParams();
  const trainer = trainers.find(t => t.TrainerId === parseInt(id));

  if (!trainer) return <p>Trainer not found</p>;

  return (
    <div className="box">
      <h3>Trainer Details</h3>
      <p>ID: {trainer.TrainerId}</p>
      <p>Name: {trainer.Name}</p>
      <p>Email: {trainer.Email}</p>
      <p>Phone: {trainer.Phone}</p>
      <p>Technology: {trainer.Technology}</p>
      <p>Skills: {trainer.Skills}</p>
    </div>
  );
}

export default TrainerDetails;
