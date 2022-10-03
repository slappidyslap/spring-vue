import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    {
        path: '/',
        component: () => import('@/views/HomeView.vue')
    },
    {
        path: '/:username',
        component: () => import('@/views/UserProfileView.vue')
    },
    {
        path: '/login',
        component: () => import('@/views/SignInView.vue')
    },
    {
        path: '/signup',
        component: () => import('@/views/SignUpView.vue')
    },
    {
        path: '/test-auth',
        component: () => import('@/views/AuthTestView.vue')
    },
    {
        path: '/zamenas',
        component: () => import('@/views/ZamenaView')
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

const publicViews = ['/login', '/', '/signup', '/test-auth', '/eld', '/tom', '/bob', '/zamenas'];

router.beforeEach((to, from, next) => {

    function isNotAuthenticated() {
        const isLoggedIn = localStorage.getItem('authUserData');
        const authRequired = !publicViews.includes(to.path);

        return !isLoggedIn && authRequired;
    }
    

    if (isNotAuthenticated()) {
        next({ path: '/login' });
    } else {
        next();
    }

});

export default router;
