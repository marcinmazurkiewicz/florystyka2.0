const userModule = {
    namespaced: true,
    state: () => ({
        token: null,
        refreshToken: null,
        loggedUser: null,
    }),
    mutations: {
        saveToken(state, token) {
            state.token = token;
        },
        saveRefreshToken(state, refreshToken) {
            state.refreshToken = refreshToken;
        },
        saveLoggedUser(state, user) {
            state.loggedUser = user;
        },
        logout(state) {
            state.token = null;
            state.refreshToken = null;
            state.loggedUser = null;
        }
    },
    actions: {
        saveToken({commit}, token) {
            commit('saveToken', token);
        },
        saveRefreshToken({commit}, refreshToken) {
            commit('saveRefreshToken', refreshToken);
        },
        saveLoggedUser({commit}, user) {
            commit('saveLoggedUser', user);
        },
        logout({commit}) {
            commit('logout');
        }
    },
    getters: {
        getToken: (state) => state.token,
        getRefreshToken: (state) => state.refreshToken,
        getLoggedUser: (state) => state.loggedUser,
        isLoggedUser: (state) => state.loggedUser != null && state.loggedUser !== '',
        isTokenAvailable: (state) => state.token != null && state.token !== '',
    },
};

export  default userModule;