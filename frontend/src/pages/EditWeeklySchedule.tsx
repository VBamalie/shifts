import { useParams } from "react-router-dom";
import { Box, Button, Typography } from "@mui/material";
import EmployeeList from "./components/EmployeeList";
import { useEffect, useState } from "react";
import EmployeeAvailability from "./components/EmployeeAvailability";
import WeeklyCalendar from "./components/EditWeeklyCalendar";
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
    const handleAddShift = (shift: {
      startTime: any; id: number 
}) => {
            setShifts((prevShifts: Array<{ startTime: any; id: number }>) => {
              const filteredShifts = prevShifts.filter((s) => s.id !== shift.id);
              const newShift = [...filteredShifts, shift];
              newShift.sort((a, b) => a.startTime - b.startTime);
              console.log("newShift", newShift);
              return newShift;
            });    };

    useEffect(() => {
      const calendarId: number = employee?.calendar;

      axiosInstance.get(`http://localhost:8080/api/shift/calendar/date/${params.date}/${calendarId}`).then((response) => {
              setShifts(response.data); // Update state with fetched shifts
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