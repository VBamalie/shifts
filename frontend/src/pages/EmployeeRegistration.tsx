/* This is the page to register a new employee. The manager will be doing this */
import axios from 'axios'
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import {
    Typography,
    Button,
    Box,
    Container,
    Grid,
    TextField,
    Card,
    CardHeader,
    CardContent,
    Step,
    StepLabel,
    Stepper
} from '@mui/material';
import axiosInstance from '../axiosConfig';
import { useAuth } from './components/AuthContext';
import { LocalizationProvider, TimePicker } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

export default function Registration() {

    const {employee} = useAuth();
    const [employeeData, setEmployeeData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        isManager: true,
    })
    const [times, setTimes] = useState({
        mon_start: Object,
        mon_end: Object,
        tue_start: Object,
        tue_end: Object,
        wed_start: Object,
        wed_end: Object,
        thu_start: Object,
        thu_end: Object,
        fri_start: Object,
        fri_end: Object,
        sat_start: Object,
        sat_end: Object,
        sun_start: Object,
        sun_end: Object
    });
    const [availabilityData, setAvailabilityData] = useState({
        mon_start: times.mon_start,
        mon_end: times.mon_end,
        tue_start: times.tue_start,
        tue_end: times.tue_end,
        wed_start: times.wed_start,
        wed_end: times.wed_end,
        thu_start: times.thu_start,
        thu_end: times.thu_end,
        fri_start: times.fri_start,
        fri_end: times.fri_end,
        sat_start: times.sat_start,
        sat_end: times.sat_end,
        sun_start: times.sun_start,
        sun_end: times.sun_end
    })

    const [error, setError] = useState('');

   

    const handleBusinessSubmit = async (e: { preventDefault: () => void; }) => {
        e.preventDefault();
        const newEmployeeData: {
            firstName: string,
            lastName: string,
            email: string,
            password: string,
            isManager: boolean
        } = {
            firstName: employeeData.firstName,
            lastName: employeeData.lastName,
            email: employeeData.email,
            password: employeeData.password,
            isManager: true
        };

        
        
            const managerUrl = `http://localhost:8080/api/employee/register/${employee?.calendar}`;
            const managerResponse = await axios.post(managerUrl, employeeData)
            try {
                const managerId = managerResponse.data.id;
                const availabilityUrl = `http://localhost:8080/api/availability/${managerId}`;
                setAvailabilityData(times);
                axiosInstance.post(availabilityUrl, availabilityData);
                window.location.href = '/';
            } catch (error) {
                console.error('Error creating manager:', error);
            }

        } 
    


    function createEmployee() {
        const employeeDataEnum = [{name: 'firstName', label: 'First Name'}, {name: 'lastName', label: 'Last Name'}, {name: 'email', label: 'Email'}, {name: 'password', label: 'Password'}];
        
        return (
            <Card sx={{ marginTop:'3%', marginBottom:'3%', maxWidth: 900, margin: '30x,auto', border: '3px solid #356' }}>
                <CardHeader>
                    Create New Manager
                </CardHeader>
                <CardContent>
                    
                        <Box
                            component="form"
                            onSubmit={handleBusinessSubmit}
                            sx={{ mt: 4, display: 'flex', flexDirection: 'column', alignItems:'center', gap: 2 }}
                        >
                            <Typography variant="h5" component="h1" align="center">
                                Create New Manager
                            </Typography>
                            {error && (
                                <Typography variant="body2" color="error">
                                    {error}
                                </Typography>
                            )}

                            {employeeDataEnum.map((data) => (
                                <TextField
                                    key={data.name}
                                    label={data.label}
                                    name={data.name}
                                    sx={{ width: '200px' }}
                                    value={employeeData[data.name as keyof typeof employeeData]}
                                    onChange={(e) => setEmployeeData({ ...employeeData, [data.name]: e.target.value })}
                                    required
                                    
                                    variant="outlined"
                                />
                            ))}
                        </Box>
                    
                </CardContent>
            </Card>
        )
    }
    function createAvailabilty(){
        const availabilityDataEnum=[{name: 'mon_start', label: 'Monday Start '}, {name: 'mon_end', label: 'Monday End '}, {name: 'tue_start', label: 'Tuesday Start '}, {name: 'tue_end', label: 'Tuesday End '}, {name: 'wed_start', label: 'Wednesday Start '}, {name: 'wed_end', label: 'Wednesday End '}, {name: 'thu_start', label: 'Thursday Start '}, {name: 'thu_end', label: 'Thursday End '}, {name: 'fri_start', label: 'Friday Start '}, {name: 'fri_end', label: 'Friday End '}, {name: 'sat_start', label: 'Saturday Start '}, {name: 'sat_end', label: 'Saturday End '}, {name: 'sun_start', label: 'Sunday Start '}, {name: 'sun_end', label: 'Sunday End '}];
        
        const [error, setError] = useState('');
       
        return (
            <Card sx={{ marginTop:'3%', marginBottom:'3%', maxWidth: 900, margin: '30x,auto', border: '3px solid #356' }}>
                <CardHeader>
                    Create Availability
                </CardHeader>
                <CardContent>
                        <Box
                        component="form"
                        onSubmit={handleBusinessSubmit}
                        sx={{ mt: 4, display: 'flex', flexDirection: 'column', alignItems:'center', gap: 2 }}
                        >
                            <Typography variant="h5" component="h1" align="center">
                                Create Manager Availability
                            </Typography>
                            {error && (
                                <Typography variant="body2" color="error">
                                    {error}
                                </Typography>
                            )}
                            <Box className="time-picker-container" sx={{ mt: 4, display: 'flex', flexDirection: 'row', alignItems:'center', gap: 2, width:'500px', flexWrap: 'wrap' }}>
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                            {availabilityDataEnum.map((data) => (
                                <TimePicker
                                views={['hours']}
                                sx={{width: '200px'}}
                                    key={data.name}
                                    label={data.label}
                                    name={data.name}
                                    onChange={(value) => setTimes({ ...times, [data.name]: value ? value?.hour() : null })}
                                />
                            ))}                      
                             </LocalizationProvider>
                             </Box>
                        </Box>
                </CardContent>
            </Card>
        )
    } 

    const headerSteps = ['Create Employee', 'Create Employee Availability'];
    const formSteps = [createEmployee(), createAvailabilty()];
    const [activeStep, setActiveStep] = React.useState(0);
    const handleNext = () => {
        setActiveStep((prevActiveStep: number) => prevActiveStep + 1);
    };
    const handleBack = () => {
        setActiveStep((prevActiveStep: number) => prevActiveStep - 1);
    };
    const handleReset = () => {
        setActiveStep(0);
    };
    
    return (
        <>
            <Box className="active-steps">
                {formSteps[activeStep]}
            </Box>
            <Box  sx={{ width: '100%' }}>
                <Stepper  activeStep={activeStep}>
                    {headerSteps.map((label, index) => {
                        const stepProps: { completed?: boolean } = {};
                        const labelProps: {
                        } = {};

                        return (
                            <Step key={label} {...stepProps}>
                                <StepLabel {...labelProps}>{label}</StepLabel>
                            </Step>
                        );
                    })}
                </Stepper>
                {activeStep === headerSteps.length ? (
                    <Box className="finished-stepper">
                        <Button onClick={handleBusinessSubmit}>Submit New Employee</Button>
                        <Typography sx={{ mt: 2, mb: 1 }}>
                            All steps completed - you&apos;re finished
                        </Typography>
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Box sx={{ flex: '1 1 auto' }} />
                            <Button onClick={handleReset}>Reset</Button>
                        </Box>
                    </Box>
                ) : (
                    <React.Fragment>
                        <Typography sx={{ mt: 2, mb: 1 }}>Step {activeStep + 1}</Typography>
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Button
                                color="inherit"
                                disabled={activeStep === 0}
                                onClick={handleBack}
                                sx={{ mr: 1 }}
                            >
                                Back
                            </Button>
                            <Box sx={{ flex: '1 1 auto' }} />
                            <Button onClick={handleNext}>
                                {activeStep === headerSteps.length - 1 ? 'Finish' : 'Next'}
                            </Button>
                        </Box>
                    </React.Fragment>
                )}
            </Box>
        </>
    );
}

