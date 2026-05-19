import request from '@/utils/request'

// 查询注册、成功列表
export function listSessions(query) {
  return request({
    url: '/register/sessions/list',
    method: 'get',
    params: query
  })
}

// 查询注册、成功详细
export function getSessions(id) {
  return request({
    url: '/register/sessions/' + id,
    method: 'get'
  })
}

// 新增注册、成功
export function addSessions(data) {
  return request({
    url: '/register/sessions',
    method: 'post',
    data: data
  })
}

// 修改注册、成功
export function updateSessions(data) {
  return request({
    url: '/register/sessions',
    method: 'put',
    data: data
  })
}

// 删除注册、成功
export function delSessions(id) {
  return request({
    url: '/register/sessions/' + id,
    method: 'delete'
  })
}
