import request from '@/utils/request'

const baseUrl = '/system/sys-dict/';

// 查询字典类型列表
export function listType(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}

// 新增字典类型
export function addType(data) {
  return request({
    url: baseUrl + 'add',
    method: 'post',
    data: data
  })
}

// 修改字典类型
export function updateType(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 删除字典类型
export function delType(dictId) {
  return request({
    url: baseUrl + dictId,
    method: 'delete'
  })
}

// 查询字典类型下拉列表
export function listTypeSelect() {
  return request({
    url: baseUrl + 'listSelect',
    method: 'get',
  })
}

// 查询字典类型详细
export function getType(id) {
  return request({
    url: baseUrl + id,
    method: 'get'
  })
}

// 刷新字典缓存
export function refreshCache() {
  return request({
    url: '/system/dict/type/refreshCache',
    method: 'delete'
  })
}

// 导出字典类型
export function exportType(query) {
  return request({
    url: '/system/dict/type/export',
    method: 'get',
    params: query
  })
}

// 获取字典选择框列表
export function optionselect() {
  return request({
    url: baseUrl + 'optionselect',
    method: 'get'
  })
}
