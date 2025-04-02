import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs';


import { ButtonGroup, Button } from '@mui/material';
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
    <div className='datePicker'>
    <ButtonGroup variant="text" aria-label="Basic button group" >
  <Button id='prevWeek' className='weekpicker'
  onClick={()=>{handleSelect(prevWeek)}}>{prevWeek}</Button>
  <Button  id='selected-week' className='weekpicker' onClick={()=>handleSelect(selectedWeek)}>{selectedWeek}</Button>
  <Button id='prevWeek' className='weekpicker' onClick={()=>handleSelect(nextWeek)}>{nextWeek}</Button>
  
</ButtonGroup>
<div>
<Button onClick={()=>handleSelect(thisWeek)}>{thisWeek}</Button>
</div>

    </div>
  );
}