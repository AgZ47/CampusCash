import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from '../src/components/Register.js';
import Login from '../src/components/Login.js';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Login />} /> {/* Set the default route to /login */}
      </Routes>
    </Router>
  );
}

export default App;
