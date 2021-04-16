import { useState, useEffect } from "react";
import UserRepository from "../services/user.repository";

const useFetchUsers = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState([]);

  const fetchUsers = async () =>
    await UserRepository.getAllUsers()
      .then((res) => {
        setUsers(res.data);
        setLoading(false);
      })
      .catch(({ response }) => {
        setError([response.status, response.statusText]);
      });

  useEffect(async () => {
    fetchUsers();
  }, []);

  return { users, loading, error, fetchUsers };
};

export default useFetchUsers;
