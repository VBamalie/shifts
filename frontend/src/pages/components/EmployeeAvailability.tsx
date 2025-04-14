import { Typography } from '@mui/material';
import Box from '@mui/material/Box';
import { DataGrid, GridColDef} from '@mui/x-data-grid';
export default function EmployeeAvailability(selectedEmployee: any) {
    const availability = selectedEmployee.selectedEmployee.availability;
  const rows = [{id:0, day:"Sunday", start: availability.sun_start, end: availability.sun_end},{id:1, day:"Monday", start: availability.mon_start, end: availability.mon_end}, {id:2, day:"Tuesday", start: availability.tue_start, end: availability.tue_end}, {id:3, day:"Wednesday", start: availability.wed_start, end: availability.wed_end}, {id:4, day:"Thursday", start: availability.thu_start, end: availability.thu_end}, {id:5, day:"Friday", start: availability.fri_start, end: availability.fri_end}, {id:6, day:"Saturday", start: availability.sat_start, end: availability.sat_end}];

  const columns: GridColDef[] = [
    { field: 'day', headerName: 'Day', width: 150 },
    { field: 'start', headerName: 'Start', width: 150 },
    { field: 'end', headerName: 'End', width: 150 },
  ];
  return (<>
    <Box >
      <Box>
        <Typography variant='h5'>{selectedEmployee.selectedEmployee.firstName} {selectedEmployee.selectedEmployee.lastName}'s Availability</Typography>
        </Box>
    </Box>
    <DataGrid
    columns={columns}
    rows={rows}
    
        sx={{
          '& .MuiDataGrid-row:hover': {
            backgroundColor: 'transparent',
          },
          [`& .course-instructor--cell`]: {
            textAlign: 'center',
            fontWeight: 'bold',
            height:'50vh'
          },
        }}
    />
    </>
  )
}