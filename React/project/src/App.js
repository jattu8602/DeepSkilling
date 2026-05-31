import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './components/Home';
import About from './components/About';
import Contact from './components/Contact';
import CalculateScore from './components/CalculateScore';
import Posts from './components/Posts';
import CohortDetails from './components/CohortDetails';
import TrainersList from './components/TrainersList';
import TrainerDetails from './components/TrainerDetails';
import OnlineShopping from './components/OnlineShopping';
import CountPeople from './components/CountPeople';
import ListofPlayers from './components/ListofPlayers';
import IndianPlayers from './components/IndianPlayers';
import OfficeSpaceRental from './components/OfficeSpaceRental';
import EventExamples from './components/EventExamples';
import CurrencyConverter from './components/CurrencyConverter';
import TicketBooking from './components/TicketBooking';
import ThemeApp from './components/ThemeApp';
import ComplaintRegister from './components/ComplaintRegister';
import Register from './components/Register';
import GetUser from './components/GetUser';
import './stylesheets/mystyle.css';

function App() {
  return (
    <BrowserRouter>
      <div className="app-container">
        <h1>React Learning Application</h1>
        <nav className="nav-links">
          <Link to="/">Home</Link>
          <Link to="/about">About</Link>
          <Link to="/contact">Contact</Link>
          <Link to="/score">Score</Link>
          <Link to="/posts">Posts</Link>
          <Link to="/cohort">Cohort</Link>
          <Link to="/trainers">Trainers</Link>
          <Link to="/shopping">Shopping</Link>
          <Link to="/counter">Counter</Link>
          <Link to="/cricket">Cricket</Link>
          <Link to="/office">Office</Link>
          <Link to="/events">Events</Link>
          <Link to="/currency">Currency</Link>
          <Link to="/tickets">Tickets</Link>
          <Link to="/theme">Theme</Link>
          <Link to="/complaint">Complaint</Link>
          <Link to="/register">Register</Link>
          <Link to="/user">User</Link>
        </nav>
        <hr />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/score" element={<CalculateScore name="John" school="ABC" total={450} goal={500} />} />
          <Route path="/posts" element={<Posts />} />
          <Route path="/cohort" element={<CohortDetails />} />
          <Route path="/trainers" element={<TrainersList />} />
          <Route path="/trainers/:id" element={<TrainerDetails />} />
          <Route path="/shopping" element={<OnlineShopping />} />
          <Route path="/counter" element={<CountPeople />} />
          <Route path="/cricket" element={<><ListofPlayers /><IndianPlayers /></>} />
          <Route path="/office" element={<OfficeSpaceRental />} />
          <Route path="/events" element={<EventExamples />} />
          <Route path="/currency" element={<CurrencyConverter />} />
          <Route path="/tickets" element={<TicketBooking />} />
          <Route path="/theme" element={<ThemeApp />} />
          <Route path="/complaint" element={<ComplaintRegister />} />
          <Route path="/register" element={<Register />} />
          <Route path="/user" element={<GetUser />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
