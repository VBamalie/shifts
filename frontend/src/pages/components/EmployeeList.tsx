//this will be a list of the employees that are connected with the Calendar. It will display the name of the employees and have some meta data within each employee for their availability and their time off requests. When a manager tries to add an employe to a shift they are unavailable for. it will put a small alert on the employee's name that will say they are scheduled for a shift they are unavailable for.

import React from "react";
import { useState, useEffect } from "react";
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";
import { DataGrid, useGridApiContext } from '@mui/x-data-grid';
import { Box, Typography } from "@mui/material";

//TODO: Add functionality to show the employee is over hours or working outside of their availability.


/*This is the table that will display the list of employees. it has a mapping function that will reference the row function above. */
function EmployeeList({ onEmployeeSelection }: { onEmployeeSelection: (employee: any) => void }) {
  const { employee } = useAuth();//this is specifically the manager that is logged in
  const [employees, setEmployees] = useState([]);
  const [selectedEmployee, setSelectedEmployee] = useState(null);

  useEffect(() => {
    const calendarId: number = employee?.calendar;
    axiosInstance.get(`http://localhost:8080/api/employee/calendar/${calendarId}`).then((response) => {
      setEmployees(response.data)
    }).catch((error) => {
      console.log("error fetching employees", error);
    });
  }, []);

  return (
    <Box id='employee-list'>
      <Typography variant="h5">Employees</Typography>
    <DataGrid
      columns={[
        {
          field: 'firstName',
          headerName: 'First Name',
          headerClassName: 'data-grid-header'
        },
        {
          field: 'lastName',
          headerName: 'Last Name',
          headerClassName: 'data-grid-header'

        }
      ]}
      
      hideFooter
      rows={employees}
      onRowSelectionModelChange={(newSelection) => {
        const selectedRow = employees.find((row: any) => row.id === newSelection[0]);
        setSelectedEmployee(selectedRow || null);
        onEmployeeSelection(selectedRow || null);
      }} 
      sx={{
        '& .data-grid-header': {
          backgroundColor: '#E5F3FD',
        }
    }}
         />
    </Box>
  );
}
export default EmployeeList;