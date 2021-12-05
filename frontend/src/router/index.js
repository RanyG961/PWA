import { createWebHistory, createRouter } from 'vue-router';
import Login from '@/views/Login.vue';
import Profile from '@/views/Profile.vue';
import Timeline from '@/views/Timeline.vue';
import Profil from '@/views/Profil.vue';
import Home from '@/views/Home.vue';

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
    {
        name: 'profil',
        path: '/profil/:username',
        component: Profil,
        props: true,
    },
    {
        name: 'home',
        path: '/home',
        component: Home,
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
