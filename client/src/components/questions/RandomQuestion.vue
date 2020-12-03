<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź,
        co pozwoli na jej łatwiejsze zapamiętanie.</p>
    </header>
    <span v-if="solved"
          :class="[correct ? 'bg-dark-green' : 'bg-red']"
          class="block w-full mt-8 p-3 text-dark-gray text-lg text-center font-semibold">
      <span v-if="correct">Dobrze :)</span>
      <span v-else>Błędna odpowiedź :(</span>
    </span>
    <question :question="question" :solution="solution" v-model="selectedAnswer"></question>
    <button v-if="solved" @click="newQuestion"
            class="w-full bg-yellow-300 mt-8 p-3 text-dark-gray text-lg font-semibold border border-yellow-400
            rounded-xl hover:bg-yellow-400 hover:text-white">
      Następne pytanie
    </button>
    <button v-else @click="submitAnswer"
            class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white">
      Sprawdź
    </button>
  </div>
</template>
<script>
import Question from "@/components/questions/visual/Question";
import {HTTP} from '@/http';

export default {
  name: 'RandomQuestion',
  components: {
    Question,
  },
  data() {
    return {
      question: {},
      selectedAnswer: '',
      solution: {}
    }
  },
  computed: {
    solved() {
      return this.solution.correct != null;
    },
    correct() {
      return this.solution.correct === this.selectedAnswer;
    }
  },
  methods: {
    submitAnswer() {
      const request = {
        questionId: this.question.id,
        selectedAnswer: this.selectedAnswer
      }
      HTTP.post(`api/v3/solutions/single`, request)
          .then((response) => {
            this.solution = response.data;
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
          });
    },
    newQuestion() {
      HTTP.get('api/v3/questions/random')
          .then((response) => {
            this.solution = {};
            this.selectedAnswer = '';
            this.question = response.data;
          });
    }
  },
  mounted() {
    this.newQuestion();
  }
}
</script>