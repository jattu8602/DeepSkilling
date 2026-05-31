import React from 'react';
import { render, screen } from '@testing-library/react';
import GitClient from '../components/GitClient';

test('renders GitClient component', () => {
  render(<GitClient />);
  expect(screen.getByText('GitClient Component')).toBeInTheDocument();
});
