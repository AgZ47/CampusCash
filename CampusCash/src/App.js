import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from '../src/components/Register.js';
import Login from '../src/components/Login.js';
import UserProfile from '../src/components/UserProfile.js';
import DueDetails from '../src/components/DueDetails.js';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Login />} /> {/* Set the default route to /login */}
        <Route path="/user" element={<UserProfile />} />
        <Route path="/due" element={<DueDetails/>}/>
      </Routes>
    </Router>
  );
}

export default App;
