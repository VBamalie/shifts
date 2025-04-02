import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from "./pages/LoginPage";
import Dashboard from "./pages/Dashboard";
import EmployeeRegistration from "./pages/EmployeeRegistration";
import { AuthProvider} from "./pages/components/AuthContext";
import NewCalendar from './pages/NewCalendar';
import HorizontalLinearStepper from './pages/demoStepper';
import DemoTimeBlock from './pages/DemoTimeBlock';
import Navbar from './pages/components/navbar';
import EditWeeklySchedule from './pages/EditWeeklySchedule';



export default function App() {
  return (
    <AuthProvider>
      <Navbar/>
    <Router>
      <Routes>
        <Route path="/demo-time" element={<DemoTimeBlock/>}/>
        <Route path="/demo" element={<HorizontalLinearStepper/>}/>
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
      <Route path="/new-calendar" element={<NewCalendar/>}/>
        <Route path="/registration" element={<EmployeeRegistration />} />
      </Routes>
    </Router>
    </AuthProvider>
  );
}