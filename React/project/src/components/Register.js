import React, { Component } from 'react';

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '', email: '', phone: '', password: '', city: '',
      errors: {}, submitted: false
    };
  }

  handleChange = (e) => this.setState({ [e.target.name]: e.target.value }, () => this.validateField(e.target.name));

  validateField(name) {
    const value = this.state[name];
    const errors = { ...this.state.errors };
    switch (name) {
      case 'name': errors.name = value.length < 3 ? 'Name must be at least 3 characters' : ''; break;
      case 'email': errors.email = !/\S+@\S+\.\S+/.test(value) ? 'Invalid email' : ''; break;
      case 'phone': errors.phone = !/^\d{10}$/.test(value) ? 'Phone must be 10 digits' : ''; break;
      case 'password': errors.password = value.length < 6 ? 'Password must be at least 6 characters' : ''; break;
      case 'city': errors.city = !value ? 'City is required' : ''; break;
    }
    this.setState({ errors });
  }

  validate() {
    const errors = {};
    if (this.state.name.length < 3) errors.name = 'Name must be at least 3 characters';
    if (!/\S+@\S+\.\S+/.test(this.state.email)) errors.email = 'Invalid email';
    if (!/^\d{10}$/.test(this.state.phone)) errors.phone = 'Phone must be 10 digits';
    if (this.state.password.length < 6) errors.password = 'Password must be at least 6 characters';
    if (!this.state.city) errors.city = 'City is required';
    this.setState({ errors });
    return Object.keys(errors).length === 0;
  }

  handleSubmit = (e) => {
    e.preventDefault();
    if (this.validate()) this.setState({ submitted: true });
  };

  render() {
    const { name, email, phone, password, city, errors, submitted } = this.state;

    if (submitted) return <div className="box" style={{ backgroundColor: 'lightgreen' }}><h3>Registration Successful!</h3></div>;

    return (
      <div>
        <h3>Mail Register</h3>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Name: </label>
            <input name="name" value={name} onChange={this.handleChange} />
            {errors.name && <span style={{ color: 'red' }}>{errors.name}</span>}
          </div>
          <div>
            <label>Email: </label>
            <input name="email" value={email} onChange={this.handleChange} />
            {errors.email && <span style={{ color: 'red' }}>{errors.email}</span>}
          </div>
          <div>
            <label>Phone: </label>
            <input name="phone" value={phone} onChange={this.handleChange} />
            {errors.phone && <span style={{ color: 'red' }}>{errors.phone}</span>}
          </div>
          <div>
            <label>Password: </label>
            <input type="password" name="password" value={password} onChange={this.handleChange} />
            {errors.password && <span style={{ color: 'red' }}>{errors.password}</span>}
          </div>
          <div>
            <label>City: </label>
            <select name="city" value={city} onChange={this.handleChange}>
              <option value="">Select</option>
              <option value="Mumbai">Mumbai</option>
              <option value="Delhi">Delhi</option>
              <option value="Bangalore">Bangalore</option>
            </select>
            {errors.city && <span style={{ color: 'red' }}>{errors.city}</span>}
          </div>
          <button type="submit">Register</button>
        </form>
      </div>
    );
  }
}

export default Register;
