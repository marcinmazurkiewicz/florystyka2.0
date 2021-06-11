import axios from "axios";
import _ from "lodash";
import { getToken } from "@/utils/authUtils";

const HTTP = axios.create({
  baseURL: process.env.VUE_APP_API_URL
});

HTTP.interceptors.request.use(
  config => {
    const originalRequest = _.cloneDeep(config);
    if (getToken()) {
      _.set(originalRequest, "headers.Authorization", getToken());
    }
    return originalRequest;
  },
  err => Promise.reject(err)
);

export { HTTP };
