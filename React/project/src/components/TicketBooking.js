import React, { Component } from 'react';

const tickets = [
  { id: 1, route: 'Mumbai-Delhi', price: 5000 },
  { id: 2, route: 'Delhi-Bangalore', price: 7000 },
  { id: 3, route: 'Mumbai-Chennai', price: 4500 },
  { id: 4, route: 'Bangalore-Kolkata', price: 8000 },
];

class TicketBooking extends Component {
  constructor(props) {
    super(props);
    this.state = { selected: null };
  }

  bookTicket = (ticket) => this.setState({ selected: ticket });

  render() {
    return (
      <div>
        <h3>Ticket Booking</h3>
        {tickets.map(t => (
          <div key={t.id} className="box">
            <p>{t.route} - ${t.price}</p>
            <button onClick={() => this.bookTicket(t)}>
              {this.state.selected?.id === t.id ? 'Booked!' : 'Book'}
            </button>
          </div>
        ))}
        {this.state.selected && (
          <div className="box" style={{ backgroundColor: 'lightgreen' }}>
            <h4>Booking Confirmed</h4>
            <p>Route: {this.state.selected.route}</p>
            <p>Price: ${this.state.selected.price}</p>
          </div>
        )}
      </div>
    );
  }
}

export default TicketBooking;
