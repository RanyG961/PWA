import { createWebHistory, createRouter } from 'vue-router';
import Login from '@/views/Login.vue';
import Profile from '@/views/Profile.vue';
import Timeline from '@/views/Timeline.vue';

const routes = [
    {
        name: 'login',
        path: '/',
        component: Login,
    },
    {
        name: 'profile',
        path: '/profile',
        component: Profile,
        props: true,
    },
    {
        name: 'timeline',
        path: '/timeline',
        component: Timeline,
        props: true,
    },
];

const router = createRouter({
    history: createWebHistory(
        process.env.BASE_URL === '/appvue' ? '/vue' : undefined
    ),
    routes,
});

export default router;
