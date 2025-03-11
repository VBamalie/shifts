//This will be where all the options that are only available if the employee that is logged in is a manager. otherwise this section displays a blank space.
function ManagersButtons(){
    return(
        <div>
            <button onClick={()=> alert("you have clicked!")}>Press me</button>
        </div>
    )
}
export default ManagersButtons;