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
        mon_start: '',
        mon_end: '',
        tue_start: '',
        tue_end: '',
        wed_start: '',
        wed_end: '',
        thu_start: '',
        thu_end: '',
        fri_start: '',
        fri_end: '',
        sat_start: '',
        sat_end: '',
        sun_start: '',
        sun_end: ''

    })

    const [error, setError] = useState('');
    const [newTimeBlockData, setNewTimeBlockData] = useState({
        startTime: "",
        endTime: '',
        shiftsRequired: "",
        weekDayEnum: ""
    })

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



        const availabilityData: {
            mon_start: number,
            mon_end: number,
            tue_start: number,
            tue_end: number,
            wed_start: number,
            wed_end: number,
            thu_start: number,
            thu_end: number,
            fri_start: number,
            fri_end: number,
            sat_start: number,
            sat_end: number,
            sun_start: number,
            sun_end: number
        } = {
            mon_start: parseInt(newManagerData.mon_start),
            mon_end: parseInt(newManagerData.mon_end),
            tue_start: parseInt(newManagerData.tue_start),
            tue_end: parseInt(newManagerData.tue_end),
            wed_start: parseInt(newManagerData.wed_start),
            wed_end: parseInt(newManagerData.wed_end),
            thu_start: parseInt(newManagerData.thu_start),
            thu_end: parseInt(newManagerData.thu_end),
            fri_start: parseInt(newManagerData.fri_start),
            fri_end: parseInt(newManagerData.fri_end),
            sat_start: parseInt(newManagerData.sat_start),
            sat_end: parseInt(newManagerData.sat_end),
            sun_start: parseInt(newManagerData.sun_start),
            sun_end: parseInt(newManagerData.sun_end)
        }
        const response = await axios.post('http://localhost:8080/api/calendar/', newBusinessData);
        try {
            const businessId = response.data.id;
            const managerUrl = `http://localhost:8080/api/employee/register/${businessId}`;
            const managerResponse = await axios.post(managerUrl, employeeData)
            try {
                const managerId = managerResponse.data.id;
                const availabilityUrl = `http://localhost:8080/api/availability/${managerId}`;
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
        const [error, setError] = useState('');
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
                            <TextField
                                label="First Name"
                                name="firstName"
                                value={newManagerData.firstName}
                                onChange={(e) => setNewManagerData({ ...newManagerData, firstName: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Last Name"
                                name="lastName"
                                value={newManagerData.lastName}
                                onChange={(e) => setNewManagerData({ ...newManagerData, lastName: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Email"
                                name="email"
                                value={newManagerData.email}
                                onChange={(e) => setNewManagerData({ ...newManagerData, email: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Password"
                                name="password"
                                value={
                                    newManagerData.password
                                }
                                onChange={(e) => setNewManagerData({ ...newManagerData, password: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Monday Start Time"
                                name="mon_start"
                                value={newManagerData.mon_start}
                                onChange={(e) => setNewManagerData({ ...newManagerData, mon_start: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Monday End Time"
                                name="mon_end"
                                value={newManagerData.mon_end}
                                onChange={(e) => setNewManagerData({ ...newManagerData, mon_end: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Tuesday Start Time"
                                name="tue_start"
                                value={newManagerData.tue_start}
                                onChange={(e) => setNewManagerData({
                                    ...
                                    newManagerData, tue_start: e.target.value
                                })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Tuesday End Time"
                                name="tue_end"
                                value={newManagerData.tue_end}
                                onChange={(e) => setNewManagerData({ ...newManagerData, tue_end: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Wednesday Start Time"
                                name="wed_start"
                                value={newManagerData.wed_start}
                                onChange={(e) => setNewManagerData({ ...newManagerData, wed_start: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Wednesday End Time"
                                name="wed_end"
                                value={newManagerData.wed_end}
                                onChange={(e) => setNewManagerData({ ...newManagerData, wed_end: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Thursday
                            Start Time"
                                name="thu_start"
                                value={newManagerData.thu_start}
                                onChange={(e) => setNewManagerData({ ...newManagerData, thu_start: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Thursday End Time"
                                name="thu_end"
                                value={newManagerData.thu_end}
                                onChange={(e) => setNewManagerData({ ...newManagerData, thu_end: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Friday Start Time"
                                name="fri_start"
                                value={newManagerData.fri_start}
                                onChange={(e) => setNewManagerData({ ...newManagerData, fri_start: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Friday End Time"
                                name="fri_end"
                                value={newManagerData.fri_end}
                                onChange={(e) => setNewManagerData({ ...newManagerData, fri_end: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Saturday Start Time"
                                name="sat_start"
                                value={newManagerData.sat_start}
                                onChange={(e) => setNewManagerData({ ...newManagerData, sat_start: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Saturday End Time"
                                name="sat_end"
                                value={newManagerData.sat_end}
                                onChange={(e) => setNewManagerData({ ...newManagerData, sat_end: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Sunday Start Time"
                                name="sun_start"
                                value={newManagerData.sun_start}
                                onChange={(e) => setNewManagerData({ ...newManagerData, sun_start: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Sunday End Time"
                                name="sun_end"
                                value={newManagerData.sun_end}
                                onChange={(e) => setNewManagerData({
                                    ...
                                    newManagerData, sun_end: e.target.value
                                })}
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

    function createTimeBlocks() {
        return (
            <Card>
                <CardContent>
                    <Container maxWidth="sm">
                        <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                            <Typography variant="h6" gutterBottom>
                                Create Time Blocks
                            </Typography>
                            <TextField
                                label="Start Time"
                                name="startTime"
                                value={newTimeBlockData.startTime}
                                onChange={(e) => setNewTimeBlockData({ ...newTimeBlockData, startTime: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="End Time"
                                name="endTime"
                                value={newTimeBlockData.endTime}
                                onChange={(e) => setNewTimeBlockData({ ...newTimeBlockData, endTime: e.target.value })}
                                required
                                fullWidth
                                variant="outlined"
                            />
                            <TextField
                                label="Shifts Required"
                                name="shiftsRequired"
                                value={newTimeBlockData.shiftsRequired}
                                onChange={(e) => setNewTimeBlockData({ ...newTimeBlockData, shiftsRequired: e.target.value })}
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

    const headerSteps = ['Create New Business', 'Create a Manager', 'Create Time Blocks'];
    const formSteps = [createNewBusiness(), createManager(), createTimeBlocks()];
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
    const handleSubmit = () => {
        console.log(newManagerData);
        console.log(newBusinessData);
        console.log(newTimeBlockData);
        // Add your form submission logic here
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
