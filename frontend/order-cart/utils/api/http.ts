import axios from "axios";
import { getAuthCredentials } from "../auth-utits";
import nookies from "nookies";
import Router from "next/router";
const http = axios.create({
  baseURL: process.env.NEXT_PUBLIC_REST_API_ENDPOINT,
  timeout: 30000,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

http.interceptors.request.use(
  (config) => {
    const { token } = getAuthCredentials();
    config.headers = {
      ...config.headers,
      Authorization: `${token}`,
    };
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

http.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (
      (error.response && error.response.status === 401) ||
      (error.response && error.response.status === 403) ||
      (error.response && error.response.data.message === "NOT_AUTHORIZED")
    ) {
      nookies.destroy(null, "AUTH_CRED");
      // Router.push(ROUTES.LOGIN);
    }
    if (error.response && error.response.status === 400) {
      
      Router.push("/login")
    }
    return Promise.reject(error);
  }
);

export default http;
