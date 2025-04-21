//This is the main file for the frontend of the application. It contains the routes for the different pages of the application.
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from "./pages/LoginPage";
import Dashboard from "./pages/Dashboard";
import EmployeeRegistration from "./pages/EmployeeRegistration";
import { AuthProvider} from "./pages/components/AuthContext";
import HorizontalLinearStepper from './pages/RegisterBusiness';
import EditTimeBlock from './pages/EditTimeBlock';
import Navbar from './pages/components/navbar';
import EditWeeklySchedule from './pages/EditWeeklySchedule';
import"./App.css"
import RegisterBusiness from './pages/RegisterBusiness';



export default function App() {
  return (
    <AuthProvider>
      <Navbar/>
    <Router>
      <Routes>
        <Route path="/register-business" element={<RegisterBusiness/>}/>
        <Route path="/" element={<Navigate to="/dashboard" />} />
        <Route path="/login" element={<LoginPage />} />
        {localStorage.getItem('employee') ? (
        <Route path="/dashboard" element={<Dashboard />} />
      ) : (
        <Route path="/dashboard" element={<Navigate to="/login" />} />
      )}
      {localStorage.getItem('employee') ? (
        <Route path="/edit-schedule/:date" element={< EditWeeklySchedule />} />
      ) : (
        <Route path="/login" element={<Navigate to="/login" />} />
      )}
      {localStorage.getItem('employee') ? (
        <Route path="/new-employee" element={<EmployeeRegistration />} />
      ) : (
        <Route path="/new-employee" element={<Navigate to="/login" />} />
      )}
      {localStorage.getItem('employee') ? (
        <Route path="/edit-time-block" element={<EditTimeBlock/>}/>
      ) : (
        <Route path="/edit-time-block" element={<Navigate to="/login" />} />
      )}
        
      </Routes>
    </Router>
    </AuthProvider>
  );
}