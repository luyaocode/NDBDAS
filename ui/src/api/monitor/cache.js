import request from '@/utils/request'

const baseUrl = '/monitor/cache/';

// 查询缓存详细
export function getCache() {
  return request({
    url: baseUrl,
    method: 'get'
  })
}
