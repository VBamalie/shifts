import { Typography } from '@mui/material';
import Box from '@mui/material/Box';
import { DataGrid, GridColDef} from '@mui/x-data-grid';
export default function EmployeeAvailability(selectedEmployee: any) {
    const availability = selectedEmployee.selectedEmployee.availability;
  const rows = [{id:0, day:"Sunday", start: availability.sun_start, end: availability.sun_end},{id:1, day:"Monday", start: availability.mon_start, end: availability.mon_end}, {id:2, day:"Tuesday", start: availability.tue_start, end: availability.tue_end}, {id:3, day:"Wednesday", start: availability.wed_start, end: availability.wed_end}, {id:4, day:"Thursday", start: availability.thu_start, end: availability.thu_end}, {id:5, day:"Friday", start: availability.fri_start, end: availability.fri_end}, {id:6, day:"Saturday", start: availability.sat_start, end: availability.sat_end}];

  const columns: GridColDef[] = [
    { field: 'day', headerName: 'Day', headerClassName: 'data-grid-header',width: 75 },
    { field: 'start', headerName: 'Start',headerClassName: 'data-grid-header', width: 75 },
    { field: 'end', headerName: 'End',headerClassName: 'data-grid-header', width: 75 },
  ];
  return (
    <Box id='employee-availability'>
      
        <Typography variant='h5'>{selectedEmployee.selectedEmployee.firstName}'s Availability</Typography>
    <DataGrid
    columns={columns}
    rows={rows}
    hideFooter
    sx={{
        '& .data-grid-header': {
          backgroundColor: 'antiquewhite',
        },
        border: 0
    }}
    />
    </Box>
  )
}