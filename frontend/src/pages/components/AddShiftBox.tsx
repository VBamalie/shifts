import { Box, Typography, Button } from "@mui/material";
import { useState } from "react";

export default function AddShiftBox(props:{selectedEmployee: any, selectedShift: any}) {
    const selectedEmployee = props.selectedEmployee;
    const selectedShift = props.selectedShift;
    const [selectedWorkingEmployee, setWorkingEmployee] = useState(props.selectedEmployee);
    const handleAddEmployeeToShift = (employee: any, shift: any) => {
        console.log("Adding employee to shift:", employee, shift);
      };
      const handleSelectWorkingEmployee = (employee: any)=>{
        console.log("Selected Employee:", employee);
        setWorkingEmployee(employee);
      }
      const handleRemoveEmployeeFromShift = ()=>{
        console.log("Removing employee from shift:", selectedEmployee, selectedShift);
      }

  return (
    <Box className="add-shift-container">
          <Box className="selected-shifts">
          {selectedShift ? <Typography variant="h5">Selected Shift: {selectedShift?.timeBlock.weekDayEnum} {selectedShift?.timeBlock.startTime} - {selectedShift.timeBlock.endTime}</Typography>: <Typography variant="h5">No Shift Selected</Typography>}
          {selectedShift?.employeesWorking.length > 0 ? (<>
            <Typography variant="h6"> Employees Working:</Typography>
            {selectedShift?.employeesWorking.map((employee: any) => (
              <Button key={employee.id} onClick={() => handleSelectWorkingEmployee(employee)}>
                {employee.firstName} {employee.lastName}
              </Button>//TODO: add toggle to button to change color to show that a specific employee has been selected to delete
            ))}
          </>) : <Typography variant="h6">No Employees Working</Typography>}
          </Box>
          <Box className="selected-employee">
          {selectedEmployee ? <Typography variant="h6">Selected Employee: {selectedEmployee?.firstName}</Typography>: <Typography variant="h6">No Employee Selected</Typography>}
            </Box >
            <Box className="button-box">
          {selectedEmployee && selectedShift && <Button onClick={() => {
            handleAddEmployeeToShift(selectedEmployee, selectedShift);
          }}>
            Add Employee To Shift
          </Button>}
          {selectedWorkingEmployee && <Button onClick={()=>{handleRemoveEmployeeFromShift()}}>
            Remove From Shift</Button>}
          
          </Box>
        </Box>
  )
}