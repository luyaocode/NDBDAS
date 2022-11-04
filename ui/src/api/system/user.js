import request from '@/utils/request'
import {praseStrEmpty} from "@/utils/asurplus";

const baseUrl = '/system/sys-user-info/';

// 查询用户列表
export function listUser(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query,
  })
}

// 查询用户详细
export function getUser(userId) {
  return request({
    url: baseUrl + praseStrEmpty(userId),
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  return request({
    url: baseUrl + userId,
    method: 'delete'
  })
}

// 导出用户
// 注：user/index.vue页面中并未使用该接口，使用的是zipdownload.js里面的exportExcel
export function exportUser(query) {
  return request({
    url: baseUrl + 'export',
    method: 'get',
    params: query
  })
}

// 用户密码重置
export function resetUserPwd(data) {
  return request({
    url: baseUrl + 'resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(id, status) {
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

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/sys-user-profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/sys-user-profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/sys-user-profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/sys-user-profile/updateAvatar',
    method: 'post',
    data: data
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/system/user/importTemplate',
    method: 'get'
  })
}

// 查询授权角色
export function getAuthRole(userId) {
  return request({
    url: baseUrl + 'authRole/' + userId,
    method: 'get'
  })
}

// 保存授权角色
export function updateAuthRole(data) {
  return request({
    url: baseUrl + '/updateAuthRole',
    method: 'put',
    params: data
  })
}
