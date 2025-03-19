import React, { createContext, useContext, useState } from 'react'

const AuthContext = createContext({
    isAuthenticated: false,
    login: () => {},
    logout: () => {}
});
export const useAuth = () => useContext(AuthContext)


export const AuthProvider = ({children}: {children: React.ReactNode}) => {

    const [isAuthenticated, setIsAuthenticated] = useState(() => {
        return localStorage.getItem('isAuthenticated') === 'true'
    })

    const login = () => {
        setIsAuthenticated(true)
        localStorage.setItem('isAuthenticated', 'true')
    }

    const logout = () => {
        setIsAuthenticated(false)
        localStorage.removeItem('isAuthenticated')
    }

    return (
        <AuthContext.Provider value={{isAuthenticated, login, logout}}>
            {children}
        </AuthContext.Provider>
    )

}