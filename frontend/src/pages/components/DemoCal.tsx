import { Box, TextField, Typography } from "@mui/material";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";

export default function WeeklyCalendar(props:{ onShiftSelection : { onShiftSelection: (shift: any) => void }, shifts: any[]}) {
    const shifts = props.shifts;
  

    const weekDayEnum = [{ name: 'Monday', enum: "MON" }, { name: 'Tuesday', enum: "TUE" }, { name: 'Wednesday', enum: "WED" }, { name: 'Thursday', enum: "THU" }, { name: 'Friday', enum: "FRI" }, { name: 'Saturday', enum: "SAT" }, { name: 'Sunday', enum: "SUN" }];

    function makeColumns(day: any): GridColDef[] {
        const column: GridColDef[] = [];
        //filter the shifts to only include the shifts for the day
        column.push({ field: 'hours', headerName: 'Open Hours', width: 100 });
        const dayShifts = shifts?.length
    ? shifts.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day)
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
        const currentDayShifts = shifts.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day);
        const employeeColumnAmount = determineEmployeeColumn(currentDayShifts);
        
        const rows = currentDayShifts.map((item) => {
            const row: any = {
                id: item.id,
                hours: item.timeBlock.startTime + "-" + item.timeBlock.endTime,
            };
            
            for(let i = 0; i < employeeColumnAmount; i++) {
                item.employeesWorking[i]?
                row[`employeeWorking${i + 1}`] = item.employeesWorking[i]?.firstName + " " + item.employeesWorking[i]?.lastName || 'Add an Employee': row[`employeeWorking${i + 1}`] = ` `;
            }
            return row;
        });
        return rows;
    }

    makeRows(weekDayEnum[0].enum);
    return (
        <Box className="edit-schedule-grid">
            {weekDayEnum.map((day) => (
                <Box key={day.enum} id={day.enum} className="week-day">
                    <Typography className="week-day-name"variant="h4">{day.name}</Typography>
                    <DataGrid
                        columns={makeColumns(day.enum)}
                        rows={makeRows(day.enum)}
                        sx={{
                            height: 'fit-content',
                        }}
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