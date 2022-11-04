import request from '@/utils/request'

const baseUrl = '/system/sys-dept-info/';

// 查询部门列表
export function listDept(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}

// 新增部门
export function addDept(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateDept(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 删除部门
export function delDept(deptId) {
  return request({
    url: baseUrl + deptId,
    method: 'delete'
  })
}

// 查询部门列表（排除节点）
export function listDeptExcludeChild(deptId) {
  return request({
    url: baseUrl + 'exclude/' + deptId,
    method: 'get'
  })
}

// 查询部门详细
export function getDept(deptId) {
  return request({
    url: baseUrl + deptId,
    method: 'get'
  })
}

// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: baseUrl + 'treeSelect',
    method: 'get'
  })
}

// 根据角色ID查询部门树结构
export function roleDeptTreeselect(roleId) {
  return request({
    url: '/system/dept/roleDeptTreeselect/' + roleId,
    method: 'get'
  })
}
