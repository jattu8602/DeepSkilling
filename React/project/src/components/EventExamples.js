import React, { Component } from 'react';

class EventExamples extends Component {
  constructor(props) {
    super(props);
    this.state = { counter: 0, message: '' };
  }

  increment = () => this.setState({ counter: this.state.counter + 1 });
  decrement = () => this.setState({ counter: this.state.counter - 1 });
  handleChange = (e) => this.setState({ message: e.target.value });
  handleSubmit = (e) => {
    e.preventDefault();
    alert('Submitted: ' + this.state.message);
  };

  render() {
    return (
      <div className="box">
        <h3>Event Examples</h3>
        <p>Counter: {this.state.counter}</p>
        <button onClick={this.increment}>+</button>
        <button onClick={this.decrement}>-</button>
        <form onSubmit={this.handleSubmit}>
          <input type="text" value={this.state.message} onChange={this.handleChange} placeholder="Type here..." />
          <button type="submit">Submit</button>
        </form>
        <p>Typing: {this.state.message}</p>
      </div>
    );
  }
}

export default EventExamples;
