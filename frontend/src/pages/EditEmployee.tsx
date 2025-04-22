import React, { useState, useEffect } from 'react';
import {
    Typography,
    Button,
    Box,
    TextField,
    Card,
    CardHeader,
    CardContent,
    Grid,
    Container,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle
} from '@mui/material';
import { LocalizationProvider, TimePicker } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import axiosInstance from '../axiosConfig';
import { useAuth } from './components/AuthContext';
import EmployeeList from './components/EmployeeList';
import dayjs from 'dayjs';

export default function EditEmployee() {
    const { employee } = useAuth();
    const [selectedEmployee, setSelectedEmployee] = useState<any>(null);
    const [employeeData, setEmployeeData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        isManager: false,
    });
    const [times, setTimes] = useState({
        mon_start: null,
        mon_end: null,
        tue_start: null,
        tue_end: null,
        wed_start: null,
        wed_end: null,
        thu_start: null,
        thu_end: null,
        fri_start: null,
        fri_end: null,
        sat_start: null,
        sat_end: null,
        sun_start: null,
        sun_end: null
    });
    const [availability, setAvailability] = useState<any>(null);
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const [openDeleteDialog, setOpenDeleteDialog] = useState(false);

    // When an employee is selected from the list
    const handleEmployeeSelection = (emp: any) => {
        setSelectedEmployee(emp);
        if (emp) {
            setEmployeeData({
                firstName: emp.firstName || '',
                lastName: emp.lastName || '',
                email: emp.email || '',
                password: '', // Don't populate password for security reasons
                isManager: emp.isManager || false,
            });
            
            // Fetch availability for the selected employee
            fetchEmployeeAvailability(emp.id);
        } else {
            setAvailability(null);
        }
    };

    const fetchEmployeeAvailability = async (employeeId: number) => {
        try {
            const response = await axiosInstance.get(`http://localhost:8080/api/availability/${employeeId}`);
            setAvailability(response.data);
            
            // Convert availability data to times state
            const availabilityData = response.data;
            if (availabilityData) {
                setTimes({
                    mon_start: availabilityData.mon_start,
                    mon_end: availabilityData.mon_end,
                    tue_start: availabilityData.tue_start,
                    tue_end: availabilityData.tue_end,
                    wed_start: availabilityData.wed_start,
                    wed_end: availabilityData.wed_end,
                    thu_start: availabilityData.thu_start,
                    thu_end: availabilityData.thu_end,
                    fri_start: availabilityData.fri_start,
                    fri_end: availabilityData.fri_end,
                    sat_start: availabilityData.sat_start,
                    sat_end: availabilityData.sat_end,
                    sun_start: availabilityData.sun_start,
                    sun_end: availabilityData.sun_end
                });
            }
        } catch (error) {
            console.error('Error fetching employee availability:', error);
            setError('Failed to fetch employee availability');
        }
    };

    const handleUpdateEmployee = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!selectedEmployee) return;

        try {
            // Update employee information
            const updatedEmployee = {
                ...selectedEmployee,
                firstName: employeeData.firstName,
                lastName: employeeData.lastName,
                email: employeeData.email,
                isManager: employeeData.isManager
            };

            // Only include password if it was changed
            if (employeeData.password) {
                updatedEmployee.password = employeeData.password;
            }

            await axiosInstance.put(`http://localhost:8080/api/employee/${selectedEmployee.id}`, updatedEmployee);
            
            // Update availability
            if (availability) {
                const updatedAvailability = {
                    ...availability,
                    ...times
                };
                await axiosInstance.put(`http://localhost:8080/api/availability/${selectedEmployee.id}`, updatedAvailability);
            }

            setSuccessMessage('Employee updated successfully');
            setTimeout(() => setSuccessMessage(''), 3000);
        } catch (error) {
            console.error('Error updating employee:', error);
            setError('Failed to update employee');
        }
    };

    const handleDeleteEmployee = async () => {
        if (!selectedEmployee) return;

        try {
            await axiosInstance.delete(`http://localhost:8080/api/employee/${selectedEmployee.id}`);
            setOpenDeleteDialog(false);
            setSelectedEmployee(null);
            setEmployeeData({
                firstName: '',
                lastName: '',
                email: '',
                password: '',
                isManager: false,
            });
            setAvailability(null);
            setSuccessMessage('Employee deleted successfully');
            setTimeout(() => setSuccessMessage(''), 3000);
            
            // Refresh the employee list
            window.location.reload();
        } catch (error) {
            console.error('Error deleting employee:', error);
            setError('Failed to delete employee');
            setOpenDeleteDialog(false);
        }
    };

    return (
        <Container maxWidth="lg">
            <Typography variant="h4" component="h1" gutterBottom sx={{ mt: 4 }}>
                Edit Employee
            </Typography>
            
            {successMessage && (
                <Box sx={{ mb: 2, p: 2, bgcolor: 'success.light', borderRadius: 1 }}>
                    <Typography>{successMessage}</Typography>
                </Box>
            )}
            
            {error && (
                <Box sx={{ mb: 2, p: 2, bgcolor: 'error.light', borderRadius: 1 }}>
                    <Typography color="error">{error}</Typography>
                </Box>
            )}
            
            <Grid container spacing={3} sx={{height: '100%'}}>
                <Grid item xs={12} md={4}>
                    <Card >
                        <CardHeader title="Select Employee" />
                        <CardContent >
                            <EmployeeList onEmployeeSelection={handleEmployeeSelection} />
                        </CardContent>
                    </Card>
                </Grid>
                
                <Grid item xs={12} md={8}>
                    {selectedEmployee ? (
                        <>
                            <Card sx={{ mb: 3, border: '1px solid #ccc' }}>
                                <CardHeader title="Employee Information" />
                                <CardContent>
                                    <Box
                                        component="form"
                                        sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}
                                    >
                                        <TextField
                                            label="First Name"
                                            value={employeeData.firstName}
                                            onChange={(e) => setEmployeeData({ ...employeeData, firstName: e.target.value })}
                                            fullWidth
                                            required
                                        />
                                        <TextField
                                            label="Last Name"
                                            value={employeeData.lastName}
                                            onChange={(e) => setEmployeeData({ ...employeeData, lastName: e.target.value })}
                                            fullWidth
                                            required
                                        />
                                        <TextField
                                            label="Email"
                                            value={employeeData.email}
                                            onChange={(e) => setEmployeeData({ ...employeeData, email: e.target.value })}
                                            fullWidth
                                            required
                                        />
                                        <TextField
                                            label="New Password (leave blank to keep current)"
                                            type="password"
                                            value={employeeData.password}
                                            onChange={(e) => setEmployeeData({ ...employeeData, password: e.target.value })}
                                            fullWidth
                                        />
                                    </Box>
                                </CardContent>
                            </Card>
                            
                            <Card sx={{ mb: 3, border: '1px solid #ccc' }}>
                                <CardHeader title="Availability" />
                                <CardContent>
                                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                                        <Grid container spacing={2}>
                                            {Object.entries(times).map(([key, value]) => {
                                                const day = key.split('_')[0];
                                                const type = key.split('_')[1];
                                                const label = `${day.charAt(0).toUpperCase() + day.slice(1)} ${type}`;
                                                
                                                return (
                                                    <Grid item xs={12} sm={6} key={key}>
                                                        <TimePicker
                                                            label={label}
                                                            value={value !== null ? dayjs().hour(value).minute(0) : null}
                                                            onChange={(newValue) => {
                                                                setTimes({
                                                                    ...times,
                                                                    [key]: newValue ? newValue.hour() : null
                                                                });
                                                            }}
                                                            views={['hours']}
                                                            sx={{ width: '100%' }}
                                                        />
                                                    </Grid>
                                                );
                                            })}
                                        </Grid>
                                    </LocalizationProvider>
                                </CardContent>
                            </Card>
                            
                            <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 2 }}>
                                <Button 
                                    variant="contained" 
                                    color="primary" 
                                    onClick={handleUpdateEmployee}
                                >
                                    Update Employee
                                </Button>
                                <Button 
                                    variant="contained" 
                                    color="error" 
                                    onClick={() => setOpenDeleteDialog(true)}
                                >
                                    Delete Employee
                                </Button>
                            </Box>
                            
                            <Dialog
                                open={openDeleteDialog}
                                onClose={() => setOpenDeleteDialog(false)}
                            >
                                <DialogTitle>Confirm Delete</DialogTitle>
                                <DialogContent>
                                    <DialogContentText>
                                        Are you sure you want to delete {employeeData.firstName} {employeeData.lastName}? This action cannot be undone.
                                    </DialogContentText>
                                </DialogContent>
                                <DialogActions>
                                    <Button onClick={() => setOpenDeleteDialog(false)} color="primary">
                                        Cancel
                                    </Button>
                                    <Button onClick={handleDeleteEmployee} color="error">
                                        Delete
                                    </Button>
                                </DialogActions>
                            </Dialog>
                        </>
                    ) : (
                        <Card>
                            <CardContent>
                                <Typography variant="body1" align="center">
                                    Please select an employee from the list to edit.
                                </Typography>
                            </CardContent>
                        </Card>
                    )}
                </Grid>
            </Grid>
        </Container>
    );
}