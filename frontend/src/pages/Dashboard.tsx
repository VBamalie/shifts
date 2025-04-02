import React, {useState, useEffect } from 'react';
import Navbar from './components/Navbar';
import './components/dashboard.css';


import EmployeeList from './components/EmployeeList';
import WeekSchedule from './components/WeekSchedule';
import WeeklyDatePicker from './components/WeeklyDatePicker';
import dayjs from 'dayjs';


//this will be the main page that an employee will see their schedule. Managers will have buttons that allow them to move to the edit schedule page
//TODO: build out a weekly schedule graphic or find one online
//TODO: add conditionals for managers to see edit schedule links
//TODO: add a pagination that allows users to click other weeks schedules. this will be connected to the controller display weekly
function Dashboard () {
    const [selectedDate, setSelectedDate] = useState(dayjs().subtract(dayjs().day(), 'day').format('MM-DD-YYYY'));

    
    const updateDate = (date:string)=>{
        setSelectedDate(date);
    }
    return (
        <div>
            <Navbar />
            <h1>Dashboard</h1>
            <WeeklyDatePicker onDateUpdate={updateDate} selectedDate={selectedDate}/>
            <WeekSchedule date={selectedDate} />
            <EmployeeList/>

        </div>
    );
}
export default Dashboard;