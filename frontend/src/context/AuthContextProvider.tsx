import { type ReactNode, useEffect, useState } from "react";
import { AuthContext } from "./AuthContext";
import { useNavigate } from "react-router-dom";


export const AuthContextProvider = ({children}: {children: ReactNode}) => {
    const [loggedIn, setLoggedIn] = useState(false)
    const [person, setPerson] = useState({})
    const [role, setRole] = useState("CUSTOMER")
    const navigate = useNavigate()

    useEffect(() => {
        fetchPerson();
    }, [])

    function fetchPerson() {
        fetch(`http://localhost:8080/person`, {
            headers: {
                "Authorization": "Bearer " + sessionStorage.getItem("token")
            }
        })
        .then(res => res.json())
        .then(json => {
            setPerson(json);
            setRole(json.role);
            setLoggedIn(true);
            console.log(json)
        })
    }

    function login(token:string) {
        sessionStorage.setItem("token", token)
        fetchPerson()
        navigate("/profile")
        
    }

    function logout() {
        setLoggedIn(false)
        setPerson({})
        setRole("")
        navigate("/")
        sessionStorage.removeItem("token")
    }
    
    return (
        <AuthContext.Provider value = {{loggedIn, person, role, login, logout}}>
            {children}
        </AuthContext.Provider>
    )
}