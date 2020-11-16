<template>
  <div class="login-container">
        <el-form
          label-position="left"
          label-width="0px"
          status-icon
        >
          <h3 class="login_title">系统登录</h3>
          <el-form-item prop="account">
            <el-input
              type="text"
              v-model="username"
              auto-complete="off"
              placeholder="账号"
            ></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input
              type="password"
              v-model="password"
              auto-complete="off"
              placeholder="密码"
            ></el-input>
          </el-form-item>
          <el-form-item style="width: 100%">
            <el-button type="primary" @click.native.prevent="login" style="width: 100%">登录</el-button>
          </el-form-item>
          <el-form-item style="width: 100%">
            <el-button type="primary" @click.native.prevent="register" style="width: 100%">注册</el-button>
          </el-form-item>
        </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
        username:'',
        password:''
    };
  },
  methods:{
      login(){
          var params = {
              username:this.username,
              password:this.password
          }
          this.$axios({
              url: "/user/login",
              method: "post",
              data: params,
          }) .then(res => {
              console.log(res);
          }) .catch (error => {
              console.log(error);
          })
      },
      register() {
        var params = {
              username:this.username,
              password:this.password
          }
          this.$axios({
              url: "/user/register",
              method: "post",
              data: params,
          }) .then(res => {
              console.log(res);
          }) .catch (error => {
              console.log(error);
          })
      }
  }
}
</script>
<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 10% auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
