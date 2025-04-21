//This is the main file for the frontend of the application. It contains the routes for the different pages of the application.
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from "./pages/LoginPage";
import Dashboard from "./pages/Dashboard";
import EmployeeRegistration from "./pages/EmployeeRegistration";
import { AuthProvider} from "./pages/components/AuthContext";
import NewCalendar from './pages/NewCalendar';
import HorizontalLinearStepper from './pages/RegisterBusiness';
import DemoTimeBlock from './pages/EditTimeBlock';
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
        <Route path="/edit-time-block" element={<DemoTimeBlock/>}/>
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
        <Route path="/registration" element={<EmployeeRegistration />} />
      ) : (
        <Route path="/registration" element={<Navigate to="/login" />} />
      )}
      {localStorage.getItem('employee') ? (
        <Route path="/edit-time-block" element={<DemoTimeBlock/>}/>
      ) : (
        <Route path="/registration" element={<Navigate to="/login" />} />
      )}
      <Route path="/edit-time-block" element={<NewCalendar/>}/>
        
      </Routes>
    </Router>
    </AuthProvider>
  );
}