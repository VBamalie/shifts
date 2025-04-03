//this is the component that displays the current week's schedule. it will display the day and hours of each timeblock as well as how many shifts are required and which employees are currently scheduled to work this shift.
import { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";

//this will be a component of solely the week's worth of schedules.
//TODO: https://mui.com/x/react-data-grid/row-spanning/ do week schedule like the demo maybe?
function WeekSchedule(props: any) {
     const { employee } = useAuth();
    const [shift, setShift] = useState<any>([]);
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
    const weekDayEnumList = [{weekDayEnum: "MON", name: "Monday"}, {weekDayEnum: "TUE", name: "Tuesday"}, {weekDayEnum: "WED", name: "Wednesday"}, {weekDayEnum: "THU", name: "Thursday"}, {weekDayEnum: "FRI", name: "Friday"}, {weekDayEnum: "SAT", name: "Saturday"}, {weekDayEnum: "SUN", name: "Sunday"}];//an enum to create more readable code
    return (

     <div id="weekSchedule">
          {weekDayEnumList.map((day)=>(//this will map over our enum to render each day of the week and what shifts are scheduled for that day
               <div key={day.weekDayEnum} id={day.weekDayEnum} className="weekDay">
                    <h1>{day.name}</h1>
                    {Array.isArray(shift) ? shift
                        .filter(shiftItem => shiftItem.timeBlock.weekDayEnum === day.weekDayEnum)
                        .map((shiftItem) => (
                            <div className='time-block'key={shiftItem.id}>
                                <p>Hours: {shiftItem.timeBlock.startTime} - {shiftItem.timeBlock.endTime}</p>
                                <p>Employees required for shift: {shiftItem.timeBlock.shiftsRequired}</p>
                                <p>Employees working the shift:</p>
                                {shiftItem.employeesWorking.map((employee: any) => (
                                    <p key={employee.id}>{employee.firstName} {employee.lastName}</p>
                                ))}
                            </div>
                        )) : null}
               </div>
          ))
}
     </div>
    );
}
export default WeekSchedule;