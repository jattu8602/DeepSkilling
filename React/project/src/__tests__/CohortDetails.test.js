import React from 'react';
import { render, screen } from '@testing-library/react';
import CohortDetails from '../components/CohortDetails';

test('renders cohort code from default data', () => {
  render(<CohortDetails />);
  const heading = screen.getByText('C001');
  expect(heading).toBeInTheDocument();
});

test('renders passed cohort data', () => {
  const cohort = { cohortCode: 'TEST01', status: 'completed', startDate: '2024-01-01' };
  render(<CohortDetails cohort={cohort} />);
  expect(screen.getByText('TEST01')).toBeInTheDocument();
  expect(screen.getByText('completed')).toBeInTheDocument();
});
