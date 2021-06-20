<template>
  <div class="w-full max-w-screen-sm mx-auto my-8">
    <div
      class="text-white my-12 px-8 pt-16 pb-8 border border-dark-gray md:rounded-2xl bg-dark-gray"
    >
      <header>
        <h1 class="text-center text-3xl pb-8">Zaloguj się</h1>
      </header>
      <error-info v-if="responseStatus.isError">
        {{ responseStatus.errorMsg }}
      </error-info>
      <div class="mt-8">
        <form>
          <TextInput
            v-model="username"
            id="username"
            :error="responseStatus.errors['username']"
          >
            Login
          </TextInput>
          <TextInput
            v-model="password"
            id="password"
            type="password"
            :error="responseStatus.errors['password']"
          >
            Hasło
          </TextInput>
        </form>
        <button
          class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-light-green
            rounded-xl hover:bg-dark-green hover:text-white"
          @click="login()"
        >
          Zaloguj
        </button>

        <p class="py-8 text-center">
          Nie posiadasz konta?
          <router-link :to="{ name: 'Register' }" class="text-red pl-2">
            Zarejestruj się
          </router-link>
        </p>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import TextInput from "@/components/custom_inputs/TextInput.vue";
import { defineComponent, ref, Ref } from "vue";
import { useAuthorization } from "@/composables/useAuthorization";
import { useRouter } from "vue-router";
import { ResponseStatus } from "@/types/ResponseStatus";
import ErrorInfo from "@/components/ErrorInfo.vue";

export default defineComponent({
  name: "LoginView",
  components: {
    ErrorInfo,
    TextInput
  },
  setup() {
    const username: Ref<string> = ref("");
    const password: Ref<string> = ref("");
    const responseStatus: Ref<ResponseStatus> = ref(ResponseStatus.ok());
    const { tryLogin } = useAuthorization();
    const router = useRouter();

    const login = function() {
      tryLogin({ username: username.value, password: password.value })
        .then(() => {
          router.push({ name: "DashboardView" });
        })
        .catch(error => {
          responseStatus.value = error.responseStatus;
        });
    };
    return {
      username,
      password,
      responseStatus,
      login
    };
  }
});
</script>
