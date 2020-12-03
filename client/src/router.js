import {createWebHistory, createRouter} from "vue-router";

const Home = import("@/components/Home.vue");
const Info = import("@/components/Info.vue");
const SingleQuestion = import("@/components/questions/SingleQuestion");
const RandomQuestion = import("@/components/questions/RandomQuestion");
const Test = import("@/components/questions/Test");

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
    // {
    //     path: "/pdf",
    //     name: "Pdf",
    //     component: SingleQuestion
    // }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;