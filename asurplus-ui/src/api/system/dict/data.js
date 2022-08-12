import request from '@/utils/request'

const baseUrl = '/system/sys-dict-detail/';

// 查询字典数据列表
export function listData(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}

// 查询字典数据详细
export function getData(dictCode) {
  return request({
    url: baseUrl + dictCode,
    method: 'get'
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request({
    url: baseUrl + 'listDetail/' + dictType,
    method: 'get'
  })
}

// 新增字典数据
export function addData(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

// 修改字典数据
export function updateData(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 删除字典数据
export function delData(dictCode) {
  return request({
    url: baseUrl + dictCode,
    method: 'delete'
  })
}

// 导出字典数据
export function exportData(query) {
  return request({
    url: '/system/dict/data/export',
    method: 'get',
    params: query
  })
}
