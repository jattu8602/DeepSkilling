import React from 'react';

const players = [
  { name: 'Virat', score: 120 },
  { name: 'Rohit', score: 85 },
  { name: 'Dhoni', score: 65 },
  { name: 'Kohli', score: 45 },
  { name: 'Jadeja', score: 30 },
];

function ListofPlayers() {
  const lowScorers = players.filter(p => p.score < 70);

  return (
    <div>
      <h3>All Players</h3>
      <ul>{players.map((p, i) => <li key={i}>{p.name} - {p.score}</li>)}</ul>
      <h4>Players with score below 70 (arrow + filter):</h4>
      <ul>{lowScorers.map((p, i) => <li key={i}>{p.name} - {p.score}</li>)}</ul>
    </div>
  );
}

export default ListofPlayers;
