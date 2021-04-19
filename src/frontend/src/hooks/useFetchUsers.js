import { useState, useEffect } from "react";
import UserRepository from "../services/user.repository";

const useFetchUsers = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isError, setIsError] = useState(false);
  const [error, setError] = useState({});

  const fetchUsers = async () =>
    await UserRepository.getAllUsers()
      .then((res) => setUsers(res.data))
      .catch(({ response }) => {
        setError(response.data);
        setIsError(true);
      })
      .finally(() => setLoading(false));

  useEffect(() => {
    fetchUsers();
  }, []);

  return [users, loading, isError, error, fetchUsers];
};

export default useFetchUsers;
