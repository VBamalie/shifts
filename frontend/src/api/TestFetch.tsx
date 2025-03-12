import { useEffect, useState } from "react";

//function methodName(variableName: type): returnType {}
//const names: Array<string> = ["John", "Jane", "Bob"];
//enum daysOfWeek { MON, TUE, WED, THU, FRI, SAT, SUN };
//ie
//let today: daysOfWeek = daysOfWeek.MON;
//if(today === daysOfWeek.MON) { code here }


//interface Person {
//id: number;
//name: string;
//age: number;
//}
//const user: User -{ id: 1, name: "John", age: 30 };

//Type Aliases
// type User = {
//   id: number;
//   name: string;
//}
//difference between type and interface is that interface can be extended and type cannot

export default function TestFetch() {
  const [calendar, setCalendar] = useState<{ businessName?: string }>({});
  useEffect(() => {
    fetch("http://localhost:8080/api/calendar/1")
      .then((response) => response.json())
      .then((data) => setCalendar(data));
  }, []);

  return(
      <div><h1>{calendar.businessName}</h1></div>
  )
}
