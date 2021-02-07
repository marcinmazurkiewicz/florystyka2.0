import {createWebHistory, createRouter} from "vue-router";
import {trackRouter} from "vue-gtag-next";

const Home = () => import("@/components/HomeView.vue");
const Info = () => import("@/components/InfoView.vue");
const Pdf = () => import("@/components/PdfView.vue");
const SingleQuestion = () => import("@/components/questions/SingleQuestion");
const RandomQuestion = () => import("@/components/questions/RandomQuestion");
const Test = () => import("@/components/questions/Test");
const PrivacyPolicy = () => import("@/components/privacy/PrivacyPolicyView")
const Login = () => import("@/components/LoginView")
const AdminDashboard = () => import("@/components/admin/Dashboard")
const AdminQuestionsView = () => import("@/components/admin/AdminQuestionsView")
const Unauthorized = () => import("@/components/UnauthorizedView")
const AdminQuestionView = () => import("@/components/admin/AdminQuestionView")
const AddEditQuestion = () => import("@/components/admin/AddEditQuestion")

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/single",
        name: "RandomQuestion",
        component: RandomQuestion,
        meta: {
            requiresAuth: false
        }

    },
    {
        path: "/question/:questionId",
        name: "SingleQuestion",
        component: SingleQuestion,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/info",
        name: "Info",
        component: Info,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/test",
        name: "Test",
        component: Test,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/pdf",
        name: "Pdf",
        component: Pdf,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/privacy",
        name: "PrivacyPolicy",
        component: PrivacyPolicy,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/login",
        name: "Login",
        component: Login,
        meta: {
            requiresAuth: false
        }
    },
    {
        path: "/admin",
        name: "AdminDashboard",
        component: AdminDashboard,
        meta: {
            requiresAuth: true,
            allowedFor: ['ROLE_ADMIN']
        },
        children: [
            {
                path: "questions",
                name: "AdminQuestionsView",
                component: AdminQuestionsView,
                meta: {
                    requiresAuth: true,
                    allowedFor: ['ROLE_ADMIN']
                },
            },
            {
                path: "questions/add",
                name: "AddQuestion",
                component: AddEditQuestion,
                meta: {
                    requiresAuth: true,
                    allowedFor: ['ROLE_ADMIN']
                },
            },
            {
                path: "questions/:questionId",
                name: "AdminQuestionView",
                component: AdminQuestionView,
                meta: {
                    requiresAuth: true,
                    allowedFor: ['ROLE_ADMIN']
                },
            },
            {
                path: 'unauthorized',
                name: 'Unauthorized',
                component: Unauthorized,
                props: true,
                meta: {
                    requiresAuth: true
                }
            },
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        if (!isLoggedUser()) {
            next({
                name: 'Login',
                params: {nextUrl: to.fullPath}
            })
        } else if (to.matched.some(record => record.meta.allowedFor)) {
            if (hasAnyRight(to.meta.allowedFor)) {
                next()
            } else {
                next({
                    name: 'Unauthorized',
                    params: {nextUrl: to.fullPath}
                })
            }
        } else {
            next();
        }
    } else {
        next();
    }
})

trackRouter(router);
export default router;