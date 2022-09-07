import axios from 'axios'
import {getToken} from '@/utils/auth'

const mimeMap = {
  xlsx: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
  zip: 'application/zip'
}

const baseUrl = process.env.VUE_APP_BASE_API

export function downLoadZip(str, filename) {
  var url = baseUrl + str
  axios({
    method: 'get',
    url: url,
    responseType: 'blob',
    headers: {'Authorization': 'Bearer ' + getToken()}
  }).then(res => {
    resolveBlob(res, mimeMap.zip)
  })
}

export function exportExcel(url, params) {
  url = baseUrl + splicingParam(url, params);
  axios({
    method: 'get',
    url: url,
    responseType: 'blob',
    headers: {'Authorization': 'Bearer ' + getToken()}
  }).then(res => {
    resolveBlob(res, mimeMap.zip)
  })
}

export function splicingParam(url, params) {
  let str = url + '?';
  for (const propName of Object.keys(params)) {
    const value = params[propName];
    var part = encodeURIComponent(propName) + "=";
    if (value !== null && typeof (value) !== "undefined") {
      if (typeof value === 'object') {
        for (const key of Object.keys(value)) {
          let params = propName + '[' + key + ']';
          var subPart = encodeURIComponent(params) + "=";
          str += subPart + encodeURIComponent(value[key]) + "&";
        }
      } else {
        str += part + encodeURIComponent(value) + "&";
      }
    }
  }
  return str.slice(0, -1);
}

/**
 * 解析blob响应内容并下载
 * @param {*} res blob响应内容
 * @param {String} mimeType MIME类型
 */
export function resolveBlob(res, mimeType) {
  const aLink = document.createElement('a')
  var blob = new Blob([res.data], {type: mimeType})
  // //从response的headers中获取filename, 后端response.setHeader("Content-disposition", "attachment; filename=xxxx.docx") 设置的文件名;
  var patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*')
  var contentDisposition = decodeURI(res.headers['content-disposition'])
  var result = patt.exec(contentDisposition)
  var fileName = result[1]
  fileName = fileName.replace(/\"/g, '')
  aLink.href = URL.createObjectURL(blob)
  aLink.setAttribute('download', fileName) // 设置下载文件名称
  document.body.appendChild(aLink)
  aLink.click()
  document.body.removeChild(aLink);
}
