import { createWebHistory, createRouter } from 'vue-router';
import Login from '@/views/Login.vue';
import Profile from '@/views/Profile.vue';
import Profiles from '@/views/Profiles.vue';
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
        name: 'profiles',
        path: '/profiles/:username',
        component: Profiles,
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
