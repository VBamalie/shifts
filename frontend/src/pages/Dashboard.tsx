import React, {useState, useEffect } from 'react';
import Navbar from './components/navbar';

import EmployeeList from './components/EmployeeList';
import WeekSchedule from './components/WeekSchedule';

//this will be the main page that an employee will see their schedule. Managers will have buttons that allow them to move to the edit schedule page
//TODO: build out a weekly schedule graphic or find one online
//TODO: add a paginated fetch request that will display the shifts on a schedule
//TODO: add conditionals for managers to see edit schedule links
//TODO: add a pagination that allows users to click other weeks schedules. this will be connected to the controller display weekly
function Dashboard () {
    return (
        <div>
            <Navbar />
            <WeekSchedule date={"01-01-20202"} />
            <EmployeeList/>

        </div>
    );
}
export default Dashboard;