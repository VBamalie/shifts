import { useState } from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Card, CardContent, CardHeader, Container, TextField } from '@mui/material';
import React from 'react';
import axiosInstance from '../axiosConfig';
import axios from 'axios';
import { useAuth } from './components/AuthContext';
import { LocalizationProvider, StaticTimePicker, TimePicker } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';


export default function HorizontalLinearStepper() {
        const {login} = useAuth();
    const [newBusinessData, setNewBusinessData] = useState({
        businessName: ''
    })
    const [newManagerData, setNewManagerData] = useState({
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

    const handleBusinesssubmit = async (e: { preventDefault: () => void; }) => {
        e.preventDefault();
        const employeeData: {
            firstName: string,
            lastName: string,
            email: string,
            password: string,
            isManager: boolean
        } = {
            firstName: newManagerData.firstName,
            lastName: newManagerData.lastName,
            email: newManagerData.email,
            password: newManagerData.password,
            isManager: true
        };

        const response = await axios.post('http://localhost:8080/api/calendar/', newBusinessData);
        try {
            const businessId = response.data.id;
            const managerUrl = `http://localhost:8080/api/employee/register/${businessId}`;
            const managerResponse = await axios.post(managerUrl, employeeData)
            try {
                const managerId = managerResponse.data.id;
                const availabilityUrl = `http://localhost:8080/api/availability/${managerId}`;
                setAvailabilityData(times);
                console.log(availabilityData);
                const availabilityResponse = await axiosInstance.post(availabilityUrl, availabilityData);
                if (response.status === 200 || response.status === 201) {
                    const loginData = {
                        email: newManagerData.email,
                        password: newManagerData.password
                    };
                    const response = await axios.post('http://localhost:8080/api/employee/login', loginData);
                    if (response.status === 200) {
                        const employeeData = response.data;
                        login(employeeData);
                        window.location.reload();
                        window.location.href = '/';
                    } else {
                        setError(response.data.message || 'Login failed for user. Please retry!')
                    }
                } else {
                    console.error(response.data.message || 'An error occurred in availability');
                }
            } catch (error) {
                console.error('Error creating manager:', error);
            }

        } catch (error) {
            console.error('Error creating business:', error);
        }
    }


    function createNewBusiness() {
        
        const [error, setError] = useState('');
        return (
            <Card sx={{ maxWidth: 900, margin: '30x,auto', border: '3px solid #356' }}>
                <CardHeader>
                    Create New Business
                </CardHeader>
                <CardContent>
                    <Container maxWidth="xs">
                        <Box
                            component="form"
                            onSubmit={handleBusinesssubmit}
                            sx={{ mt: 4, display: 'flex', flexDirection: 'column', gap: 2 }}
                        >
                            <Typography variant="h5" component="h1" align="center">
                                Create New Business
                            </Typography>
                            {error && (
                                <Typography variant="body2" color="error">
                                    {error}
                                </Typography>
                            )}
                            <TextField
                                label="Business Name"
                                name="businessName"
                                value={newBusinessData.businessName}
                                onChange={(e) => setNewBusinessData({ ...newBusinessData, businessName: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                        </Box>
                    </Container>
                </CardContent>
            </Card>
        )
    }

    function createManager() {
        const ManagerDataEnum = [{name: 'firstName', label: 'First Name'}, {name: 'lastName', label: 'Last Name'}, {name: 'email', label: 'Email'}, {name: 'password', label: 'Password'}];
        
        return (
            <Card sx={{ maxWidth: 900, margin: '30x,auto', border: '3px solid #356' }}>
                <CardHeader>
                    Create New Manager
                </CardHeader>
                <CardContent>
                    <Container maxWidth="xs">
                        <Box
                            component="form"
                            onSubmit={handleBusinesssubmit}
                            sx={{ mt: 4, display: 'flex', flexDirection: 'column', gap: 2 }}
                        >
                            <Typography variant="h5" component="h1" align="center">
                                Create New Manager
                            </Typography>
                            {error && (
                                <Typography variant="body2" color="error">
                                    {error}
                                </Typography>
                            )}

                            {ManagerDataEnum.map((data) => (
                                <TextField
                                    key={data.name}
                                    label={data.label}
                                    name={data.name}
                                    value={newManagerData[data.name as keyof typeof newManagerData]}
                                    onChange={(e) => setNewManagerData({ ...newManagerData, [data.name]: e.target.value })}
                                    required
                                    fullWidth
                                    variant="outlined"
                                />
                            ))}
                        </Box>
                    </Container>
                </CardContent>
            </Card>
        )
    }
    function createAvailabilty(){
        const availabilityDataEnum=[{name: 'mon_start', label: 'Monday Start Time'}, {name: 'mon_end', label: 'Monday End Time'}, {name: 'tue_start', label: 'Tuesday Start Time'}, {name: 'tue_end', label: 'Tuesday End Time'}, {name: 'wed_start', label: 'Wednesday Start Time'}, {name: 'wed_end', label: 'Wednesday End Time'}, {name: 'thu_start', label: 'Thursday Start Time'}, {name: 'thu_end', label: 'Thursday End Time'}, {name: 'fri_start', label: 'Friday Start Time'}, {name: 'fri_end', label: 'Friday End Time'}, {name: 'sat_start', label: 'Saturday Start Time'}, {name: 'sat_end', label: 'Saturday End Time'}, {name: 'sun_start', label: 'Sunday Start Time'}, {name: 'sun_end', label: 'Sunday End Time'}];
        
        const [error, setError] = useState('');
       
        return (
            <Card>
                <CardHeader>
                    Create Availability
                </CardHeader>
                <CardContent>
                    <Container maxWidth="xs">
                        <Box
                        component="form"
                        onSubmit={handleBusinesssubmit}
                        sx={{ mt: 4, display: 'flex', flexDirection: 'column', gap: 2 }}
                        >
                            <Typography variant="h5" component="h1" align="center">
                                Create Manager Availability
                            </Typography>
                            {error && (
                                <Typography variant="body2" color="error">
                                    {error}
                                </Typography>
                            )}
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                            {availabilityDataEnum.map((data) => (
                                <TimePicker
                                views={['hours']}
                                sx={{width: '75%'}}
                                    key={data.name}
                                    label={data.label}
                                    name={data.name}
                                    onChange={(value) => setTimes({ ...times, [data.name]: value ? value?.hour() : null })}
                                />
                            ))}                      
                             </LocalizationProvider>
                        </Box>
                    </Container>
                </CardContent>
            </Card>
        )
    } 

    const headerSteps = ['Create New Business', 'Create a Manager', 'Create Manager Availability'];
    const formSteps = [createNewBusiness(), createManager(), createAvailabilty()];
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
            <Box>
                {formSteps[activeStep]}
            </Box>
            <Box sx={{ width: '100%' }}>
                <Stepper activeStep={activeStep}>
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
                    <React.Fragment>
                        <Button onClick={handleBusinesssubmit}>Submit Business</Button>
                        <Typography sx={{ mt: 2, mb: 1 }}>
                            All steps completed - you&apos;re finished
                        </Typography>
                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                            <Box sx={{ flex: '1 1 auto' }} />
                            <Button onClick={handleReset}>Reset</Button>
                        </Box>
                    </React.Fragment>
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
