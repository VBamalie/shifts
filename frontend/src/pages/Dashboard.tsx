//this will be the main page that an employee will see their schedule. Managers will have buttons that allow them to move to add employees, edit employees, and edit shifts
import React, {useState, useEffect } from 'react';
import './components/dashboard.css';
import WeekSchedule from './components/WeekSchedule';
import WeeklyDatePicker from './components/WeeklyDatePicker';
import dayjs from 'dayjs';
//TODO: build out a weekly schedule graphic or find one online
function Dashboard () {
    const [selectedDate, setSelectedDate] = useState(dayjs().subtract(dayjs().day(), 'day').format('MM-DD-YYYY'));//the default week that is displayed is the current scheduled week
    const updateDate = (date:string)=>{
        setSelectedDate(date);
    }
    return (
        <div id='dashboard'>
            <WeeklyDatePicker onDateUpdate={updateDate} selectedDate={selectedDate}/>
            <WeekSchedule date={selectedDate} />
        </div>
    );
}
export default Dashboard;