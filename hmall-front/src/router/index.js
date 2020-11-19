import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Index from '@/common/Index'
import AllGoods from '@/components/AllGoods'
import GoodDetail from '@/components/GoodDetail'

Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      component: Index,
      name: 'index',
      redirect: '/allGoods',
      children: [
        {
          path: 'allGoods',
          component: AllGoods,
        },
        {
          path: 'goodDetail',
          component: GoodDetail,
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})
