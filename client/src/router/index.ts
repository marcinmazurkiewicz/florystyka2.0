import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import { trackRouter } from "vue-gtag-next";
import { hasAnyRight, isLoggedUser, refreshToken } from "@/utils/authUtils";
import store from "@/store/index";

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
    path: "/register",
    name: "Register",
    component: () => import("@/views/RegisterView.vue"),
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
    name: "StudentDashboard",
    component: () => import("@/views/logged/student/StudentDashboardView.vue"),
    meta: {
      requiresAuth: true,
      allowedFor: ["ROLE_STUDENT"]
    }
  },
  {
    path: "/student/random",
    name: "StudentRandomQuestion",
    component: () => import("@/views/RandomQuestionView.vue"),
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/student/test",
    name: "StudentTest",
    component: () => import("@/views/TestView.vue"),
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/admin/questions",
    name: "AdminQuestionsView",
    component: () => import("@/views/logged/admin/AdminQuestionsView.vue"),
    meta: {
      requiresAuth: true,
      allowedFor: ["ROLE_ADMIN"]
    }
  },
  {
    path: "/admin/questions/:questionId",
    name: "AdminQuestionView",
    component: () => import("@/views/logged/admin/SingleQuestionPreview.vue"),
    meta: {
      requiresAuth: true,
      allowedFor: ["ROLE_ADMIN"]
    }
  },
  {
    path: "/admin/questions/add",
    name: "AdminAddQuestion",
    component: () => import("@/views/logged/admin/AddEditQuestionView.vue"),
    meta: {
      requiresAuth: true,
      allowedFor: ["ROLE_ADMIN"]
    }
  },
  {
    path: "/:pathMatch(.*)*",
    component: () => import("@/views/NotFoundView.vue")
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
    if (store.getters["isLogged"] && isLoggedUser()) {
      if (to.matched.some(record => record.meta.allowedFor)) {
        hasAnyRight(to.meta.allowedFor)
          ? next()
          : next({ name: "Unauthorized", params: { nextUrl: to.fullPath } });
      } else {
        next();
      }
    } else {
      refreshToken().then(status =>
        status
          ? next()
          : next({ name: "Login", params: { nextUrl: to.fullPath } })
      );
    }
  } else {
    next();
  }
});

trackRouter(router);
export default router;
