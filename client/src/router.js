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

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/single",
        name: "RandomQuestion",
        component: RandomQuestion

    },
    {
        path: "/question/:questionId",
        name: "SingleQuestion",
        component: SingleQuestion
    },
    {
        path: "/info",
        name: "Info",
        component: Info
    },
    {
        path: "/test",
        name: "Test",
        component: Test
    },
    {
        path: "/pdf",
        name: "Pdf",
        component: Pdf
    },
    {
        path: "/privacy",
        name: "PrivacyPolicy",
        component: PrivacyPolicy
    },
    {
        path: "/login",
        name: "Login",
        component: Login
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

trackRouter(router);
export default router;