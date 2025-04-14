import { useParams } from "react-router-dom";
import WeekSchedule from "./components/WeekSchedule";
import { Box } from "@mui/material";
import EmployeeList from "./components/EmployeeList";
import { useState } from "react";
import EmployeeAvailability from "./components/EmployeeAvailability";
import WeeklyCalendar from "./components/DemoCal";

export default function EditWeeklySchedule() {
    const params = useParams();
    const [selectedEmployee, setSelectedEmployee] = useState(null);

    const handleEmployeeSelection = (employee: any) => {
        setSelectedEmployee(employee);
    };

  return (
    <Box id='edit-schedule'>
    {/* <WeekSchedule date={params.date}/> */}
    <WeeklyCalendar date={params.date} />
    <Box id="employee-list">
    <EmployeeList onEmployeeSelection={handleEmployeeSelection}/>
    {selectedEmployee? <EmployeeAvailability selectedEmployee={selectedEmployee}/>: null}
    </Box>
    </Box>
  )
}