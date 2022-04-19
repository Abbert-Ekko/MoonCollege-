<template>
    <div class="app-container">
    讲师表单
     <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" :min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像：TODO -->
      <el-form-item label="讲师头像">

          <!-- 头衔缩略图 -->
          <pan-thumb :image="teacher.avatar"/>
          <!-- 文件上传按钮 -->
          <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
          </el-button>

          <!--
      v-show：是否显示上传组件
      :key：类似于id，如果一个页面多个图片上传控件，可以做区分
      :url：后台上传的url地址
      @close：关闭上传组件
      @crop-upload-success：上传成功后的回调 
        <input type="file" name="file"/>
      -->
          <image-cropper
                        v-show="imagecropperShow"
                        :width="300"
                        :height="300"
                        :key="imagecropperKey"
                        :url="BASE_API+'/eduOss/file'"
                        field="file"
                        @close="close"
                        @crop-upload-success="cropSuccess"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>


<script>
import teacher from '@/api/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
export default {
  components: { ImageCropper, PanThumb },
    data(){
      return{
        teacher:{
          name:'',
          sort:0,
          level:1,
          career:'',
          intro:'',
          avatar:'1'
        },
        //上传弹框组件是否显示
        imagecropperShow:false,
        imagecropperKey:0,//上传组件key值
        BASE_API:'http://localhost:9999',
        // process.env.BASE_API, //获取dev.env.js里面地址
        saveBtnDisabled:false
      }
    },
    
    created(){
      this.init()
    },
    watch:{
      $route(to, from) { //路由变化方式，路由发生变化，方法就会执行
        this.init()
      }
    },
    methods:{

      close() { //关闭上传弹框的方法
        this.imagecropperShow=false
        //上传组件初始化
        this.imagecropperKey = this.imagecropperKey+1
      },

      //上传成功方法
      cropSuccess(data) {
        this.imagecropperShow=false
        //上传之后接口返回图片地址
        this.teacher.avatar = data
        console.log("上传成功:"+data);
        console.log("this.teacher.avatar:"+this.teacher.avatar);
        this.imagecropperKey = this.imagecropperKey+1
      },

      init(){
        if(this.$route.params && this.$route.params.id){
          //console.log(this.$route.params);
          const id = this.$route.params.id
          teacher.getTeacherInfo(id)
            .then(response => {
              // console.log(response.data);
              this.teacher = response.data
              // console.log("init"+this.teacher);
            })
          }else{
            this.teacher = {}
        }
      },
    

      //根据teacher是否有id判断修改还是添加
      saveOrUpdate(){
          if(this.teacher.id){
            // console.log(this.teacher.id);
            this.updateTeacher()
          }else{
            this.saveTeacher()
          }
      },
      
      //添加
      saveTeacher(){
        teacher.addTeacher(this.teacher)
        .then( response => {
          console.log("this.teacher"+this.teacher);
          this.$message.success("添加成功");
        })
        this.$router.push({path:'/teacher/table'})
      },

      //修改
      updateTeacher(){
        // console.log("updateTeacher:"+this.teacher);
        teacher.updateTeacherInfo(this.teacher)
          .then( response => {
            // console.log("response=>"+response);
            this.$message.success("修改成功")
          })
          this.$router.push({path:'/teacher/table'})
      },

    }
}
</script>
