<template>
  <div>
    <span v-if="solved"
          :class="[correct ? 'bg-dark-green' : 'bg-red']"
          class="block w-full mt-8 p-3 text-dark-gray text-lg text-center font-semibold">
      <span v-if="correct">Dobrze :)</span>
      <span v-else>Błędna odpowiedź :(</span>
    </span>
    <question-view :question="question" :solution="solution" v-model="selectedAnswer"></question-view>
    <button v-if="!solved" @click="submitAnswer"
            class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white">
      Sprawdź
    </button>
  </div>
</template>
<script>
import QuestionView from "@/components/questions/visual/QuestionView";
import {HTTP} from "@/http";

export default {
  name: 'RandomQuestion',
  props: ['question'],
  components: {
    QuestionView,
  },
  data() {
    return {
      selectedAnswer: '',
      solution: {},
      allowNext: true
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
      const data = {
        questionId: this.question.id,
        selectedAnswer: this.selectedAnswer
      }
      HTTP.post(`/api/v3/solutions/single`, data)
          .then((response) => {
            this.solution = response.data;
            this.$emit('solved', true);
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
          });
    }
  },
  watch: {
    question: function () {
      this.solution = {};
      this.selectedAnswer = '';
    }
  }
}
</script>