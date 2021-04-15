import axiosIntance from "../configuration/AxiosConfig";
import { apiEndpointUser } from "../constants/apis";

const UserRepository = {
  getAllUsers: () => axiosIntance.get(apiEndpointUser),
};

export default UserRepository;
