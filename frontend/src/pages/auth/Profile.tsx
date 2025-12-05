import { useEffect,useState } from "react";
import { useTranslation } from "react-i18next";
import { toast, ToastContainer } from 'react-toastify';

function Profile() {
  const [person,setPerson] = useState({email: "", password: "", firstName: "", lastName: ""});
  const { t } = useTranslation()

  useEffect(() => {
    async function load() {     
      try {
        const res = await fetch("http://localhost:8080/person", {
          headers: {
            "Authorization": "Bearer " + sessionStorage.getItem("token")
          }
        })
        const json = await res.json()
        setPerson(json)
        console.log(json)
      } catch(error) {
        console.log(error)
      }
    }
    load()
  }, []);

  async function updateProfile() {
    try {
      const res = await fetch("http://localhost:8080/persons", {
        method: "PUT",
        body: JSON.stringify(person),
        headers: {
          "Authorization": "Bearer " + sessionStorage.getItem("token"),
          "Content-Type": "application/json"
        }
      })
      const json = await res.json()
      if(json.message && json.status && json.timestamp) {
        toast.error(getErrorMessage(json)) //ilusam on neid läbi tõlke kuvada
      } else {
        toast.success(t("profile.success"))
      }
      console.log(json)
    } catch(error) {
      console.log(error)
    }
  }

  function getErrorMessage(message: string) {
    const errorMessage =  t("error." + message)
    if (errorMessage.startsWith("error.")) { // ei saanud tõlkida
      return t("error.generic") // üldine veateade
    }
    else {
      return errorMessage // sai tõlkida, backendist tulnud tõlgitud veateade
    }
  }

  return (
    <div><label>First name</label> <br />
      <input defaultValue={person.firstName} onChange={(e) => setPerson({...person, "firstName": e.target.value})} type="text" /> <br />
      <label>Last name</label> <br />
      <input defaultValue={person.lastName} onChange={(e) => setPerson({...person, "lastName": e.target.value})} type="text" /> <br />
      <label>Email</label> <br />
      <input defaultValue={person.email} onChange={(e) => setPerson({...person, "email": e.target.value})} type="text" /> <br />
      <button onClick={updateProfile}>Update</button>
      <ToastContainer />
      <br /><br /><br />

      <label>Old Password</label> <br />
      <input defaultValue={person.password} onChange={(e) => setPerson({...person, "password": e.target.value})} type="password" /> <br />
      <label>New Password</label> <br />
      <input defaultValue={person.password} onChange={(e) => setPerson({...person, "password": e.target.value})} type="password" /> <br />
      <label>Confirm new Password</label> <br />
      <input defaultValue={person.password} onChange={(e) => setPerson({...person, "password": e.target.value})} type="password" /> <br />
      
    </div>
  )
}

export default Profile