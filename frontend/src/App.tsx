import axios from "axios";
import TestFetch from "./testFetch";

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
    <TestFetch/>
    </div>
  )
}

export default App
