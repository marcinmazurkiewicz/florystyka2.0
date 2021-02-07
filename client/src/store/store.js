import { createStore } from 'vuex'
import VuexPersist from 'vuex-persist'
import userModule from "@/store/userDetails";

const vuexPersist = new VuexPersist({
    key: 'pl.egzamin-florystyka',
    storage: window.localStorage
})

export default createStore({
    modules: {
        user: userModule
    },
    plugins: [vuexPersist.plugin]
})