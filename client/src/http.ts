import axios from "axios";

const HTTP = axios.create({
  baseURL: process.env.VUE_APP_API_URL
});

export { HTTP };
