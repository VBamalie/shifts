//This will be where all the options that are only available if the employee that is logged in is a manager. otherwise this section displays a blank space.
import { useAuth } from './AuthContext';
import { Button } from '@mui/material';

const Navbar = () => {
    const { employee, logout } = useAuth();

    return (
        <nav>
            {employee ? (
                <>
                    <span>Welcome, {employee.firstName} ({employee.isManager})</span>
                    <Button onClick={logout} variant="contained" color="secondary">
                        Logout
                    </Button>
                </>
            ) : (
                <a href="/login">Login</a>
            )}
        </nav>
    );
};

export default Navbar;
