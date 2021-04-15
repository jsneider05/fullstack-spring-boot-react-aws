import { useState, useEffect } from "react";
import { getAllUsers } from "./client.js";

const useFetchUsers = () => {
  const [users, setUsers] = useState([]);
  const [fetching, setFetching] = useState(true);

  const fetchUsers = () =>
    getAllUsers()
      .then((res) => res.json())
      .then((data) => {
        setUsers(data);
        setFetching(false);
      });

  useEffect(() => {
    fetchUsers();
  }, []);

  return [users, fetching];
};

export default useFetchUsers;
