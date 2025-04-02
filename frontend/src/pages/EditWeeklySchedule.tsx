import { useParams } from "react-router-dom";
import WeekSchedule from "./components/WeekSchedule";
import { Box } from "@mui/material";
import EmployeeList from "./components/EmployeeList";

export default function EditWeeklySchedule() {
    const params = useParams();

  return (
    <Box id='edit-schedule'>
    <WeekSchedule date={params.date}/>
    <Box id="employee-list">
    <EmployeeList/>
    </Box>
    </Box>
  )
}