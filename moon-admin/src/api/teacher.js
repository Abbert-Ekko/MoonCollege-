import request from '@/utils/request'

export default{
  //条件分页查询
  getTeacherList(current,pagesize,eduTeacher) {
    return request({
      url: '/eduService/edu-teacher/'+current+"/"+pagesize,
      method: 'post',
      data:eduTeacher
    })
  },
  //删除
  deleteDataById(id){
    return request({
      url: '/eduService/edu-teacher/'+id,
      method:'delete',
    })
  },
  //添加
  addTeacher(teacher){
    return request({
      url: '/eduService/edu-teacher/add',
      method:'post',
      data: teacher
    })
  },
  //根据id查询
  getTeacherInfo(id){
    return request({
      url: '/eduService/edu-teacher/'+id,
      method: 'get'
    })
  },
  //修改
  updateTeacherInfo(teacher) {
    // console.log(teacher);
    return request({
      url: '/eduService/edu-teacher/edit',
      method: 'put',
      data: teacher
    })
  }
}