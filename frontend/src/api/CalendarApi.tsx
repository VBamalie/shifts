import axios from "axios";

function CalendarApi() {
  //fetch to get the calendar info
  const getCalendar = async () => {
    axios.get('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error(error);
      });
  }
}
export default CalendarApi;