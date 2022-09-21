import request from '@/utils/request'

const baseUrl = '/device/dev-epara-info/';

// 查询当前电参数
export function getEParas() {
  return request({
    url: baseUrl + 'A01-01',
    method: 'get',
    // params: query
  })
}

//查询历史电参量
export function ListEParas() {
  return request({
    url: baseUrl + 'A01-01-all',
    method: 'get',
    // params: query
  })
}


//读单个线圈
export function readHoldingRegister(data) {
  return request({
    url: baseUrl + 'A01-01/0x03',
    method: 'post',
    data: data
    // params: query
  })
}


/**
 * 测试用
 */
//插入一条电参量记录
export function testAddEPara(){
  return request({
    url:baseUrl+"A01-01-add",
    method:'get',
  })
}
