import axios from "axios";

function App() {
  const getData=()=>{
      axios
          .get("localhost:8080/api/calendar")
          .then(data => console.log(data.data))
          .catch(error => console.log(error));
  };
  getData();
  return (
    <div className="App">
    <h1>Hello World</h1>
    </div>
  )
}

export default App
