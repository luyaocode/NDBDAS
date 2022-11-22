import request from '@/utils/request'

const baseUrl = '/test/sendCtl';

export function sendCtl() {
  return request({
    url: baseUrl,
    method: 'get',
  })
}
