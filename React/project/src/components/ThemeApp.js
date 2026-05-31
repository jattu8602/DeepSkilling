import React, { createContext, useState, useContext } from 'react';

const ThemeContext = createContext();

function ThemeProvider({ children }) {
  const [theme, setTheme] = useState('light');
  const toggleTheme = () => setTheme(t => (t === 'light' ? 'dark' : 'light'));
  return <ThemeContext.Provider value={{ theme, toggleTheme }}>{children}</ThemeContext.Provider>;
}

function ThemeSwitcher() {
  const { theme, toggleTheme } = useContext(ThemeContext);
  return (
    <div className="box" style={{ backgroundColor: theme === 'dark' ? '#333' : '#fff', color: theme === 'dark' ? '#fff' : '#000' }}>
      <h4>Current Theme: {theme}</h4>
      <button onClick={toggleTheme}>Toggle Theme</button>
    </div>
  );
}

const employees = [
  { id: 1, name: 'Alice', role: 'Developer' },
  { id: 2, name: 'Bob', role: 'Manager' },
  { id: 3, name: 'Charlie', role: 'Tester' },
];

function EmployeeCard() {
  const { theme } = useContext(ThemeContext);
  return (
    <div className="box" style={{ backgroundColor: theme === 'dark' ? '#444' : '#eee' }}>
      <h4>Employees List</h4>
      <ul>{employees.map(e => <li key={e.id}>{e.name} - {e.role}</li>)}</ul>
    </div>
  );
}

function ThemeApp() {
  return (
    <ThemeProvider>
      <h3>Context API - Theme + Employees</h3>
      <ThemeSwitcher />
      <EmployeeCard />
    </ThemeProvider>
  );
}

export default ThemeApp;
