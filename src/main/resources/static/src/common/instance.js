import axios from "axios";

const instance = axios.create({
  baseURL:
  process.env.NODE_ENV !== "development"
    ? window.location.pathname
      : false, //设置默认api路径
  timeout: 30000, //设置超时时间
  headers: {
    "Accept": "*/*",
    "Content-Type": "application/json;charset=UTF-8",
    // "authorization": token,
    "Pragma": "no-cache"
  }
})
// 添加请求拦截器
instance.interceptors.request.use(function (config) {
  let cfg = config;
  if (!cfg.headers.authorization && window.sessionStorage.getItem("token")) {
    cfg.headers.authorization = window.sessionStorage.getItem("token");
  }
  return cfg;
});
export default instance