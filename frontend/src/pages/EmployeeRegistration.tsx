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
                console.log(availabilityUrl);
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
                                <Grid item xs={12}>
                                    <TextField
                                        label="First Name"
                                        name="firstName"
                                        fullWidth
                                        value={formData.firstName}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Last Name"
                                        name="lastName"
                                        fullWidth
                                        value={formData.lastName}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Email"
                                        name="email"
                                        fullWidth
                                        value={formData.email}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Password"
                                        name="password"
                                        type="password"
                                        fullWidth
                                        value={formData.password}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Monday Start Time"
                                        name="mon_start"
                                        type="mon_start"
                                        fullWidth
                                        value={formData.mon_start}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Monday End Time"
                                        name="mon_end"
                                        type="mon_end"
                                        fullWidth
                                        value={formData.mon_end}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Tuesday Start Time"
                                        name="tue_start"
                                        type="tue_start"
                                        fullWidth
                                        value={formData.tue_start}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Tuesday End Time"
                                        name="tue_end"
                                        type="tue_end"
                                        fullWidth
                                        value={formData.tue_end}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Wednesday Start Time"
                                        name="wed_start"
                                        type="wed_start"
                                        fullWidth
                                        value={formData.wed_start}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Wednesday End Time"
                                        name="wed_end"
                                        type="wed_end"
                                        fullWidth
                                        value={formData.wed_end}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Thursday Start Time"
                                        name="thu_start"
                                        type="thu_start"
                                        fullWidth
                                        value={formData.thu_start}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Thursday End Time"
                                        name="thu_end"
                                        type="thu_end"
                                        fullWidth
                                        value={formData.thu_end}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Friday Start Time"
                                        name="fri_start"
                                        type="fri_start"
                                        fullWidth
                                        value={formData.fri_start}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Friday End Time"
                                        name="fri_end"
                                        type="fri_end"
                                        fullWidth
                                        value={formData.fri_end}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Saturday Start Time"
                                        name="sat_start"
                                        type="sat_start"
                                        fullWidth
                                        value={formData.sat_start}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Saturday End Time"
                                        name="sat_end"
                                        type="sat_end"
                                        fullWidth
                                        value={formData.sat_end}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Sunday Start Time"
                                        name="sun_start"
                                        type="sun_start"
                                        fullWidth
                                        value={formData.sun_start}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField
                                        label="Sunday End Time"
                                        name="sun_end"
                                        type="sun_end"
                                        fullWidth
                                        value={formData.sun_end}
                                        onChange={handleChange}
                                        required
                                    />
                                </Grid>

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

