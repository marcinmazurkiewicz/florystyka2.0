<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">
        Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź, co
        pozwoli na jej łatwiejsze zapamiętanie.
      </p>
    </header>
    <div v-if="isDataReturned">
      <single-question-wrapper
        :question="question"
        @solved="setSolved"
      ></single-question-wrapper>
      <button
        v-if="solved"
        @click="newQuestion"
        class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white"
      >
        Następne pytanie
      </button>
    </div>
    <error-info v-if="isConnectError" />
  </div>
</template>
<script>
import SingleQuestionWrapper from "@/components/questions/SingleQuestionWrapper";
import ErrorInfo from "@/components/visual/ConnectErrorInfo";
import { HTTP } from "@/http";

export default {
  name: "RandomQuestion",
  components: {
    SingleQuestionWrapper,
    ErrorInfo
  },
  data() {
    return {
      isDataReturned: false,
      isConnectError: false,
      question: {},
      solved: false
    };
  },
  methods: {
    newQuestion() {
      HTTP.get("/api/v3/questions/random")
        .then(response => {
          this.isDataReturned = true;
          this.isConnectError = false;
          this.solution = {};
          this.selectedAnswer = "";
          this.solved = false;
          this.question = response.data;
        })
        .catch(() => {
          this.isConnectError = true;
          this.isDataReturned = false;
        });
    },
    setSolved(status) {
      this.solved = status;
    }
  },
  mounted() {
    this.newQuestion();
  }
};
</script>
