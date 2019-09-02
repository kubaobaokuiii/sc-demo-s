import Login from './views/Login.vue'
import PersOrder from './views/PersOrder.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import User from './views/nav1/User.vue'
import Fight from './views/nav1/Fight.vue'
import Page4 from './views/nav2/viewFight.vue'
import Page5 from './views/nav2/viewUser.vue'
import Page6 from './views/nav3/Page6.vue'
import echarts from './views/charts/echarts.vue'
import Order from "./views/nav1/Order";

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    // {
    //     path: '/PersOrder',
    //     component: PersOrder,
    //     name: '',
    //     hidden: true
    // },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/main', component: Main, name: '首页', hidden: true },
            { path: '/User', component: User, name: '用户管理' },
            { path: '/Fight', component: Fight, name: '航班管理' },
            { path: '/Order', component: Order, name: '订单管理' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '查看',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/viewFight', component: Page4, name: '查看航班' },
            { path: '/viewUser', component: Page5, name: '查看用户' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            { path: '/Page6', component: Page6, name: '订单信息查看' }
        ]
    },
    // {
    //     path: '/',
    //     component: Home,
    //     name: '',
    //     iconCls: 'fa fa-address-card',
    //     leaf: true,//只有一个节点
    //     children: [
    //         {
    //             path: '/PersOrder',
    //             component: PersOrder,
    //             name: '个人订票'
    //         }
    //     ]
    // },
    // {
    //     path: '/',
    //     component: Home,
    //     name: 'Charts',
    //     iconCls: 'fa fa-bar-chart',
    //     children: [
    //         { path: '/echarts', component: echarts, name: 'echarts' }
    //     ]
    // },

    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;