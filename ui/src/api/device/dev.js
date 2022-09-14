import request from '@/utils/request'

const baseUrl = '/device/dev-epara-info/';

// 查询设备显示数据
export function listEPara() {
  return request({
    url: baseUrl + 'A01-01',
    method: 'get',
    // params: query
  })
}
