//This will be where all the options that are only available if the employee that is logged in is a manager. otherwise this section displays a blank space.

import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { useAuth } from './AuthContext';
import axiosInstance from '../../axiosConfig';
import { useEffect, useState } from 'react';
import { ButtonGroup } from '@mui/material';

const Navbar = () => {
    const { employee, logout } = useAuth();
    const [businessName, setBusinessName]= useState('');
    useEffect(() => {
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
            {employee ?(
                <>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    Shifts App</Typography>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    {businessName}
                    </Typography>
                    <ButtonGroup variant="contained" aria-label="Basic button group">
                        <Button>New Employee</Button>
                        <Button>Edit Employee</Button>
                    </ButtonGroup>
                    <Button onClick={logout} variant="contained" color="secondary">
                    Logout
                    </Button>
                </>
            )
            :(
            <Button href='/login' color="inherit">Login</Button>
            )
        }
            
          </Toolbar>
        </AppBar>
      </Box>
        
    );
};

export default Navbar;