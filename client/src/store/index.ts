import { createStore } from "vuex";
import VuexPersist from "vuex-persist";

const vuexPersist = new VuexPersist({
  key: "pl.egzamin-florystyka",
  storage: window.localStorage
});

export default createStore({
  state: () => ({
    isLogged: false
  }),
  mutations: {
    setLogged(state, isLogged) {
      state.isLogged = isLogged;
    },
    logout(state) {
      state.isLogged = false;
    }
  },
  actions: {
    setLogged({ commit }, isLogged) {
      commit("setLogged", isLogged);
    },
    logout({ commit }) {
      commit("setLogged", false);
    }
  },
  getters: {
    isLogged: state => state.isLogged
  },
  plugins: [vuexPersist.plugin]
});
