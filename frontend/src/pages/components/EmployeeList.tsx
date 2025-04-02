//this will be a list of the employees that are connected with the Calendar. It will display the name of the employees and have some meta data within each employee for their availability and their time off requests. When a manager tries to add an employe to a shift they are unavailable for. it will put a small alert on the employee's name that will say they are scheduled for a shift they are unavailable for.
import { TableContainer, Paper, Table, TableBody, TableRow, TableCell, IconButton, Collapse, TableHead, Typography } from "@mui/material";
import React from "react";
import { useState, useEffect } from "react";
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";
//TODO: Add functionality to show the employee is over hours or working outside of their availability.
function Row(props: { row: any }) {
  const { row } = props;
  const [open, setOpen] = React.useState(false);
  return (
    <React.Fragment>
            <TableRow key={row.id}>
              <TableCell>
                <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
                  {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                </IconButton>
              </TableCell>
              <TableCell component="th" scope="row">
                <Typography variant="h5">{row.firstName} {row.lastName}</Typography>
              </TableCell>
            </TableRow>
              <TableCell style={{paddingBottom: "0", paddingTop: "0"}} colSpan={6}>
                <Collapse in={open} timeout="auto" unmountOnExit>
                  <Table size="small" aria-label="purchases">
                    <TableHead>
                      <TableRow>
                        <Typography variant="h6">availability</Typography>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      <TableRow>
                      <TableCell>Monday: {row.availability.mon_start|| "Not Available"} - {row.availability.mon_end|| "Not Available"}</TableCell>
                      <TableCell>Friday: {row.availability.fri_start|| "Not Available"} - {row.availability.fri_end|| "Not Available"}</TableCell>
                    </TableRow>
                    <TableRow>
                      
                      <TableCell>Tuesday: {row.availability.tue_start|| "Not Available"} - {row.availability.tue_end|| "Not Available"}</TableCell>
                      <TableCell>Saturday: {row.availability.sat_start|| "Not Available"} - {row.availability.sat_end|| "Not Available"}</TableCell>
                    </TableRow>
                    <TableRow>
                      <TableCell>Wednesday: {row.availability.wed_start|| "Not Available"} - {row.availability.wed_end|| "Not Available"}</TableCell>
                      <TableCell>Sunday: {row.availability.sun_start|| "Not Available"} - {row.availability.sun_end|| "Not Available"}</TableCell>
                    </TableRow>
                    <TableRow>
                      <TableCell>Thursday: {row.availability.thu_start|| "Not Available"} - {row.availability.thu_end|| "Not Available"}</TableCell>
                      <TableCell/>
                    </TableRow>
                    <TableHead>
                      <TableRow>
                      <Typography variant="h6">Time Off Requests</Typography>
                      </TableRow>
                    </TableHead>
                      {
                        row.timeOffRequests.map((timeOffRequest: any) => (
                          <TableRow key={timeOffRequest.id}>
                            <TableCell>{timeOffRequest.firstDate}</TableCell>
                            <TableCell>{timeOffRequest.weekDayEnum}</TableCell>
                          </TableRow>
                        ))
                      }
                    </TableBody>
                  </Table>
                </Collapse>
              </TableCell>
            <TableRow>
            </TableRow>
    </React.Fragment>
  );
}
/*This is the table that will display the list of employees. it has a mapping function that will reference the row function above. */
function EmployeeList() {
  const {employee} = useAuth();
  const [employees, setEmployees] = useState([]);
  const [open, setOpen] = React.useState(false);
  useEffect(() => {
    const calendarId:number = employee?.calendar;
    axiosInstance.get(`http://localhost:8080/api/employee/calendar/${calendarId}`).then((response) => {
      setEmployees(response.data)
    }).catch((error) => {
      console.log("error fetching employees", error);
    });
  }, []);
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell><Typography variant="h3">Employee List</Typography></TableCell>
            <TableCell/>
          </TableRow>
        </TableHead>
        <TableBody>
          {employees.map((
            employee: {
              id: number,
              firstName: string,
              lastName: string,
              availability: {
                mon_start: number,
                mon_end: number,
                tue_start: number,
                tue_end: number,
                wed_start: number,
                wed_end: number,
                thu_start: number,
                thu_end: number,
                fri_start: number,
                fri_end: number,
                sat_start: number,
                sat_end: number,
                sun_start: number,
                sun_end: number
              }
            }) => (
            <Row key={employee.id} row={employee} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  )
}
export default EmployeeList;