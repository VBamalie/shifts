//this page will be used by managers to edit the schedule for their business. the first time this week is selected, a call will be made to create a bunch of emptty shifts. The page will ask them if they want to autocreate shifts. if they say yes, it will call the autocreate shifts function in the back end

//after the either autocreated shifts or empty shifts are made, a manager can hopefully drag and drop employees into different shifts, either that or select shifts and click add employee. When they are done, they will click save and a PUT request will be sent to the server changing the employee and the shift data.
function EditSchedule(){
    return(
        <div>
            <p>Edit Schedule</p>
        </div>
    )
}
export default EditSchedule;