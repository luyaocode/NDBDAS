import request from '@/utils/request'

const baseUrl = '/tool/chat';

/**
 * 请求数据
 * @param tableName
 */
export function requestData() {
  return request({
    url: baseUrl,
    method: 'get'
  })
}
