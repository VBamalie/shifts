import { useParams } from "react-router-dom";
import WeekSchedule from "./components/WeekSchedule";
import { Box, Button, Typography } from "@mui/material";
import EmployeeList from "./components/EmployeeList";
import { useEffect, useState } from "react";
import EmployeeAvailability from "./components/EmployeeAvailability";
import WeeklyCalendar from "./components/DemoCal";
import AddShiftBox from "./components/AddShiftBox";
import axiosInstance from "../axiosConfig";
import { useAuth } from "./components/AuthContext";

export default function EditWeeklySchedule() {
    const { employee } = useAuth();
    const params = useParams();
    const [selectedEmployee, setSelectedEmployee] = useState(null);
    const [selectedShift, setSelectedShift] = useState(null);
    const [shifts, setShifts] = useState([]);

    const handleEmployeeSelection = (employee: any) => {
        setSelectedEmployee(employee);
    };
    const handleShiftSelection = (shift: any) => {
      setSelectedShift(shift);
    };
    const handleAddShift = (shift: { id: number }) => {
      setShifts((prevShifts: { id: number }[]) => {
        const filteredShifts = prevShifts.filter((s) => s.id !== shift.id);
        return [...filteredShifts, shift];
      });
    };

    useEffect(() => {
      const calendarId: number = employee?.calendar;

      axiosInstance.get(`http://localhost:8080/api/shift/calendar/date/${params.date}/${calendarId}`).then((response) => {
              setShifts(response.data); // Update state with fetched shifts
              console.log(shifts);
      }).catch((error) => {
          console.log("error fetching shift", error);
      });
  }, []);
    

  return (
    <Box>
    <Box id='edit-box'>
    <AddShiftBox selectedEmployee={selectedEmployee} selectedShift={selectedShift} addShift={handleAddShift} />
    <EmployeeList onEmployeeSelection={handleEmployeeSelection}/>
    {selectedEmployee ? <EmployeeAvailability selectedEmployee={selectedEmployee}/>: <Box/>}
    </Box>
    <Box id='edit-schedule'>
    {/* <WeekSchedule date={params.date}/> */}
    <WeeklyCalendar shifts={shifts} onShiftSelection={{onShiftSelection: handleShiftSelection}} />
    
    </Box>
    </Box>
  )
}