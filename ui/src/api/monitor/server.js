import request from '@/utils/request'

const baseUrl = '/monitor/server/';

// 查询服务器详细
export function getServer() {
  return request({
    url: baseUrl,
    method: 'get'
  })
}
