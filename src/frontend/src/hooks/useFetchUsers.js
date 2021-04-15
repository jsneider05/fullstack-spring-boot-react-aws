import { useState, useEffect } from "react";
import UserRepository from "../services/user.repository";

const useFetchUsers = () => {
  const [users, setUsers] = useState([]);
  const [fetching, setFetching] = useState(true);
  const [error, setError] = useState([]);

  const fetchUsers = async () =>
    await UserRepository.getAllUsers()
      .then((res) => {
        setUsers(res.data);
        setFetching(false);
      })
      .catch(({ response }) => {
        setError([response.status, response.statusText]);
      });

  useEffect(async () => {
    fetchUsers();
  }, []);

  return [users, fetching, error];
};

export default useFetchUsers;
