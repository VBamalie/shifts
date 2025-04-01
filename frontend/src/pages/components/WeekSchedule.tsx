import { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";
import { useAuth } from "./AuthContext";

//this will be a component of solely the week's worth of schedules.
function WeekSchedule(props: any) {
     console.log(props.date);
     const { employee } = useAuth();
    const [shift, setShift] = useState<any>([]);
    useEffect(() => {
         const calendarId:number = employee?.calendar;
         axiosInstance.get(`http://localhost:8080/api/shift/calendar/date/${props.date}/${calendarId}`).then((response) => {
          console.log(props.date);
          console.log(response.data);
          setShift(response.data);
          }).catch((error) => {
              console.log("error fetching shift", error);
          });
    }, []);
    const weekDayEnumList = [{weekDayEnum: "MON", name: "Monday"}, {weekDayEnum: "TUE", name: "Tuesday"}, {weekDayEnum: "WED", name: "Wednesday"}, {weekDayEnum: "THU", name: "Thursday"}, {weekDayEnum: "FRI", name: "Friday"}, {weekDayEnum: "SAT", name: "Saturday"}, {weekDayEnum: "SUN", name: "Sunday"}];
    return (

     <div>
          {weekDayEnumList.map((day)=>(
               <div key={day.weekDayEnum}>
                    <h1>{day.name}</h1>
                    {Array.isArray(shift) ? shift.map((shiftItem) => (
                         <div key={shiftItem.id}>
                              <p>{shiftItem.timeBlock.weekDayEnum === day.weekDayEnum && `Hours: ${shiftItem.timeBlock.startTime}-${shiftItem.timeBlock.endTime}`}</p>
                              <p> {shiftItem.timeBlock.weekDayEnum === day.weekDayEnum && `Employees required for shift: ${shiftItem.timeBlock.shiftsRequired}`}</p>
                              <p> {shiftItem.timeBlock.weekDayEnum === day.weekDayEnum && "Employees working the shift:"}</p>
                              {shiftItem.timeBlock.weekDayEnum === day.weekDayEnum && shiftItem.employeesWorking.map((employee: any) => (
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