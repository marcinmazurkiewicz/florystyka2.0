<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">
        Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź, co
        pozwoli na jej łatwiejsze zapamiętanie.
      </p>
    </header>
    <single-question-wrapper :allow-next="true" />
  </div>
</template>
<script lang="ts">
import SingleQuestionWrapper from "@/components/questions/SingleQuestionWrapper.vue";
import { defineComponent } from "vue";

export default defineComponent({
  name: "RandomQuestion",
  components: {
    SingleQuestionWrapper
  }
});
</script>
