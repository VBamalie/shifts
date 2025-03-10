function CalendarApi() {
  //fetch to get the calendar info
  const getCalendar = async () => {
    fetch('https://api.example.com/data')
      .then(response => response.json())
      .then(json => console.log(json))
      .catch(error => console.error(error));
  }
}
export default CalendarApi;