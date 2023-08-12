import { createRouter, createWebHistory } from 'vue-router'
import AnnouncementDetail from '../views/AnnouncementDetail.vue'
import NotFound from '../views/NotFound.vue'
import TableAnnouncement from '../views/TableAnnouncement.vue'
import AnnouncementAdd from '../views/AnnouncementAdd.vue'
import AnnouncementEdit from '../views/AnnouncementEdit.vue'
import UserAnnouncements from '../views/UserAnnouncements.vue'
import UserAnnouncementDetailActive from '../views/UserAnnouncementDetailActive.vue'
import UserAnnouncementDetailClose from '../views/UserAnnouncementDetailClose.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
        path:'/admin/announcement',
        name:'TableAnnouncement',
        component:TableAnnouncement
    },
    {
        path: '/admin/announcement/:id',
        name: 'AnnouncementDetail',
        component: AnnouncementDetail,
        props: true
    },
    {
        path:'/admin/announcement/add',
        name: 'AnnouncementAdd',
        component: AnnouncementAdd
    },
    {
        path:'/admin/announcement/:id/edit',
        name: 'AnnouncementEdit',
        component: AnnouncementEdit,
        props: true
    },
    {
        path: '/:catchNotMathPath(.*)',
        name: 'NotFound',
        component: NotFound
    },
    {
        path: '/announcement',
        name: 'UserAnnouncements',
        component: UserAnnouncements,
        props: true
    },
    {
        path: '/announcement/:id',
        name: 'UserAnnouncementDetailActive',
        component: UserAnnouncementDetailActive,
        props: true
    },
    {
        path: '/announcement/:id',
        name: 'UserAnnouncementDetailClose',
        component: UserAnnouncementDetailClose,
        props: true
    }
  ]
})

export default router