//this is the component that displays the current week's schedule. it will display the day and hours of each timeblock as well as how many shifts are required and which employees are currently scheduled to work this shift.
import { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";
import { Box, Typography } from "@mui/material";
import { GridColDef, DataGrid } from "@mui/x-data-grid";

//this will be a component of solely the week's worth of schedules.
//TODO: https://mui.com/x/react-data-grid/row-spanning/ do week schedule like the demo maybe?
function WeekSchedule(props: any) {
     const { employee } = useAuth();
    const [shift, setShift] = useState([]);
    const [refreshTrigger, setRefreshTrigger] = useState(0); // Trigger for re-fetching

    
    function addShifts() {
     axiosInstance.post(`http://localhost:8080/api/shift/calendar/addShifts/${props.date}`, employee?.calendar)
         .then(() => {
             setRefreshTrigger(prev => prev + 1); // Trigger a re-fetch
         })
         .catch(error => {
             console.error("Error creating shifts", error);
         });
 }
         
    
    useEffect(() => {
         const calendarId:number = employee?.calendar;
         axiosInstance.get(`http://localhost:8080/api/shift/calendar/date/${props.date}/${calendarId}`).then((response) => {
          if (response.data.length === 0) {
               addShifts(); // Create shifts if none exist
           } else {
               setShift(response.data); // Update state with fetched shifts
           }
          }).catch((error) => {
              console.log("error fetching shift", error);
          });
    }, [props.date, employee, refreshTrigger ]);
    const weekDayEnum = [{weekDayEnum: "MON", name: "Monday"}, {weekDayEnum: "TUE", name: "Tuesday"}, {weekDayEnum: "WED", name: "Wednesday"}, {weekDayEnum: "THU", name: "Thursday"}, {weekDayEnum: "FRI", name: "Friday"}, {weekDayEnum: "SAT", name: "Saturday"}, {weekDayEnum: "SUN", name: "Sunday"}];//an enum to create more readable code


function makeColumns(day: any): GridColDef[] {
        const column: GridColDef[] = [];
        //filter the shifts to only include the shifts for the day
        column.push({ field: 'hours', headerName: 'Open Hours', width: 100 });
        const dayShifts = shift?.length
    ? shift.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day)
    : [];
    const columnAmount = determineEmployeeColumn(dayShifts);
    for (let i = 0; i < columnAmount; i++) {
        column.push({ field: `employeeWorking${i + 1}`, headerName: ``, width: 150 });
    }
        return column;
    }

    function determineEmployeeColumn(currentDayShifts: any){
        //find the currentDayShift that has the highest employe encounter
        let maxRequiredEmployees = 0;
        currentDayShifts.forEach((shiftItem: { timeBlock: { shiftsRequired: number } }) => {
            if (shiftItem.timeBlock.shiftsRequired > maxRequiredEmployees) {
                maxRequiredEmployees = shiftItem.timeBlock.shiftsRequired;
            }
        });
        //find out if any shift has more employees than max employees
        let maxWorkingEmployees = 0;
        currentDayShifts.forEach((shiftItem: {
            employeesWorking: any; timeBlock: { shiftsRequired: number } 
}) => {
            if( shiftItem.employeesWorking.length > maxWorkingEmployees) {
                maxWorkingEmployees = shiftItem.employeesWorking.length;
            }
        });
        return maxRequiredEmployees > maxWorkingEmployees ? maxRequiredEmployees : maxWorkingEmployees;
    }
    function makeRows(day: string): any {

        //filter the shifts to only include the shifts for the day
        //sort the shifts by the earliest timed shift
        //create a row for each of those shifts
        //include the hours in one column
        //include as many columns as there are employees working the shift
        const currentDayShifts = shift.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day);
        const employeeColumnAmount = determineEmployeeColumn(currentDayShifts);
        
        const rows = currentDayShifts.map((item: any) => {
            const row: any = {
                id: item.id,
                hours: item.timeBlock.startTime + "-" + item.timeBlock.endTime,
            };
            
            for(let i = 0; i < employeeColumnAmount; i++) {
                item.employeesWorking[i]?
                row[`employeeWorking${i + 1}`] = item.employeesWorking[i]?.firstName + " " + item.employeesWorking[i]?.lastName || 'Add an Employee': row[`employeeWorking${i + 1}`] = ` `;
            }
            return row;
        });        return rows;
    }

    return (
        <Box className="edit-schedule-grid">
            {weekDayEnum.map((day) => (
                <Box key={day.weekDayEnum} id={day.weekDayEnum} className="week-day">
                    <Typography className="week-day-name"variant="h4">{day.name}</Typography>
                    <DataGrid
                        columns={makeColumns(day.weekDayEnum)}
                        rows={makeRows(day.weekDayEnum)}
                        sx={{
                            height: 'fit-content',
                        }}
                        // unstable_rowSpanning
                        hideFooter
                        showCellVerticalBorder
                        showColumnVerticalBorder
                    />
                </Box>
            ))}

         
        </Box>
    )
}
export default WeekSchedule;