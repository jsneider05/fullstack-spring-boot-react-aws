import { getAllStudents } from "./client";
import "./App.css";

function App() {
  getAllStudents()
    .then((res) => res.json())
    .then(console.log);

  return "Hello React";
}

export default App;
