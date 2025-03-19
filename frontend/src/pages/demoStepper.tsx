import { useState } from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { Card, CardContent, CardHeader, Container, TextField } from '@mui/material';
import React from 'react';
export default function HorizontalLinearStepper() {


    const handleBusinesssubmit = async (e: { preventDefault: () => void; }) => {
        e.preventDefault();
    }


    function createNewBusiness() {
        const [error, setError] = useState('');
        const [newBusinessData, setNewBusinessData] = useState({
            businessName: ''
        })
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
        const [error, setError] = useState('');
        const [newTimeBlockData, setNewTimeBlockData] = useState({
            startTime: "",
            endTime: '',
            shiftsRequired: "",
            weekDayEnum: ""
        })
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
