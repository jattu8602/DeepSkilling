import React, { Component } from 'react';
import axios from 'axios';

class GetUser extends Component {
  constructor(props) {
    super(props);
    this.state = { user: null, loading: false, error: null };
  }

  fetchUser = () => {
    this.setState({ loading: true, error: null });
    axios.get('https://randomuser.me/api/')
      .then(res => this.setState({ user: res.data.results[0], loading: false }))
      .catch(err => this.setState({ error: err.message, loading: false }));
  };

  render() {
    const { user, loading, error } = this.state;

    return (
      <div>
        <h3>Random User (REST API)</h3>
        <button onClick={this.fetchUser}>Fetch User</button>
        {loading && <p>Loading...</p>}
        {error && <p style={{ color: 'red' }}>Error: {error}</p>}
        {user && (
          <div className="box">
            <img src={user.picture.large} alt="user" />
            <p>Name: {user.name.first} {user.name.last}</p>
            <p>Email: {user.email}</p>
            <p>Location: {user.location.city}, {user.location.country}</p>
          </div>
        )}
      </div>
    );
  }
}

export default GetUser;
