import axiosIntance from "../configuration/AxiosConfig";
import { apiEndpointUser } from "../constants/apis";

const UserRepository = {
  getAllUsers: () => axiosIntance.get(apiEndpointUser),
  addUser: (user) => axiosIntance.post(apiEndpointUser, user),
  deleteUser: (id) => axiosIntance.delete(`${apiEndpointUser}/${id}`),
};

export default UserRepository;
