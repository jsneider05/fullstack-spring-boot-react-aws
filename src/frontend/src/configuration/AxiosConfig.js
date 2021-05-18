import axios from "axios";

const axiosIntance = axios.create({
  baseURL: "/api",
  timeout: 30000,
  headers: { "Content-Type": "application/json", "Accept-Language": "en-US, es-CO" },
});

export default axiosIntance;
