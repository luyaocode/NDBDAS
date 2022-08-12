import request from '@/utils/request'

const baseUrl = '/config/sys-app/';

// 查询app版本信息列表
export function listApp(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}

// 查询app版本信息详细
export function getApp(id) {
  return request({
    url: baseUrl + id,
    method: 'get'
  })
}

// 新增app版本信息
export function addApp(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

// 修改app版本信息
export function updateApp(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 删除app版本信息
export function delApp(id) {
  return request({
    url: baseUrl + id,
    method: 'delete'
  })
}

// 导出app版本信息
export function exportApp(query) {
  return request({
    url: baseUrl + 'export',
    method: 'get',
    params: query
  })
}
