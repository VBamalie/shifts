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
    const [loading, setLoading] = useState(false);
    const [autocreate, setAutocreate] = useState(false);

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
              let employeesWorkingShifts = 0;
              response.data.forEach((shift: { employeesWorking: any[]; }) => {
                shift.employeesWorking.length > 0 && employeesWorkingShifts++;
              });
              employeesWorkingShifts === 0 ?  setAutocreate(true) : setLoading(true);
      }).catch((error) => {
          console.log("error fetching shift", error);
      });
  }, []);
    

  function handleAutoCreate(): void {
    axiosInstance.put(`http://localhost:8080/api/auto-create/${employee?.calendar}/${params.date}`).then((response) => {
    setLoading(true);
    setAutocreate(false);
    window.location.reload();
  }).catch((error) => {
    console.log("error", error);
  });
  }
  
  function handleRejectAutoCreate(): void {
    setLoading(true);
  }

  return (
    
    <Box>
      {autocreate ? (
        <Box id = 'autocreate-confirmation'>
          <Typography variant="h3"> Looks like this is a new schedule. Would you like to create shifts automatically? </Typography>
          <Button variant="contained" color="primary" onClick={()=> handleAutoCreate()}> yes</Button>
          <Button variant="contained" color="primary" onClick={()=> handleRejectAutoCreate()}>No</Button>
        </Box>
      ) :
      loading? (
    <><Box id='edit-box'>
          <AddShiftBox selectedEmployee={selectedEmployee} selectedShift={selectedShift} addShift={handleAddShift} />
          <EmployeeList onEmployeeSelection={handleEmployeeSelection} />
          {selectedEmployee ? <EmployeeAvailability selectedEmployee={selectedEmployee} /> : <Box />}
        </Box><Box id='edit-schedule'>
            <WeeklyCalendar shifts={shifts} onShiftSelection={{ onShiftSelection: handleShiftSelection }} />
          </Box></>
    ) : (
      <Box className="loading-container">
        <Typography variant="h1">Loading...</Typography>
      </Box>
    )}
    </Box>
    
  )
}