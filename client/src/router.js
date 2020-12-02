import {createWebHistory, createRouter} from "vue-router";
import Home from "@/components/Home.vue";
import Info from "@/components/Info.vue";
import SingleQuestion from "@/components/questions/SingleQuestion"

const routes = [
    {
        path: "/",
        name: "Home",
        component: Home,
    },
    {
        path: "/single",
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
        component: SingleQuestion
    },
    {
        path: "/pdf",
        name: "Pdf",
        component: SingleQuestion
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;