import axios from "axios";
import _ from "lodash";
import { getToken } from "@/utils/authUtils";

axios.defaults.withCredentials = true;

const HTTP = axios.create({
  baseURL: process.env.VUE_APP_API_URL
});

const authHTTP = axios.create({
  baseURL: process.env.VUE_APP_API_URL
});

authHTTP.interceptors.request.use(
  config => {
    const originalRequest = _.cloneDeep(config);
    _.set(originalRequest, "headers.Authorization", getToken());
    return originalRequest;
  },
  err => Promise.reject(err)
);

export { HTTP, authHTTP };
