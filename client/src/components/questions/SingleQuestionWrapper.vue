<template>
  <div>
    <span
      v-if="solved"
      :class="[correct ? 'bg-dark-green' : 'bg-red']"
      class="block w-full mt-8 p-3 text-dark-gray text-lg text-center font-semibold"
    >
      <span v-if="correct">Dobrze :)</span>
      <span v-else>Błędna odpowiedź :(</span>
    </span>
    <question
      :question="question"
      :solution="solution"
      v-model="selectedAnswer"
    ></question>
    <button
      v-if="!solved"
      @click="submitAnswer"
      class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white"
    >
      Sprawdź
    </button>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Question from "@/components/questions/SingleQuestion.vue";
import { HTTP } from "@/http";

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
  correct?: string;
  selected?: string;
};

export default defineComponent({
  name: "RandomQuestion",
  props: ["question"],
  components: {
    Question
  },
  data() {
    return {
      selectedAnswer: "",
      solution: {} as Solution,
      allowNext: true
    };
  },
  computed: {
    solved(): boolean {
      return this.solution.correct != null;
    },
    correct(): boolean {
      return this.solution.correct === this.selectedAnswer;
    }
  },
  methods: {
    submitAnswer() {
      const data = {
        questionId: this.question.id,
        selectedAnswer: this.selectedAnswer
      };
      HTTP.post(`/api/v3/solutions/single`, data).then(response => {
        this.solution = response.data;
        this.$emit("solved", true);
      });
    }
  },
  watch: {
    question: function() {
      this.solution = { questionId: this.question.id };
      this.selectedAnswer = "";
    }
  }
});
</script>
