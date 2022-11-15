import request from '@/utils/request'

const baseUrl = '/config/gateway/';

// 查询参数列表
export function listConfig(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}

// 查询参数详细
export function getConfig(configId) {
  return request({
    url: baseUrl + configId,
    method: 'get'
  })
}

// 根据参数键名查询参数值
export function getParamValue(key) {
  return request({
    url: baseUrl + "getParamValue/" + key,
    method: 'get'
  })
}

// 新增参数配置
export function addConfig(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

// 修改网关参数配置

export function updateConfig(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 删除参数配置
export function delConfig(configId) {
  return request({
    url: baseUrl + configId,
    method: 'delete'
  })
}

// 刷新参数缓存
export function refreshCache() {
  return request({
    url: baseUrl + 'refreshCache',
    method: 'delete'
  })
}

// 导出参数
export function exportConfig(query) {
  return request({
    url: baseUrl + '/export',
    method: 'get',
    params: query
  })
}

// 连接网关
export function connect(id) {
  return request({
    url: baseUrl + 'connect/' + id,
    method: 'get',
  })
}

//查找字典
export function listDict(dictType) {
  return request({
    url: baseUrl + 'dictList/' + dictType,
    method: 'get',
  })
}

//网关切换到状态
export function updateStatus(data) {
  return request({
    url: baseUrl + "updateStatus",
    method: 'put',
    data: data,
  })
}
