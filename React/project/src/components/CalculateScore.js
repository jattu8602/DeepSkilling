import React from 'react';

function CalculateScore({ name, school, total, goal }) {
  const average = total / 5;

  return (
    <div className="box">
      <h3>Calculate Score</h3>
      <p>Name: {name}</p>
      <p>School: {school}</p>
      <p>Total: {total}</p>
      <p>Goal: {goal}</p>
      <p>Average: {average.toFixed(2)}</p>
    </div>
  );
}

export default CalculateScore;
