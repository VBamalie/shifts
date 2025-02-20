import { useEffect, useState } from "react";

function TestFetch() {
  const [calendar, setCalendar] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/api/calendar")
      .then((response) => response.json())
      .then((data) => setCalendar(data));
  }, []);

  return(
    <div>
      {/* <h1>{calendar}</h1> */}
      {/* map through calendar and display each event */}
      {calendar.map((event) => (
        <div key={event.id}>
          <h2>{event.businessName}</h2>
        </div>
      ))}
    </div>
  )
}
export default TestFetch;