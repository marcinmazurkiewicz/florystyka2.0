import axios from 'axios'
import _ from "lodash";
import store from "@/store/store";

const HTTP = axios.create({
    baseURL: process.env.VUE_APP_API_URL
})

const authHTTP = axios.create({
    baseURL: process.env.VUE_APP_API_URL
})

authHTTP.interceptors.request.use(
    config => {
        const originalRequest = _.cloneDeep(config);
        _.set(originalRequest, 'headers.Authorization', store.getters["user/getToken"]);
        return originalRequest;
    },
    err => Promise.reject(err),
);

let isRefreshing = false;
let failedQueue = [];

const processQueue = (error, token = null) => {
    failedQueue.forEach(prom => {
        if (error) {
            prom.reject(error);
        } else {
            prom.resolve(token);
        }
    })

    failedQueue = [];
}

authHTTP.interceptors.response.use(response => {
    return response;
}, error => {
    const prevRequest = error.config;
    if (error.response.status === 401 && !prevRequest._retry) {
        if (isRefreshing) {
            return new Promise(function (resolve, reject) {
                failedQueue.push({resolve, reject})
            }).then(token => {
                prevRequest.headers['Authorization'] = token;
                return axios(prevRequest);
            }).catch(err => {
                return Promise.reject(err);
            })
        }

        prevRequest._retry = true;
        isRefreshing = true;

        const data = {
            refreshToken: store.getters["user/getRefreshToken"]
        }

        return new Promise(function (resolve, reject) {
            HTTP.post('/api/auth/refresh', data)
                .then((response) => {
                    store.dispatch("user/saveToken", response.headers["authorization"])
                        .then(() => {
                            prevRequest.headers['Authorization'] = store.getters["user/getToken"];
                            processQueue(null, store.getters["user/getToken"]);
                            resolve(authHTTP(prevRequest));
                        });
                    store.dispatch("user/saveRefreshToken", response.headers["refresh-token"]);
                })
                .catch(error => {
                    processQueue(error, null);
                    reject(error);
                    store.dispatch("user/logout")
                        .then(() => {
                            window.location.href = '/';
                        });
                })
                .finally(() => isRefreshing = false)
        })
    }
    return Promise.reject(error);
});

export {HTTP, authHTTP};