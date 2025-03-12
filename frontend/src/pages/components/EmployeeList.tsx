import { useState, useEffect } from "react";

//this will be a list of the employees that are connected with the Calendar. It will display the name of the employees and have some meta data within each employee for their availability and their time off requests. When a manager tries to add an employe to a shift they are unavailable for. it will put a small alert on the employee's name that will say they are scheduled for a shift they are unavailable for.
function EmployeeList(){
     const [employees, setEmployees] = useState([]);
      useEffect(() => {
        fetch("http://localhost:8080/api/employee/calendar/1")
          .then((response) => response.json())
          .then((data) => setEmployees(data))
      }, []);    
      return(
        <div>
          {employees.map((
            employee: { 
                id: number, 
                firstName: string , 
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
                } }) => (
            <div key={employee.id}>
              <h3>{employee.firstName} {employee.lastName}</h3>
              <p>Monday: {employee.availability.mon_start} - {employee.availability.mon_end}</p>
              <p>Tuesday: {employee.availability.tue_start} - {employee.availability.tue_end}</p>
              <p>Wednesday: {employee.availability.wed_start} - {employee.availability.wed_end}</p>
              <p>Thursday: {employee.availability.thu_start} - {employee.availability.thu_end}</p>
              <p>Friday: {employee.availability.fri_start} - {employee.availability.fri_end}</p>
              <p>Saturday: {employee.availability.sat_start} - {employee.availability.sat_end}</p>
              <p>Sunday: {employee.availability.sun_start} - {employee.availability.sun_end}</p>
            </div>
          ))}
        </div>
      )
}
export default EmployeeList;