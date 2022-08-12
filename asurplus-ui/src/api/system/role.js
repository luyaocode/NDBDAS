import request from '@/utils/request'

const baseUrl = '/system/sys-role-info/';

// 查询角色列表
export function listRole(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}

// 查询角色详细
export function getRole(roleId) {
  return request({
    url: baseUrl + roleId,
    method: 'get'
  })
}

// 新增角色
export function addRole(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

// 修改角色
export function updateRole(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 角色数据权限
export function dataScope(data) {
  return request({
    url: '/system/role/dataScope',
    method: 'put',
    data: data
  })
}

// 角色状态修改
export function changeRoleStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: baseUrl + 'updateStatus',
    method: 'put',
    data: data
  })
}

// 删除角色
export function delRole(roleId) {
  return request({
    url: baseUrl + roleId,
    method: 'delete'
  })
}

// 导出角色
export function exportRole(query) {
  return request({
    url: '/system/role/export',
    method: 'get',
    params: query
  })
}

// 查询角色已授权用户列表
export function allocatedUserList(query) {
  return request({
    url: baseUrl + 'allocatedList',
    method: 'get',
    params: query
  })
}

// 查询角色未授权用户列表
export function unallocatedUserList(query) {
  return request({
    url: baseUrl + 'unAllocatedList',
    method: 'get',
    params: query
  })
}

// 取消用户授权角色
export function authUserCancel(data) {
  return request({
    url: baseUrl + 'cancelAuth',
    method: 'put',
    data: data
  })
}

// 批量取消用户授权角色
export function authUserCancelAll(data) {
  return request({
    url: baseUrl + 'cancelAuthAll',
    method: 'put',
    params: data
  })
}

// 授权用户选择
export function authUserSelectAll(data) {
  return request({
    url: baseUrl + 'addAuthAll',
    method: 'put',
    params: data
  })
}
