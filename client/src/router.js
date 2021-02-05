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
                component: AdminQuestionsView
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

trackRouter(router);
export default router;