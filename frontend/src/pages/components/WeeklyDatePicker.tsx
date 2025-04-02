import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs';


import { ButtonGroup, Button } from '@mui/material';
import { useState } from 'react';

export default function WeeklyDatePicker(props: any) {
    const [selectedDate, setSelectedDate] = useState(dayjs().subtract(dayjs().day()).format('MM-DD-YYYY'));

    const handleSelect = (date: string) => {
        setSelectedDate(date);
        console.log("this week:"+ dayjs().subtract(dayjs().day()).format('MM-DD-YYYY'));
        props.onDateUpdate(date);
    }
    const selectedWeek = dayjs(selectedDate).subtract(dayjs(selectedDate).day(), 'day').format('MM-DD-YYYY')
    const nextWeek = dayjs(selectedWeek).add(7, 'day').format('MM-DD-YYYY')
    const prevWeek = dayjs(selectedWeek).subtract(7, 'day').format('MM-DD-YYYY')
    const thisWeek = dayjs().subtract(dayjs().day(), 'day').format('YYYY-MM-DD')
  return (
    <>
    <ButtonGroup variant="text" aria-label="Basic button group" orientation='vertical'>
  <Button
  onClick={()=>{handleSelect(prevWeek)}}>{prevWeek}</Button>
  <Button onClick={()=>handleSelect(selectedWeek)}>{selectedWeek}</Button>
  <Button onClick={()=>handleSelect(nextWeek)}>{nextWeek}</Button>
  <Button>This Week: </Button>
  <Button onClick={()=>handleSelect(thisWeek)}>{thisWeek}</Button>
</ButtonGroup>

    </>
  );
}