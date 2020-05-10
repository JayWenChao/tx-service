import Vue from 'vue'
import Router from 'vue-router'
import CopClass from '@/components/EnterPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'CopName',
      component: CopClass
    }
  ]
})
