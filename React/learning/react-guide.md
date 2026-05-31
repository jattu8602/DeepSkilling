# React Guide

This project consolidates all 19 React HOL exercises into one single-page app with React Router navigation.

## HOL 1 - SPA & React Introduction
- **Concepts**: SPA vs MPA, React ecosystem, `create-react-app` scaffold
- **App.js**: Root component, renders with `ReactDOM.createRoot`

## HOL 2 - Class Components
- **Concepts**: Basic class components, export
- **Files**: `Home.js`, `About.js`, `Contact.js` - 3 simple class/function components

## HOL 3 - Function Components with Props
- **Concepts**: Function components, props destructuring
- **File**: `CalculateScore.js` - receives `name`, `school`, `total`, `goal` as props

## HOL 4 - Lifecycle Methods
- **Concepts**: `componentDidMount`, `componentDidCatch`, fetch API
- **File**: `Posts.js` - class component fetching from `jsonplaceholder`, error boundary

## HOL 5 - CSS Modules
- **Concepts**: CSS Modules (`.module.css`), inline styles, import styles
- **File**: `CohortDetails.js` with `CohortDetails.module.css`

## HOL 6 - React Router
- **Concepts**: `BrowserRouter`, `Routes`, `Route`, `Link`, `useParams`
- **Files**: `TrainersList.js` (list), `TrainerDetails.js` (detail with `useParams`)
- **Data**: `TrainersMock.js` (3 trainers)

## HOL 7 - Props (Shopping Cart)
- **Concepts**: Passing props to child components
- **File**: `OnlineShopping.js` - `Cart` child component receives `item` prop

## HOL 8 - State (Counter)
- **Concepts**: Component state, `this.setState`, event handlers
- **File**: `CountPeople.js` - tracks entry/exit counts

## HOL 9 - ES6 Features
- **Concepts**: Arrow functions, `map()`, `filter()`, destructuring, spread operator
- **Files**: `ListofPlayers.js` (filter + map), `IndianPlayers.js` (destructuring + spread)

## HOL 10 - JSX
- **Concepts**: JSX expressions, inline styles (`style={{}}`), conditional styling
- **File**: `OfficeSpaceRental.js` - renders office list with rent-based color

## HOL 11 - Events
- **Concepts**: Event handlers, `onClick`, `onChange`, `onSubmit`, synthetic events
- **Files**: `EventExamples.js` (counter + form), `CurrencyConverter.js` (dollar->rupee)

## HOL 12 - Conditional Rendering
- **Concepts**: Ternary operator, conditional display, `&&`, booked state
- **File**: `TicketBooking.js` - booking confirmation shown/hidden

## HOL 13 - Lists & Keys
- **Concepts**: `.map()` with `key` prop, rendering arrays, conditional subsets
- **Files**: `BookDetails.js`, `BlogDetails.js`, `CourseDetails.js` (all use map + keys)

## HOL 14 - Context API
- **Concepts**: `createContext`, `Provider`, `useContext`, theme toggling
- **File**: `ThemeApp.js` - ThemeContext provides theme + toggle, consumed by two components

## HOL 15 - Controlled Forms
- **Concepts**: Controlled components, form state, onSubmit
- **File**: `ComplaintRegister.js` - register complaints via form

## HOL 16 - Form Validation
- **Concepts**: Per-field validation, error messages, submit validation
- **File**: `Register.js` - validates name, email, phone, password, city

## HOL 17 - REST API (Axios)
- **Concepts**: Axios GET, loading/error states, displaying API data
- **File**: `GetUser.js` - fetches random user from `randomuser.me`

## HOL 18 - Unit Testing (Jest + Enzyme)
- **Concepts**: Component rendering, prop testing with RTL/Jest
- **File**: `__tests__/CohortDetails.test.js` - 2 tests (default + custom props)

## HOL 19 - Unit Testing (Jest + Mock Axios)
- **Concepts**: Component rendering test
- **File**: `__tests__/GitClient.test.js` - basic render test

## Key Libraries Used
- `react` / `react-dom` (18.x)
- `react-router-dom` (6.x)
- `axios` (1.x)
- `@testing-library/react` (14.x)

## Running the App
```bash
cd React/project
npm install
npm start      # development server
npm run build  # production build
npm test       # run tests
```
