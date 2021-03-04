<template>
  <div class="w-full max-w-screen-sm mx-auto my-8">
    <div
        class="text-white my-12 px-8 py-16 border border-dark-gray rounded-2xl bg-dark-gray">
      <header>
        <h1 class="text-center text-3xl pb-16">Zaloguj się</h1>
      </header>
      <div>
        <TextInput v-model="username" id="username" :error="errors['username']">
          Login
        </TextInput>
        <TextInput v-model="password" id="password" type="password" :error="errors['password']">
          Hasło
        </TextInput>

        <div v-if="loginError"
             class="my-4 p-2.5 tracking-wide text-center border-2 border-dark-red rounded-md">
          <p class="text-red font-semibold">
            Błędny login lub hasło
          </p>
        </div>

        <button class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-light-green
            rounded-xl hover:bg-dark-green hover:text-white" @click="sendLoginRequest()">
          Zaloguj
        </button>
      </div>
      <error-info v-if="isConnectError" />

    </div>
  </div>
</template>
<script lang="ts">
import ErrorInfo from "@/components/ErrorInfo.vue";
import TextInput from "@/components/custom_inputs/TextInput.vue";
import { HTTP } from "@/http"
import { defineComponent } from 'vue';
import {parseErrorRequest} from "@/utils/errorUtils";

export default defineComponent({
  name: 'LoginView',
  components: {
    ErrorInfo,
    TextInput
  },
  data() {
    return {
      username: '',
      password: '',
      isConnectError: false,
      loginError: false,
      errors: {}
    }
  },
  methods: {
    sendLoginRequest() {
      HTTP.post("api/v3/auth/login", {
        username: this.username,
        password: this.password,
      })
          .then((response) => {
            this.errors = [];
            console.log("zalogowano");
            console.log(response);
            // this.login(response);
            // this.$router.push("/admin");
          })
          .catch((e) => {
            this.errors = parseErrorRequest(e.response.data);
            this.loginError = e.response.data.error;
          });
    }
  }
});
</script>