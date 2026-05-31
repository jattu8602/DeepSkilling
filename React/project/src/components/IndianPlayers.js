import React from 'react';

const T20players = ['Virat', 'Rohit', 'Dhoni'];
const RanjiTrophy = ['Jadeja', 'Bumrah', 'Ashwin'];
const merged = [...T20players, ...RanjiTrophy];
const [odd, ...rest] = merged;
const even = rest.filter((_, i) => i % 2 === 0);

function IndianPlayers() {
  return (
    <div>
      <h3>Indian Players (Destructuring + Spread)</h3>
      <h4>Merged Teams:</h4>
      <ul>{merged.map((p, i) => <li key={i}>{p}</li>)}</ul>
    </div>
  );
}

export default IndianPlayers;
