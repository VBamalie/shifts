import axios from 'axios'
import React, { useState } from 'react'
import { redirect, useNavigate } from 'react-router-dom'
import {
    Typography,
    Button,
    Box,
    Container,
    Grid,
    TextField,
    Card,
    CardHeader,
    CardContent
} from '@mui/material';
import axiosInstance from '../axiosConfig';
//TODO: make this page look better
//TODO: add context where the manager's calendar is the one that the employee is registering for
/* This is the page to register a new employee. The manager will be doing this */
export default function Registration() {

    const [formData, setFormData] = useState({//these are the fields that will be used to create a new employee and their availability
        firstName: '',
        lastName: '',
        email: '',
        password: '',
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

    const [error, setError] = useState('')
    const navigate = useNavigate()

    const handleChange = (e: { target: { name: any; value: any; }; }) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e: { preventDefault: () => void; }) => {
        e.preventDefault();
        const employeeData: { 
            firstName:string, 
            lastName:string, 
            email:string, 
            password:string } = {
                firstName:formData.firstName, 
                lastName:formData.lastName, 
                email:formData.email, 
                password:formData.password};

                
        const availabilityData: {
            mon_start:number, 
            mon_end:number, 
            tue_start:number, 
            tue_end:number, 
            wed_start:number, 
            wed_end:number, 
            thu_start:number, 
            thu_end:number, 
            fri_start:number, 
            fri_end:number, 
            sat_start:number, 
            sat_end:number, 
            sun_start:number, 
            sun_end:number} = {
                mon_start:parseInt(formData.mon_start), 
                mon_end:parseInt(formData.mon_end), 
                tue_start:parseInt(formData.tue_start), 
                tue_end:parseInt(formData.tue_end), 
                wed_start:parseInt(formData.wed_start), 
                wed_end:parseInt(formData.wed_end), 
                thu_start:parseInt(formData.thu_start), 
                thu_end:parseInt(formData.thu_end), 
                fri_start:parseInt(formData.fri_start), 
                fri_end:parseInt(formData.fri_end), 
                sat_start:parseInt(formData.sat_start), 
                sat_end:parseInt(formData.sat_end), 
                sun_start:parseInt(formData.sun_start), 
                sun_end:parseInt(formData.sun_end)}

        const employeeUrl = 'http://localhost:8080/api/employee/register/1';


        setError('')

        try {
            const response = await axiosInstance.post(employeeUrl, employeeData);
            if (response.status === 201||response.status === 200) {
                const employeeId:number = response.data.id;
                const availabilityUrl=`http://localhost:8080/api/availability/${employeeId}`;
                try {
                    const response = await axiosInstance.post(availabilityUrl, availabilityData);
                    if (response.status === 201||response.status === 200) {
                        navigate('/dashboard');
                    } else {
                        const errorText = response.data;
                        setError(errorText)
                    }
                } catch (err) {
                    setError('An error occured during availability registration')
                }
        
            } else {
                const errorText = response.data;
                setError(errorText)
            }
        } catch (err) {
            setError('An error occured during employee registration')
        }
    }

    const ManagerDataEnum = [{name: 'firstName', label: 'First Name'}, {name: 'lastName', label: 'Last Name'}, {name: 'email', label: 'Email'}, {name: 'password', label: 'Password'}, {name: 'mon_start', label: 'Monday Start Time'}, {name: 'mon_end', label: 'Monday End Time'}, {name: 'tue_start', label: 'Tuesday Start Time'}, {name: 'tue_end', label: 'Tuesday End Time'}, {name: 'wed_start', label: 'Wednesday Start Time'}, {name: 'wed_end', label: 'Wednesday End Time'}, {name: 'thu_start', label: 'Thursday Start Time'}, {name: 'thu_end', label: 'Thursday End Time'}, {name: 'fri_start', label: 'Friday Start Time'}, {name: 'fri_end', label: 'Friday End Time'}, {name: 'sat_start', label: 'Saturday Start Time'}, {name: 'sat_end', label: 'Saturday End Time'}, {name: 'sun_start', label: 'Sunday Start Time'}, {name: 'sun_end', label: 'Sunday End Time'}];
    return (
        <Card sx={{ maxWidth: 700, margin: '30px auto', border: '3px solid #356' }}>
            <CardHeader>
                Login
            </CardHeader>
            <CardContent>
                <Container maxWidth="xs">
                    <Box sx={{ mt: 0 }}>
                        <Typography variant="h4" align="center" gutterBottom>
                            Register
                        </Typography>

                        <form onSubmit={handleSubmit}>
                            <Grid container spacing={2}>
                            {ManagerDataEnum.map((field) => (//maps through all the form fields for better readability
                                <Grid item xs={12} key={field.name}>
                                        <TextField
                                            key={field.name}
                                            label={field.label}
                                            name={field.name}
                                            fullWidth
                                            value={formData[field.name as keyof typeof formData]}
                                            onChange={handleChange}
                                            required
                                        />
                                        </Grid>
                                    ))}                               
                                {error && (
                                    <Grid item xs={12}>
                                        <Typography color="error" variant="body2">
                                            {error}
                                        </Typography>
                                    </Grid>
                                )}
                                <Grid item xs={12}>
                                    <Button type="submit" fullWidth variant="contained" color="primary">
                                        Register
                                    </Button>
                                </Grid>
                            </Grid>
                        </form>
                    </Box>
                </Container>
            </CardContent>
        </Card>
    )


}

