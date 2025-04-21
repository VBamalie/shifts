//This is the navbar. for managers it displays buttons to edit an employee, create a new employee, and edit the business's timeblocks. for employees it displays the company name and a logout button.
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { useAuth } from './AuthContext';
import axiosInstance from '../../axiosConfig';
import { useEffect, useState } from 'react';
import { ButtonGroup } from '@mui/material';

const Navbar = () => {
    const { employee, logout } = useAuth();//gets the employee profile from authcontext and allows logout function
    const [businessName, setBusinessName]= useState('');
    
    useEffect(() => {//this grabs the current employee's business name
        const calendarId:number = employee?.calendar;
        axiosInstance.get(`http://localhost:8080/api/calendar/${calendarId}`).then((response) => {
            setBusinessName(response.data.businessName);
        }).catch((error) => {
          console.log("error fetching employees", error);
        });
      }, []);


    return (
        <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
            {employee ?(//if the employee is logged in then this will display the business name and the logout button
                <>
                <Box  sx={{  backgroundColor: 'purple', borderRadius: '15px', padding: '5px', marginRight: '10px', marginTop: '10px', marginBottom: '10px' }}>
                  <Button href='/'>
                    <img src="/shift.png" alt="Shift Logo" width="50" height="50" />
                  </Button>
                </Box>
                <Button color="inherit" href="/">Shifts App</Button>
                  <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    {businessName}
                  </Typography>
                    {employee.isManager &&(
                  <ButtonGroup variant="contained" aria-label="Basic button group">
                        <Button href='/new-employee'>New Employee</Button>
                        <Button>Edit Employee</Button>
                        <Button href='/edit-time-block'>Edit Timeblocks</Button>
                  </ButtonGroup>)}
                    
                  <Button onClick={logout} variant="contained" color="secondary">
                    Logout
                  </Button>
                </>
            ):(//if the employee is not logged in then this will display the login button
            <Button href='/login' color="inherit">Login</Button>
            )}  
          </Toolbar>
        </AppBar>
      </Box>
    );
};

export default Navbar;