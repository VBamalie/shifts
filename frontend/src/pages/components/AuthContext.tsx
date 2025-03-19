import React, { createContext, useContext, useEffect, useState } from 'react'

interface Employee {
    id: number;
    firstName: string;
    lastName: string;
    isManager: boolean;
    calendar: any;
}

interface AuthContextType {
    employee: Employee | null;
    login: (employeeData: Employee) => void;
    logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) throw new Error("useAuth must be used within an AuthProvider");
    return context;
};

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [employee, setEmployee] = useState<Employee | null>(() => {
        const storedUser = localStorage.getItem('employee');
        return storedUser ? JSON.parse(storedUser) : null;
    });

    const login = (employeeData: Employee) => {
        setEmployee(employeeData);
        localStorage.setItem('employee', JSON.stringify(employeeData));
    };

    const logout = () => {
        setEmployee(null);
        localStorage.removeItem('employee');
        window.location.href = '/login';
    };

    return (
        <AuthContext.Provider value={{ employee, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};