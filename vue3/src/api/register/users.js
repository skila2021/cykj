import request from '@/utils/request'

// 查询注册、成功列表
export function listUsers(query) {
  return request({
    url: '/register/users/list',
    method: 'get',
    params: query
  })
}

// 查询注册、成功详细
export function getUsers(id) {
  return request({
    url: '/register/users/' + id,
    method: 'get'
  })
}

// 新增注册、成功
export function addUsers(data) {
  return request({
    url: '/register/users',
    method: 'post',
    data: data
  })
}

// 修改注册、成功
export function updateUsers(data) {
  return request({
    url: '/register/users',
    method: 'put',
    data: data
  })
}

// 删除注册、成功
export function delUsers(id) {
  return request({
    url: '/register/users/' + id,
    method: 'delete'
  })
}
