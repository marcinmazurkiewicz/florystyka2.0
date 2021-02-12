import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/HomeView.vue")
  },
  {
    path: "/single",
    name: "RandomQuestion",
    component: () => import("@/views/HomeView.vue")
  },
  {
    path: "/question/:questionId",
    name: "SingleQuestion",
    component: () => import("@/views/HomeView.vue")
  },
  {
    path: "/info",
    name: "Info",
    component: () => import("@/views/InfoView.vue")
  },
  {
    path: "/test",
    name: "Test",
    component: () => import("@/views/HomeView.vue")
  },
  {
    path: "/pdf",
    name: "Pdf",
    component: () => import("@/views/PdfView.vue")
  },
  {
    path: "/privacy",
    name: "PrivacyPolicy",
    component: () => import("@/views/PrivacyPolicyView.vue")
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior() {
    // always scroll to top
    return { top: 0 };
  }
});

export default router;
