import request from '@/utils/request'
import {praseStrEmpty} from "@/utils/asurplus";

const baseUrl = '/device/management/';

/**
 * 1、查询设备列表
 * url: /device/management/list
 * method: get
 * params传参-传递查询参数对象
 */

export function listDev(query) {
  return request({
    url: baseUrl + 'list',
    method: 'get',
    params: query
  })
}


/**
 * 2、增加设备
 * url: /device/management/
 * method: post
 * data传参-传递需要写入数据库的对象-需要和@RequestBody联合使用
 *
 */
export function addDev(data) {
  return request({
    url: baseUrl,
    method: 'post',
    data: data
  })
}

/**
 * 3、根据记录编号查询设备（非设备编号）
 * url:/device/management/{id}
 * method:get
 * url传参-传递单一参数
 */
export function getDev(id) {
  return request({
    url: baseUrl + praseStrEmpty(id),
    method: 'get',
  })
}

/**
 * 4、修改设备信息
 * url:/device/management/
 * method:put
 * data传参，后端使用@RequestBody接收
 */
export function updateDev(data) {
  return request({
    url: baseUrl,
    method: 'put',
    data: data
  })
}

/**
 * 5、删除设备信息
 * url:/device/management/{ids}
 * method:delete
 *
 */
export function delDev(ids) {
  return request({
    url: baseUrl + ids,
    method: 'delete',
  })
}

/**
 * 6、查询所有已经删除的设备
 * url:/device/management/bin/list
 * method:get
 * params:query
 * 主要是需要传递分页参数pageNum和pageSize。ServletUtil会获取这两个参数并组成TableInfo对象返回给前端
 */
export function listBin(query) {
  return request({
    url: baseUrl + 'bin/list',
    method: 'get',
    params: query,
  })
}

/**
 * 7、彻底删除回收站的设备
 * url: /device/management/bin/{ids}
 * method:delete
 */
export function rmvDev(binIds){
  return request({
    url:baseUrl+'bin/'+binIds,
    method:'delete',
  })
}

/**
 * 8、还原设备信息
 * url:/device/management/bin/{ids}
 * method:put
 */
export function restoreDev(binIds){
  return request({
    url:baseUrl+'bin/'+binIds,
    method:'get',
  })
}




