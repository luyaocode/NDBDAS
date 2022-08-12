import request from '@/utils/request'

const baseUrl = '/tool/gen-table/';

// 查询生成表数据
export function listTable(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}
// 查询db数据库列表
export function listDbTable(query) {
  return request({
    url: baseUrl + 'dbList',
    method: 'get',
    params: query
  })
}

// 查询表详细信息
export function getGenTable(tableId) {
  return request({
    url: baseUrl + tableId,
    method: 'get'
  })
}

// 修改代码生成信息
export function updateGenTable(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 导入表
export function importTable(data) {
  return request({
    url: baseUrl + 'importTable',
    method: 'post',
    params: data
  })
}

// 预览生成代码
export function previewTable(tableId) {
  return request({
    url: baseUrl + 'preview/' + tableId,
    method: 'get'
  })
}

// 删除表数据
export function delTable(tableId) {
  return request({
    url: baseUrl + tableId,
    method: 'delete'
  })
}

// 生成代码（自定义路径）
export function genCode(tableName) {
  return request({
    url: baseUrl + 'genCode/' + tableName,
    method: 'get'
  })
}

// 同步数据库
export function synchDb(tableName) {
  return request({
    url: baseUrl + 'synchDb/' + tableName,
    method: 'get'
  })
}
