import React, { Component } from 'react';

class CurrencyConverter extends Component {
  constructor(props) {
    super(props);
    this.state = { dollars: 0, rupees: 0 };
  }

  handleDollarChange = (e) => {
    const dollars = parseFloat(e.target.value) || 0;
    this.setState({ dollars, rupees: dollars * 74.5 });
  };

  render() {
    return (
      <div className="box">
        <h3>Currency Converter</h3>
        <label>Dollars: </label>
        <input type="number" value={this.state.dollars} onChange={this.handleDollarChange} />
        <p>Rupees: {this.state.rupees.toFixed(2)}</p>
      </div>
    );
  }
}

export default CurrencyConverter;
