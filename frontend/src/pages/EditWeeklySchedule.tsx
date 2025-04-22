import { useParams } from "react-router-dom";
import { Box, Button, ButtonGroup, Typography } from "@mui/material";
import EmployeeList from "./components/EmployeeList";
import { useEffect, useState } from "react";
import EmployeeAvailability from "./components/EmployeeAvailability";
import WeeklyCalendar from "./components/EditWeeklyCalendar";
import AddShiftBox from "./components/AddShiftBox";
import axiosInstance from "../axiosConfig";
import { useAuth } from "./components/AuthContext";

export default function EditWeeklySchedule() {
  const { employee } = useAuth();//gets the employee profile from authcontext
  const params = useParams();
  const [selectedEmployee, setSelectedEmployee] = useState(null);
  const [selectedShift, setSelectedShift] = useState(null);
  const [shifts, setShifts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [autocreate, setAutocreate] = useState(false);
  const [employees, setEmployees] = useState([]);
  const [coloredCells, setColoredCells] = useState<Array<{
    className: string;
    color: string;
  }>>([]);
  let colorList = ['068D9D', '7A7FB8', 'FE938C','7DCD85','E71D36', 'AF5AAF', 'DA6C9C','DA6C9C','FE5F55', 'C5F4E0', 'C7CCDB','A5C4D4', '8D809D']

  // Function to generate random color
  const generateRandomColor = () => {
    
    let color = '#';
    const chosenColor = colorList[Math.floor(Math.random() * colorList.length)];
        color += chosenColor;
    return color;
}
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
    });
  };
  

  useEffect(() => {
    const calendarId: number = employee?.calendar;
    axiosInstance.get(`http://localhost:8080/api/shift/calendar/date/${params.date}/${calendarId}`).then((response) => {
      setShifts(response.data); // Update state with fetched shifts
      let employeesWorkingShifts = 0;
      response.data.forEach((shift: { employeesWorking: any[]; }) => {
        shift.employeesWorking.length > 0 && employeesWorkingShifts++;
      });
      if (employeesWorkingShifts === 0) {
        setAutocreate(true);
      } else {
        setLoading(true);
      }
    }).catch((error) => {
      console.log("error fetching shift", error);
    });
    axiosInstance.get(`http://localhost:8080/api/employee/calendar/${calendarId}`).then((response) => {
      setEmployees(response.data)
      response.data.forEach(element => {
        setColoredCells(prev => [...prev, {
          className: element?.lastName,
          color: `${generateRandomColor()}`
        }]);
      })
    }).catch((error) => {
      console.log("error fetching employees", error);
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

  let content;
  if (autocreate) {
    content = (
      <Box id='autocreate-confirmation'>
        <Typography variant="h3"> Looks like this is a new schedule. Would you like to create shifts automatically? </Typography>
        <ButtonGroup size="large" aria-label="Large button group">
          <Button variant="contained" color="primary" onClick={() => handleAutoCreate()}> yes</Button>
          <Button variant="contained" color="secondary" onClick={() => handleRejectAutoCreate()}>No</Button>
        </ButtonGroup>
      </Box>
    );
  } else if (loading) {
    content = (
      <><Box id='edit-box'>
        <AddShiftBox selectedEmployee={selectedEmployee} selectedShift={selectedShift} addShift={handleAddShift} />
        <EmployeeList onEmployeeSelection={handleEmployeeSelection} />
        {selectedEmployee ? <EmployeeAvailability selectedEmployee={selectedEmployee} /> : <Box />}
      </Box>
        <Box
          id='edit-schedule'
          sx={Object.fromEntries(coloredCells.map((cell) => [
            `& .${cell.className}`,
            { backgroundColor: cell.color }
          ]))}
        >
          <WeeklyCalendar shifts={shifts} onShiftSelection={{ onShiftSelection: handleShiftSelection }} />
        </Box></>
    );
  } else {
    content = (
      <Box className="loading-container">
        <Typography variant="h1">Loading...</Typography>
      </Box>
    );
  }

  return (
    <Box>
      {content}
    </Box>
  );
}