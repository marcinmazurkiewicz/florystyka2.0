import store from "@/store/store";
import { authHTTP } from "@/http";
import { hasRight, hasAnyRight, isLoggedUser } from "@/auth/authUtils";

export default {
  methods: {
    loggedUsername() {
      return store.getters["user/getLoggedUser"].login;
    },
    userNameAndLastname() {
      return `${store.getters["user/getLoggedUser"].name} ${store.getters["user/getLoggedUser"].lastname}`;
    },
    isLoggedUser() {
      return isLoggedUser();
    },
    hasRight(permission) {
      return hasRight(permission);
    },
    hasAnyRight(permissions) {
      return hasAnyRight(permissions);
    },
    isAuthor(authorId) {
      return authorId === store.getters["user/getLoggedUser"].id;
    },
    login(response) {
      store.dispatch("user/saveToken", response.headers["authorization"]);
      store.dispatch(
        "user/saveRefreshToken",
        response.headers["refresh-token"]
      );
      store.dispatch("user/saveLoggedUser", response.data);
    },
    logout() {
      const data = {
        refreshToken: store.getters["user/getRefreshToken"]
      };
      authHTTP
        .post("api/auth/logout", data)
        .then(() => {
          store.dispatch("user/logout").then(() => {
            if (this.$route.path !== "/login") {
              this.$router.push("/login");
            }
          });
          //TODO notification success
        })
        .catch(() => {
          //TODO notification error
          store.dispatch("user/logout").then(() => {
            if (this.$route.path !== "/") {
              this.$router.push("/");
            }
          });
        });
    }
  }
};
