import { Box, Typography, Button } from "@mui/material";
import { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";

//This is the box that allows managers to add employees to shifts and remove them from shifts. The manager selects a shift and an employee and adds them to the shift.
export default function AddShiftBox(props: { selectedEmployee: any, selectedShift: any }) {
  const selectedEmployee = props.selectedEmployee;
  const selectedShift = props.selectedShift;
  const [selectedWorkingEmployee, setWorkingEmployee] = useState(props.selectedEmployee);
  const [currentlySelectedEmployee, setCurrentlySelectedEmployee] = useState();


  const handleAddEmployeeToShift = async(e: {preventDefault:()=> void;}) => {
    // e.preventDefault();
    console.log(`current selected employee: ${currentlySelectedEmployee?.id}`)
    if(currentlySelectedEmployee){
    const response = await axiosInstance.put(`http://localhost:8080/api/employee/addShift/${currentlySelectedEmployee.id}`, selectedShift.id);
    try{
      console.log("response", response);
    }catch(error){
      console.log("error", error);
    }
  }
  };
  
  const handleSelectWorkingEmployee = (employee: any) => {
    console.log("Selected Employee:", employee);
    setWorkingEmployee(employee);
    setCurrentlySelectedEmployee(employee);
  }
  const handleRemoveEmployeeFromShift = () => {
    console.log("Removing employee from shift:", selectedEmployee, selectedShift);
  }
  useEffect(() => {
    setCurrentlySelectedEmployee(selectedEmployee);
  }, [props.selectedEmployee]);
  

  return (
    <Box className="add-shift-container">
      <Box className="selected-shifts">
        {selectedShift ? <Typography variant="h5">Selected Shift: {selectedShift?.timeBlock.weekDayEnum} {selectedShift?.timeBlock.startTime} - {selectedShift.timeBlock.endTime}</Typography> : <Typography variant="h5">No Shift Selected</Typography>}
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
        {selectedEmployee ? <Typography variant="h6">Selected Employee: {currentlySelectedEmployee?.firstName + " " + currentlySelectedEmployee?.lastName}</Typography> : <Typography variant="h6">No Employee Selected</Typography>}
      </Box >
      <Box className="button-box">
        {currentlySelectedEmployee === selectedWorkingEmployee && currentlySelectedEmployee !== null ? <Button onClick={() => {
          handleRemoveEmployeeFromShift();
        }}>
          Remove Employee From Shift
        </Button>
          :
          currentlySelectedEmployee === selectedEmployee && selectedShift && <Button onClick={() => {
            handleAddEmployeeToShift();
          }}>
            Add Employee To Shift
          </Button>
        }
      </Box>
    </Box>
  )
}