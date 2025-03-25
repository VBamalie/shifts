import { Card, CardHeader, CardContent, Container, Box, Typography, TextField, Button, CardActions } from '@mui/material';
import axios, { Axios } from 'axios';
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from './components/AuthContext';

//TODO: currently the demoStepper is the way to make new calendars. we need to clean that up and add it to this file.
//TODO: demoStepper also doesnt have the right logic for creating a timeblock. We'll need to make a component specifically for editing and addding timeblocks 
//This page is how we create a new business. We will take in the business name, the manager, and the timeblocks for each day.
export default function NewCalendar() {
     return(
      <>
      </>
     );
}