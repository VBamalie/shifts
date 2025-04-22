import { Box, Typography } from "@mui/material";
import { DataGrid, GridCellParams, GridColDef } from "@mui/x-data-grid";
import { useState } from "react";


export default function WeeklyCalendar(props: { onShiftSelection: { onShiftSelection: (shift: any) => void }, shifts: any[] }) {
    const shifts = props.shifts;


    const weekDayEnum = [{ name: 'Monday', enum: "MON" }, { name: 'Tuesday', enum: "TUE" }, { name: 'Wednesday', enum: "WED" }, { name: 'Thursday', enum: "THU" }, { name: 'Friday', enum: "FRI" }, { name: 'Saturday', enum: "SAT" }, { name: 'Sunday', enum: "SUN" }];

    function makeColumns(day: any): GridColDef[] {
        const column: GridColDef[] = [];
        //filter the shifts to only include the shifts for the day
        column.push({field: 'alert', headerName:'', width: 50, renderCell: (params) => <img src={params.value} alt={params.row.alertMessage} width="20" height="20" title={params.row.alertMessage} /> });
        column.push({ field: 'startTime', headerName: 'Start Time', width: 100 });
        column.push({ field: 'endTime', headerName: 'End Time', width: 100 });
        column.push({ field: 'employeesRequired', headerName: 'Req', width: 50 });
        const dayShifts = shifts?.length
            ? shifts.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day)
            : [];
        const columnAmount = determineEmployeeColumn(dayShifts);
        for (let i = 0; i < columnAmount; i++) {
            column.push({ field: `employeeWorking${i + 1}`, headerName: ``, width: 150, cellClassName: (params: GridCellParams<any>): string => params.value?.toString() || '' });
        }
        return column;
    }

    function determineEmployeeColumn(currentDayShifts: any) {
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
            if (shiftItem.employeesWorking.length > maxWorkingEmployees) {
                maxWorkingEmployees = shiftItem.employeesWorking.length;
            }
        });
        return maxRequiredEmployees > maxWorkingEmployees ? maxRequiredEmployees : maxWorkingEmployees;
    }
    function makeRows(day: string): any {

        const currentDayShifts = shifts.filter((shiftItem: { timeBlock: { weekDayEnum: any } }) => shiftItem.timeBlock.weekDayEnum === day);//filter the shifts to only include the shifts for the day
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

        const rows = currentDayShifts.map((item) => {//create a row for each of those shifts
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

            for (let i = 0; i < employeeColumnAmount; i++) {//include as many columns as there are employees working the shift
                item.employeesWorking[i] ?
                    row[`employeeWorking${i + 1}`] = item.employeesWorking[i]?.firstName + " " + item.employeesWorking[i]?.lastName || 'Add an Employee' : row[`employeeWorking${i + 1}`] = ` `;
                    
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
        rows.sort((a, b) => {
            if (a.startTime < b.startTime) {
                return -1;
            }
            if (a.startTime > b.startTime) {
                return 1;
            }
            return 0;
        })
        return rows;
    }
    return (
        <Box className="edit-schedule-grid">
            {weekDayEnum.map((day) => (//maps through each day to create a grid for each day
                <Box 
                key={day.enum}
                 id={day.enum} 
                 className="week-day"
                 >
                    <Typography className="week-day-name" variant="h4">{day.name}</Typography>
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