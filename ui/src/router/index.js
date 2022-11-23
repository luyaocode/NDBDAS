import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import ParentView from '@/components/ParentView';
import InnerLink from '@/layout/components/InnerLink'

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    noCache: true                // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
  }
 */

// 公共路由
export const constantRoutes = [
  //自定义路由
  {
    path: '/operation',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '',
        component: (resolve) => require(['@/views/operation'], resolve),
        name: 'operation',
        meta: {title: '操作控制'}
      }
    ]
  },
  //通过楼号-楼层号-设备号定位到具体设备
  {
    path: '/building',
    component: Layout,
    hidden: true,
    children: [
      // {
      //   path: '',
      //   component: (resolve) => require(['@/views/building'], resolve),
      //   name: 'floor',
      //   meta: {title: '选择楼层'}
      // },
      //切记：子路由不要再写/
      {
        path: 'no1',
        component: (resolve) => require(['@/views/building/no1'], resolve),
        name: 'no1',
        meta: {title: '1号楼'},
      },
      {
        path: 'no2',
        component: (resolve) => require(['@/views/building/no2'], resolve),
        name: 'no2',
        meta: {title: '2号楼'}
      },
      {
        path: 'no3',
        component: (resolve) => require(['@/views/building/no3'], resolve),
        name: 'no3',
        meta: {title: '3号楼'}
      },
      {
        path: 'no4',
        component: (resolve) => require(['@/views/building/no4'], resolve),
        name: 'no4',
        meta: {title: '4号楼'}
      },
      {
        path: 'no5',
        component: (resolve) => require(['@/views/building/no5'], resolve),
        name: 'no5',
        meta: {title: '5号楼'}
      },
      {
        path: 'no1/f1',
        component: (resolve) => require(['@/views/building/no1/f1'], resolve),
        name: 'no1-f1',
        meta: {title: '1号楼-1层'},
      },


    ]
  },
  //设备之状态显示、操作模块
  {
    path: '/device',
    component: Layout,
    hidden: true,
    children: [
      {
        //:id写法
        path: 'show/:id',
        component: (resolve) => require(['@/views/device/show.vue'], resolve),
        name: 'DeviceInfo',
        meta: {title: '设备详情'},
      }
    ]

  },

  //设备管理
  {
    path: '/devmanage',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '',
        component: (resolve) => require(['@/views/devmanage/index.vue'], resolve),
        name: 'DeviceManagement',
        meta: {title: '设备管理'},
      }
    ]
  },
  //设备管理
  {
    path: '/tool',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'chat',
        component: (resolve) => require(['@/views/tool/chat/index.vue'], resolve),
        name: 'Chat',
        meta: {title: '通讯工具'},
      }
    ]
  },


  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: (resolve) => require(['@/views/redirect'], resolve)
      }
    ]
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  //首页的路由
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/index'], resolve),
        name: '首页',
        meta: {title: '首页', icon: 'dashboard', noCache: true, affix: true}
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
        name: 'Profile',
        meta: {title: '个人中心', icon: 'user'}
      }
    ]
  },
  {
    path: '/auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: (resolve) => require(['@/views/system/user/authRole'], resolve),
        name: 'AuthRole',
        meta: {title: '分配角色'}
      }
    ]
  },
  {
    path: '/auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: (resolve) => require(['@/views/system/role/authUser'], resolve),
        name: 'AuthUser',
        meta: {title: '分配用户'}
      }
    ]
  },
  {
    path: '/dict',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'type/data/:dictId(\\d+)',
        component: (resolve) => require(['@/views/system/dict/data'], resolve),
        name: 'Data',
        meta: {title: '字典数据', icon: ''}
      }
    ]
  },
  {
    path: '/gen',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'edit/:tableId(\\d+)',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        name: 'GenEdit',
        meta: {title: '修改生成配置'}
      }
    ]
  },


]

export default new Router({
  base: "",
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})
