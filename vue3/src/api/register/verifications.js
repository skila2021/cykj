import request from '@/utils/request'

// 查询注册、成功列表
export function listVerifications(query) {
  return request({
    url: '/register/verifications/list',
    method: 'get',
    params: query
  })
}

// 查询注册、成功详细
export function getVerifications(id) {
  return request({
    url: '/register/verifications/' + id,
    method: 'get'
  })
}

// 新增注册、成功
export function addVerifications(data) {
  return request({
    url: '/register/verifications',
    method: 'post',
    data: data
  })
}

// 修改注册、成功
export function updateVerifications(data) {
  return request({
    url: '/register/verifications',
    method: 'put',
    data: data
  })
}

// 删除注册、成功
export function delVerifications(id) {
  return request({
    url: '/register/verifications/' + id,
    method: 'delete'
  })
}
