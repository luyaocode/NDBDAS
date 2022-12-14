import request from '@/utils/request'

const baseUrl = '/gateway/';

// 查询网关列表-下拉框
export function listGateway() {
  return request({
    url: baseUrl + 'listGateway',
    method: 'get',
  })
}

// 新增网关
export function addGateway(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

