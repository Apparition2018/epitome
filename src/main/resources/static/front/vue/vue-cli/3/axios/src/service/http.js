import axios from 'axios'
import service from './contactApi'
import { Toast } from 'vant'

// service 循环遍历输出不同的请求方法
const instance = axios.create({
  baseURL: 'http://localhost:9000/api',
  timeout: 1000
})
// 包裹请求方法的容器
const Http = {}

// 请求格式/参数的统一
for (const key in service) {
  const api = service[key]
  // async：避免进入回调地狱
  Http[key] = async function (
    // get,delete: url
    // put,post,patch: data
    params,
    // 表示是否是 form-data 请求
    isFormData = false,
    // 配置参数
    config = {}
  ) {
    let newParams
    // 判断是否 multipart/form-data
    if (params && isFormData) {
      newParams = new FormData()
      for (const i in params) {
        newParams.append(i, params[i])
      }
    } else {
      newParams = params
    }

    // 请求的返回值
    let response = {}
    // 不同请求的判断
    if (['put', 'post', 'patch'].indexOf(api.method) >= 0) {
      try {
        response = await instance[api.method](api.url, newParams, config)
      } catch (err) {
        response = err
      }
    } else if (api.method === 'delete' || api.method === 'get') {
      config.params = newParams
      try {
        response = await instance[api.method](api.url, config)
      } catch (err) {
        response = err
      }
    }
    return response
  }
}

// 请求拦截器
instance.interceptors.request.use(config => {
  // 发起请求前做些什么
  Toast.loading({
    mask: false,
    // 一直存在
    duration: 0,
    // 禁止点击
    forbidClick: true,
    message: '加载中...'
  })
  return config
}, () => {
  // 请求错误
  Toast.clear()
  Toast('请求失败，请稍后重试')
})
// 响应拦截器
instance.interceptors.response.use(res => {
  // 请求成功
  Toast.clear()
  return res.data
}, () => {
  // 请求错误
  Toast.clear()
  Toast('请求失败，请稍后重试')
})

export default Http
