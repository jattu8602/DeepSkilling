import React, { Component } from 'react';

class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = { entrycount: 0, exitcount: 0 };
  }

  updateEntry = () => this.setState({ entrycount: this.state.entrycount + 1 });
  updateExit = () => this.setState({ exitcount: this.state.exitcount + 1 });

  render() {
    return (
      <div className="box">
        <h3>People Counter</h3>
        <p>Entry Count: {this.state.entrycount}</p>
        <p>Exit Count: {this.state.exitcount}</p>
        <button onClick={this.updateEntry}>Login</button>
        <button onClick={this.updateExit}>Exit</button>
      </div>
    );
  }
}

export default CountPeople;
