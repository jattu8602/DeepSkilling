import React from 'react';

const offices = [
  { Name: 'CoWork A', Rent: 55000, Address: 'Street 1' },
  { Name: 'CoWork B', Rent: 75000, Address: 'Street 2' },
  { Name: 'CoWork C', Rent: 45000, Address: 'Street 3' }
];

function OfficeSpaceRental() {
  return (
    <div>
      <h2>Office Space Rental</h2>
      {offices.map((o, i) => (
        <div key={i} className="box">
          <h3>{o.Name}</h3>
          <p>Address: {o.Address}</p>
          <p style={{ color: o.Rent < 60000 ? 'red' : 'green' }}>
            Rent: ${o.Rent}
          </p>
        </div>
      ))}
    </div>
  );
}

export default OfficeSpaceRental;
