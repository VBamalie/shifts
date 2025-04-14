import { useParams } from "react-router-dom";
import WeekSchedule from "./components/WeekSchedule";
import { Box, Button, Typography } from "@mui/material";
import EmployeeList from "./components/EmployeeList";
import { useState } from "react";
import EmployeeAvailability from "./components/EmployeeAvailability";
import WeeklyCalendar from "./components/DemoCal";
import AddShiftBox from "./components/AddShiftBox";

export default function EditWeeklySchedule() {
    const params = useParams();
    const [selectedEmployee, setSelectedEmployee] = useState(null);
    const [selectedShift, setSelectedShift] = useState(null);

    const handleEmployeeSelection = (employee: any) => {
        setSelectedEmployee(employee);
    };
    const handleShiftSelection = (shift: any) => {
      setSelectedShift(shift);
    };
    

  return (
    <Box>
    
    
    <Box id='edit-schedule'>
    {/* <WeekSchedule date={params.date}/> */}
    <WeeklyCalendar date={params.date} onShiftSelection={{onShiftSelection: handleShiftSelection}} />
    <Box id="employee-list" >
    <AddShiftBox selectedEmployee={selectedEmployee} selectedShift={selectedShift} />
    <EmployeeList onEmployeeSelection={handleEmployeeSelection}/>
    {selectedEmployee? <EmployeeAvailability selectedEmployee={selectedEmployee}/>: null}
    </Box>
    </Box>
    </Box>
  )
}