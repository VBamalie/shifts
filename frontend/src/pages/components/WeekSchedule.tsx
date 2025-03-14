import { useEffect, useState } from "react";

//this will be a component of solely the a week's worth of schedules.
function WeekSchedule() {
     
    const [shift, setShift] = useState<any>({});
    useEffect(() => {
         fetch("http://localhost:8080/api/shift/calendar/1")
              .then((response) => response.json())
              .then((data) => setShift(data));
    }, []);
    
    return (
         Array.isArray(shift) ? shift.map((shiftItem) => (
              <div key={shiftItem.id}>
                   <p>{shiftItem.timeBlock.weekDayEnum}: {shiftItem.timeBlock.startTime}-{shiftItem.timeBlock.endTime}</p>
                   <p>Employees Working: {shiftItem.employeesWorking[0].first}</p>
              </div>
         )) : null
    );
}
export default WeekSchedule;