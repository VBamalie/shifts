import axios from "axios";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from "./pages/LoginPage";
import NewBusiness from "./pages/NewBusiness";
import EditSchedule from "./pages/EditSchedule";
import Dashboard from "./pages/Dashboard";
import EmployeeRegistration from "./pages/components/EmployeeRegistration";
import ProtectedRoute from "./pages/components/ProtectedRoute";
import { AuthProvider } from "./pages/components/AuthContext";


// import TestFetch from "./testFetch";

// function App() {
//   const getData=()=>{
//       axios
//           .get("localhost:8080/api/calendar")
//           .then(data => console.log(data.data))
//           .catch(error => console.log(error));
//   };
//   getData();

export default function App() {
  return (
    <AuthProvider>
    <Router>
      <Routes>
        {/* <Route path="/" element={<Navigate to="/login" replace />} /> */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/newbusiness" element={<NewBusiness />} />
        <Route path="/editschedule" element={<EditSchedule />} />
        <Route path="/registration" element={<EmployeeRegistration />} />

      </Routes>
    </Router>
    </AuthProvider>
  );
}
