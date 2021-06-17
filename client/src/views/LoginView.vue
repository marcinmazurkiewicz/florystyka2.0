<template>
  <view-wrapper :response-status="responseStatus">
    <div class="w-full max-w-screen-sm mx-auto my-8">
      <div
          class="text-white my-12 px-8 py-16 border border-dark-gray rounded-2xl bg-dark-gray"
      >
        <header>
          <h1 class="text-center text-3xl pb-16">Zaloguj się</h1>
        </header>
        <div>
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
          <button
              class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-light-green
            rounded-xl hover:bg-dark-green hover:text-white"
              @click="loginRequest()"
          >
            Zaloguj
          </button>
        </div>
      </div>
    </div>
  </view-wrapper>
</template>
<script lang="ts">
import TextInput from "@/components/custom_inputs/TextInput.vue";
import {defineComponent, ref, Ref} from "vue";
import {useAuthorization} from "@/composables/useAuthorization";
import ViewWrapper from "@/components/ViewWrapper.vue";
import router from "@/router/index.ts";
import {ResponseStatus} from "@/types/ResponseStatus";

export default defineComponent({
  name: "LoginView",
  components: {
    TextInput,
    ViewWrapper
  },
  setup() {
    const username: Ref<string> = ref("");
    const password: Ref<string> = ref("");
    const responseStatus: Ref<ResponseStatus> = ref(ResponseStatus.ok());
    const {sendLoginRequest} = useAuthorization();

    const loginRequest = function () {
      sendLoginRequest({username: username.value, password: password.value})
          .then(() => {
            router.push({ name: "DashboardView"});
          })
    }
    return {
      username,
      password,
      responseStatus,
      loginRequest
    };
  }
});
</script>
