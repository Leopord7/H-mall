import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'default',
      component: Login,
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})
