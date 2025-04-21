//Login page for employees and managers
import React, { useState } from 'react'

import {
    Typography,
    Button,
    Box,
    Container,
    Card,
    CardActions,
    CardHeader,
    CardContent,
    TextField
  } from '@mui/material';
import { useNavigate, Link } from 'react-router-dom';

import axios from 'axios';

import { useAuth } from './components/AuthContext';

export default function Login() {

    
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate()

    const {login} = useAuth();

    const handleLogin = async (e: { preventDefault: () => void; }) => {
        e.preventDefault()
        setError('')

        const loginData = {
            email,
            password
        }
        try {
            const response = await axios.post('http://localhost:8080/api/employee/login', loginData);
            if (response.status === 200){
              const employeeData = response.data;
                login(employeeData);
                window.location.reload();
                window.location.href = '/';
              } else {
                setError(response.data.message || 'Login failed for user. Please retry!')
            }
        } catch(error) {
            setError('An error occurred. please retry')
        }
    }
  return (
    <Card sx={{ maxWidth: 900, margin: '30px auto', border: '3px solid #356' }}>
      <CardHeader>
        Login
      </CardHeader>
      <CardContent>
          <Container maxWidth="xs">
          <Box
            component="form"
            onSubmit={handleLogin}
            sx={{ mt: 4, display: 'flex', flexDirection: 'column', gap: 2 }}
          >
            <Typography variant="h5" component="h1" align="center">
              Login
            </Typography>

            {error && (
              <Typography variant="body2" color="error" align="center">
                {error}
              </Typography>
            )}

            <TextField
              label="email"
              variant="outlined"
              fullWidth
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />

            <TextField
              label="Password"
              variant="outlined"
              type="password"
              fullWidth
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />

            <Button type="submit" variant="contained" color="primary" fullWidth>
              Login
            </Button>
          </Box>
          </Container>
      </CardContent>
      <CardActions>
        <Link to="/register-business" color="primary">
          Register a New Business
        </Link>
      </CardActions>
    </Card>

  )
}