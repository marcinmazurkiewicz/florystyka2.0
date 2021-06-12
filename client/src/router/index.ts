import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { trackRouter } from "vue-gtag-next";
import { isLoggedUser, hasAnyRight } from "@/utils/authUtils";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "Home",
    component: () => import("@/views/HomeView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/random",
    name: "RandomQuestion",
    component: () => import("@/views/RandomQuestionView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/question/:questionId",
    name: "SingleQuestion",
    component: () => import("@/views/SingleQuestionView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/info",
    name: "Info",
    component: () => import("@/views/InfoView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/test",
    name: "Test",
    component: () => import("@/views/TestView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/pdf",
    name: "Pdf",
    component: () => import("@/views/PdfView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/privacy",
    name: "PrivacyPolicy",
    component: () => import("@/views/PrivacyPolicyView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/LoginView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/unauthorized",
    name: "Unauthorized",
    component: () => import("@/views/UnauthorizedView.vue"),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/dashboard",
    name: "DashboardView",
    component: () => import("@/views/logged/DashboardView.vue"),
    meta: {
      requiresAuth: true,
      allowedFor: ["ROLE_ADMIN"]
    },
    children: [
      {
        path: "/questions",
        name: "AdminQuestionsView",
        component: () => import("@/views/logged/admin/AdminQuestionsView.vue"),
        meta: {
          requiresAuth: true,
          allowedFor: ["ROLE_ADMIN"]
        }
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  scrollBehavior() {
    return { top: 0 };
  }
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!isLoggedUser()) {
      next({
        name: "Login",
        params: { nextUrl: to.fullPath }
      });
    } else if (to.matched.some(record => record.meta.allowedFor)) {
      if (hasAnyRight(to.meta.allowedFor)) {
        next();
      } else {
        next({
          name: "Unauthorized",
          params: { nextUrl: to.fullPath }
        });
      }
    } else {
      next();
    }
  } else {
    next();
  }
});

trackRouter(router);
export default router;
