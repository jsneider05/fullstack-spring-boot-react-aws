import axios from "axios";

const axiosIntance = axios.create({
  baseURL: "/api",
  // baseURL: process.env.NEXT_PUBLIC_PYME_HOSTNAME,
  timeout: 30000,
  headers: { "Content-Type": "application/json" },
});

export default axiosIntance;
