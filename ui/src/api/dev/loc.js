import request from '@/utils/request'

const baseUrl = '/device/location/';

// 查询楼层树结构
export function treeSelect() {
  return request({
    url: baseUrl + 'treeSelect',
    method: 'get',
  })
}

// 查询当前楼层信息
export function listLoc(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}

