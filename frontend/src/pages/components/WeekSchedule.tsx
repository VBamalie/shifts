//this is the component that displays the current week's schedule. it will display the day and hours of each timeblock as well as how many shifts are required and which employees are currently scheduled to work this shift.
import { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";
import { Box, Typography } from "@mui/material";
import { GridColDef, DataGrid } from "@mui/x-data-grid";

//this will be a component of solely the week's worth of schedules.
function WeekSchedule(props: any) {
     const { employee } = useAuth();//gets the employee profile from authcontext
    const [shift, setShift] = useState([]);
    const [refreshTrigger, setRefreshTrigger] = useState(0); // Trigger for re-fetching

    const [coloredCells, setColoredCells] = useState<{className: string, color: string}[]>([]);

    
    function addShifts() {
     axiosInstance.post(`http://localhost:8080/api/shift/calendar/addShifts/${props.date}`, employee?.calendar)
         .then(() => {
             setRefreshTrigger(prev => prev + 1); // Trigger a re-fetch
         })
         .catch(error => {
             console.error("Error creating shifts", error);
         });
 }
         
   // Function to generate random color
   const generateRandomColor = () => {
    const colorList = ['068D9D', '7A7FB8', 'FE938C','7DCD85','E71D36', 'AF5AAF', 'DA6C9C','DA6C9C','FE5F55']
    let color = '#';
    const chosenColor = colorList[Math.floor(Math.random() * colorList.length)];
        color += chosenColor;
        console.log("color", color);
        colorList.splice(colorList.indexOf(chosenColor), 1);
    return color;
}

// Function to create coloredCells list
const createColoredCells = (shifts: any[]) => {
    const colorMap: {className: string, color: string}[] = [];
    
    shifts.forEach((shiftItem: any) => {
        if (shiftItem.employeesWorking && shiftItem.employeesWorking.length > 0) {
            shiftItem.employeesWorking.forEach((employee: any) => {
                // Check if employee's lastName already exists in colorMap
                const existingEntry = colorMap.find(item => item.className === employee.lastName);
                
                if (!existingEntry && employee.lastName) {
                    colorMap.push({
                        className: employee.lastName,
                        color: generateRandomColor()
                    });
                }
            });
        }
    });
    
    setColoredCells(colorMap);
};
    
    useEffect(() => {
         const calendarId:number = employee?.calendar;
         axiosInstance.get(`http://localhost:8080/api/shift/calendar/date/${props.date}/${calendarId}`).then((response) => {
          if (response.data.length === 0) {
               addShifts(); // Create shifts if none exist
           } else {
               setShift(response.data); // Update state with fetched shifts
               console.log(response.data);
               createColoredCells(response.data);
           }
          }).catch((error) => {
              console.log("error fetching shift", error);
          });
    }, [props.date, employee, refreshTrigger ]);
    const weekDayEnum = [{weekDayEnum: "MON", name: "Monday"}, {weekDayEnum: "TUE", name: "Tuesday"}, {weekDayEnum: "WED", name: "Wednesday"}, {weekDayEnum: "THU", name: "Thursday"}, {weekDayEnum: "FRI", name: "Friday"}, {weekDayEnum: "SAT", name: "Saturday"}, {weekDayEnum: "SUN", name: "Sunday"}];//an enum to create more readable code


    function makeColumns(day: any): GridColDef[] {
        const column: GridColDef[] = [];
        //filter the shifts to only include the shifts for the day
        column.push({field: 'alert', headerName:'', width: 50, renderCell: (params) => <img src={params.value} alt={params.row.alertMessage} width="20" height="20" title={params.row.alertMessage} /> });
        column.push({ field: 'startTime', headerName: 'Start Time', width: 100 });
        column.push({field: 'endTime', headerName: 'End Time', width: 100 });
        column.push({field:'employeesRequired', headerName: 'Req', width: 50 });
        const dayShifts = shift?.length
    ? shift.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day)
    : [];
    const columnAmount = determineEmployeeColumn(dayShifts);
    for (let i = 0; i < columnAmount; i++) {
        column.push({ field: `employeeWorking${i + 1}`, headerName: ``, width: 150, cellClassName: (params: GridCellParams<any>): string => params.value?.toString() || '' });
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
        const currentDayShifts = shift.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day);
        const employeeColumnAmount = determineEmployeeColumn(currentDayShifts);
        // Track total hours worked by each employee for this day
    const employeeHoursMap: Record<string, number> = {};
    
    // First pass: calculate total hours for each employee
    currentDayShifts.forEach((item) => {
        const shiftDuration = item.timeBlock.endTime - item.timeBlock.startTime;
        item.employeesWorking.forEach((employee: any) => {
            const employeeId = employee.id;
            if (!employeeHoursMap[employeeId]) {
                employeeHoursMap[employeeId] = 0;
            }
            employeeHoursMap[employeeId] += shiftDuration;
        });
    });

        const rows = currentDayShifts.map((item) => {
            let alert = false;
            let alertMessage = "Shift is properly staffed";
            if(item.employeesWorking.length < item.timeBlock.shiftsRequired) {
                alert = true;
                alertMessage = "Shift is understaffed";
            }
            if(item.employeesWorking.length > item.timeBlock.shiftsRequired) {
                alert = true;
                alertMessage = "Shift is overstaffed";
            }


            const row: any = {
                id: item.id,
                startTime: item.timeBlock.startTime,
                endTime: item.timeBlock.endTime,
                employeesRequired: item.timeBlock.shiftsRequired
            };
            
            for(let i = 0; i < employeeColumnAmount; i++) {
                item.employeesWorking[i]?
                row[`employeeWorking${i + 1}`] = item.employeesWorking[i]?.firstName + " " + item.employeesWorking[i]?.lastName || 'Add an Employee': row[`employeeWorking${i + 1}`] = ` `;
            }
            
            item.employeesWorking.map((employee: any) => {
                //check if the employee is unavailable for the shift
                const availabilityStart = Object.keys(employee.availability).find((key) => key === `${day.toLowerCase()}_start`);
                const availabilityEnd = Object.keys(employee.availability).find((key) => key === `${day.toLowerCase()}_end`);
                if(employee.availability[availabilityStart] > item.timeBlock.startTime || employee.availability[availabilityEnd] < item.timeBlock.endTime) {
                    alert = true;
                    alertMessage = "Employee is outside of their available hours";
                }
                if (employeeHoursMap[employee.id] > 8) {
                    alert = true;
                    alertMessage = `${employee.firstName} ${employee.lastName} is working more than 8 hours (${employeeHoursMap[employee.id]} hours)`;
                }
            });
            
            alert ? row.alert = "/caution.png" : row.alert = "/check.png";
            row.alertMessage = alertMessage;

            return row;
        });     
        return rows;
    }

    return (
        <Box 
        className="edit-schedule-grid"

        >
            {weekDayEnum.map((day) => (
                <Box key={day.weekDayEnum} id={day.weekDayEnum} className="week-day">
                    <Typography className="week-day-name"variant="h4">{day.name}</Typography>
                    <DataGrid
                        columns={makeColumns(day.weekDayEnum)}
                        rows={makeRows(day.weekDayEnum)}
                        sx={
                            Object.fromEntries(coloredCells.map((cell)=>[
                            `& .${cell.className}`,
                            { backgroundColor: cell.color }
                          ]
                        ))}
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