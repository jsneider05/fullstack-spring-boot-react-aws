import axiosIntance from "../configuration/AxiosConfig";
import { apiEndpointUser } from "../constants/apis";

const UserRepository = {
  getAllUsers: () => axiosIntance.get(apiEndpointUser),
  addUser: (user) => axiosIntance.post(apiEndpointUser, user),
};

export default UserRepository;
