import React, { Component } from 'react';

class ComplaintRegister extends Component {
  constructor(props) {
    super(props);
    this.state = {
      title: '',
      description: '',
      date: '',
      complaints: []
    };
  }

  handleChange = (e) => this.setState({ [e.target.name]: e.target.value });

  handleSubmit = (e) => {
    e.preventDefault();
    this.setState({
      complaints: [...this.state.complaints, { title: this.state.title, description: this.state.description, date: this.state.date }],
      title: '', description: '', date: ''
    });
  };

  render() {
    return (
      <div>
        <h3>Complaint Registration</h3>
        <form onSubmit={this.handleSubmit}>
          <input name="title" value={this.state.title} onChange={this.handleChange} placeholder="Title" required />
          <br />
          <textarea name="description" value={this.state.description} onChange={this.handleChange} placeholder="Description" required />
          <br />
          <input name="date" type="date" value={this.state.date} onChange={this.handleChange} required />
          <br />
          <button type="submit">Register</button>
        </form>
        <h4>All Complaints</h4>
        {this.state.complaints.map((c, i) => (
          <div key={i} className="box">
            <p><strong>{c.title}</strong> - {c.date}</p>
            <p>{c.description}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default ComplaintRegister;
