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
    <connect-error-info v-if="isConnectError" />
  </div>
</template>
<script lang="ts">
import SingleQuestionWrapper from "@/components/questions/SingleQuestionWrapper.vue";
import ConnectErrorInfo from "@/components/ConnectErrorInfo.vue";
import { HTTP } from "@/http";
import { defineComponent } from "vue";

type Answer = {
  value: string;
  content: string;
};

type Question = {
  id: number;
  content: string;
  answers: Array<Answer>;
  img?: string;
};

type Solution = {
  questionId: number;
  correct: string;
  selected: string;
};

export default defineComponent({
  name: "RandomQuestion",
  components: {
    SingleQuestionWrapper,
    ConnectErrorInfo
  },
  data() {
    return {
      isDataReturned: false,
      isConnectError: false,
      question: {} as Question,
      solved: false
    };
  },
  methods: {
    newQuestion() {
      HTTP.get("/api/v3/questions/random")
        .then(response => {
          this.isDataReturned = true;
          this.isConnectError = false;
          this.solved = false;
          this.question = response.data;
        })
        .catch(() => {
          this.isConnectError = true;
          this.isDataReturned = false;
        });
    },
    setSolved(status: boolean) {
      this.solved = status;
    }
  },
  mounted() {
    this.newQuestion();
  }
});
</script>
