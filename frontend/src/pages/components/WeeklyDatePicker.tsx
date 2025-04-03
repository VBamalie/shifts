//This is the component to pick which date is viewed. it defaults to selecting this current scheduled week. it has a button to go to the next week and the previous week. if a new week is selected it will update with options to see that weeks previous week and next week. there is also a button that will always return to the current week
import dayjs, { Dayjs } from 'dayjs';
import { ButtonGroup, Button, Typography, Box } from '@mui/material';
import { useState } from 'react';

export default function WeeklyDatePicker(props: any) {//props is the date that is being passed in from the parent component
  const [selectedDate, setSelectedDate] = useState(dayjs().subtract(dayjs().day()).format('MM-DD-YYYY'));//the currently selected week
  const handleSelect = (date: string) => {
    setSelectedDate(date);
    props.onDateUpdate(date);//this will update the date in the parent component
  }
  const selectedWeek = dayjs(selectedDate).subtract(dayjs(selectedDate).day(), 'day').format('MM-DD-YYYY')//this is the first day of the week that is selected
  const nextWeek = dayjs(selectedWeek).add(7, 'day').format('MM-DD-YYYY')//this is the next week of the week that is selected
  const prevWeek = dayjs(selectedWeek).subtract(7, 'day').format('MM-DD-YYYY')//this is the previous week of the week that is selected
  const thisWeek = dayjs().subtract(dayjs().day(), 'day').format('MM-DD-YYYY')//this is the current week
  return (
    <Box className='date-picker'>
        <ButtonGroup variant="text" aria-label="Basic button group" >
          <Button id='prev-week' className='week-picker'
            onClick={() => { handleSelect(prevWeek) }}>{prevWeek}</Button>
          <Button id='next-week' className='week-picker' onClick={() => handleSelect(nextWeek)}>{nextWeek}</Button>
        </ButtonGroup>
        <Box id="date-picker-header" >
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            {selectedWeek}
          </Typography>
          <Button href={`./edit-schedule/${selectedWeek}`}id="edit-schedule-button" variant="contained">Edit Schedule</Button>
        </Box>
        <Box>
          <Button onClick={() => handleSelect(thisWeek)}>{thisWeek}</Button>
        </Box>
    </Box>
  );
}