import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs';


import { ButtonGroup, Button, Typography, Box } from '@mui/material';
import { useState } from 'react';

export default function WeeklyDatePicker(props: any) {
  const [selectedDate, setSelectedDate] = useState(dayjs().subtract(dayjs().day()).format('MM-DD-YYYY'));

  const handleSelect = (date: string) => {
    setSelectedDate(date);
    props.onDateUpdate(date);
  }
  const selectedWeek = dayjs(selectedDate).subtract(dayjs(selectedDate).day(), 'day').format('MM-DD-YYYY')
  const nextWeek = dayjs(selectedWeek).add(7, 'day').format('MM-DD-YYYY')
  const prevWeek = dayjs(selectedWeek).subtract(7, 'day').format('MM-DD-YYYY')
  const thisWeek = dayjs().subtract(dayjs().day(), 'day').format('MM-DD-YYYY')
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
          <Button href="#"id="edit-schedule-button" variant="contained">Edit Schedule</Button>
        </Box>
        <Box>
          <Button onClick={() => handleSelect(thisWeek)}>{thisWeek}</Button>
        </Box>
    </Box>
  );
}