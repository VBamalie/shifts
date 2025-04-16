import { Box, TextField, Typography } from "@mui/material";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";

export default function WeeklyCalendar(props:{ onShiftSelection : { onShiftSelection: (shift: any) => void }, shifts: any[]}) {
    const { employee } = useAuth();
    const shifts = props.shifts;
    const [selectedShiftCell, setSelectedShiftCell] = useState<any>(null);

    const [refreshTrigger, setRefreshTrigger] = useState(0); // Trigger for re-fetching


    // function addShifts() {
    //     axiosInstance.post(`http://localhost:8080/api/shift/calendar/addShifts/${props.date}`, employee?.calendar)
    //         .then(() => {
    //             setRefreshTrigger(prev => prev + 1); // Trigger a re-fetch
    //         })
    //         .catch(error => {
    //             console.error("Error creating shifts", error);
    //         });
    // }
    
    

    const weekDayEnum = [{ name: 'Monday', enum: "MON" }, { name: 'Tuesday', enum: "TUE" }, { name: 'Wednesday', enum: "WED" }, { name: 'Thursday', enum: "THU" }, { name: 'Friday', enum: "FRI" }, { name: 'Saturday', enum: "SAT" }, { name: 'Sunday', enum: "SUN" }];
    
    const demoColumns = [
        { field: 'hours', headerName: '', width: 100 },
        { field: 'employeeWorking1', headerName: '', width: 100 },
        { field: 'employeeWorking2', headerName: '', width: 100 }
    ]

    const demoRows = [
        {
            id: 0,
            hours: "10-11",
            employeeWorking1: shifts[0]?.firstDate || '',
            employeeWorking2: "Jane Doe"
        },
        {
            id: 1,
            hours: "12-24",
            employeeWorking1: "John Doe",
            employeeWorking2: null
        },
    ]

    function makeColumns(day: any): GridColDef[] {
        const column: GridColDef[] = [];
        //filter the shifts to only include the shifts for the day
        const dayShifts = shifts?.length
    ? shifts.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day)
    : [];
    const columnAmount = determineEmployeeColumn(dayShifts);
    for (let i = 0; i < columnAmount; i++) {
        column.push({ field: `employeeWorking${i + 1}`, headerName: `employee ${i+1}`, width: 170 });
    }
    
    column.push({ field: 'hours', headerName: 'Open Hours', width: 100 });
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
        const currentDayShifts = shifts.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day);
        const employeeColumnAmount = determineEmployeeColumn(currentDayShifts);
        
        const rows = currentDayShifts.map((item) => {
            const row: any = {
                id: item.id,
                hours: item.timeBlock.startTime + "-" + item.timeBlock.endTime,
            };
            
            for(let i = 0; i < employeeColumnAmount; i++) {
                item.employeesWorking[i]?
                row[`employeeWorking${i + 1}`] = item.employeesWorking[i]?.firstName + " " + item.employeesWorking[i]?.lastName || 'Add an Employee': row[`employeeWorking${i + 1}`] = `Add an Employee `;
            }
            return row;
        });
        return rows;
    }

    makeRows(weekDayEnum[0].enum);
    return (
        <Box className="edit-schedule-grid">
            {weekDayEnum.map((day) => (
                <Box key={day.enum} id={day.enum} className="weekDay">
                    <Typography variant="h4">{day.name}</Typography>
                    <DataGrid
                        
                        columns={makeColumns(day.enum)}
                        rows={makeRows(day.enum)}
                        // unstable_rowSpanning
                        hideFooter
                        showCellVerticalBorder
                        showColumnVerticalBorder
                        onRowSelectionModelChange={(newSelection) => {
                            const selectedRow = shifts.find((row: any) => row.id === newSelection[0]);
                            props.onShiftSelection.onShiftSelection(selectedRow);
                        }}
                    />
                </Box>
            ))}

         
        </Box>
    )
}