import React, {useState, useEffect } from 'react';
import ManagersButtons from './components/ManagersButtons';
import WeekSchedule from './components/WeekSchedule';
import OtherWeeks from './components/OtherWeeks';
import CalendarApi from '../api/CalendarApi';

//this will be the main page that an employee will see their schedule. Managers will have buttons that allow them to move to the edit schedule page
//TODO: build out a weekly schedule graphic or find one online
//TODO: add a paginated fetch request that will display the shifts on a schedule
//TODO: add conditionals for managers to see edit schedule links
//TODO: add a pagination that allows users to click other weeks schedules. this will be connected to the controller display weekly
//TODO: add a logout button
function Dashboard () {
    const [data, setData] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/api/calendar/')
      .then(response => response.json())
      .then(json => setData(json))
      .catch(error => console.error(error));
  }, []);
    return (
        <div>
            <p>Main Page</p>
            {data ? <p>{JSON.stringify(data, null, 2)}</p> : <p>Loading...</p>}
            <ManagersButtons/>
            <WeekSchedule />
            <OtherWeeks />
        </div>
    );
}

export default Dashboard;