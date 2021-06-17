<template>
  <section>
    <p
      :id="questionUnit.question.id"
      class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold"
    >
      {{ number + 1 }}.
      <span v-html="questionUnit.question.content"></span>
      <img
        v-if="questionUnit.question.img"
        :src="imgUrl"
        class="pt-4 mx-auto"
        alt="obrazek do pytania"
      />
    </p>
    <div v-for="answer in questionUnit.question.answers" :key="answer.value">
      <answer-span
        v-if="solved"
        :value="answer.value"
        :id="questionUnit.question.id + '_' + answer.value"
        :solution="questionUnit.correctAnswer"
        :checked="isChecked(answer.value)"
      >
        <span v-html="answer.content"></span>
      </answer-span>
      <answer-radio
        v-else
        :value="answer.value"
        :id="questionUnit.question.id + '_' + answer.value"
        :name="questionUnit.question.id + '_answer'"
        v-model="selectedAnswer"
      >
        <span v-html="answer.content"></span>
      </answer-radio>
    </div>
    <span class="block py-2 px-5 mb-1 bg-dark-gray text-xs text-right "
      >Pyt. {{ questionUnit.question.id }}</span
    >
  </section>
</template>
<script lang="ts">
import { defineComponent, PropType } from "vue";
import AnswerRadio from "@/components/questions/AnswerRadio.vue";
import AnswerSpan from "@/components/questions/AnswerSpan.vue";
import { QuestionUnit } from "@/types/QuestionTypes";

export default defineComponent({
  name: "Question",
  components: {
    AnswerSpan,
    AnswerRadio
  },
  emits: ["selected"],
  props: {
    number: {
      type: Number,
      required: false,
      default: 0
    },
    questionUnit: {
      type: Object as PropType<QuestionUnit>,
      required: true
    }
  },
  computed: {
    selectedAnswer: {
      get(): string {
        return this.questionUnit.selectedAnswer;
      },
      set(selectedAnswer: string): void {
        this.$emit("selected", selectedAnswer);
      }
    },
    imgUrl(): string {
      return this.questionUnit.question.img
        ? `${process.env.VUE_APP_API_URL}${this.questionUnit.question.img}`
        : "";
    },
    solved(): boolean {
      return typeof this.questionUnit.correctAnswer !== "undefined";
    }
  },
  methods: {
    isChecked(value: string): boolean {
      return this.questionUnit.selectedAnswer === value;
    }
  }
});
</script>
<style>
table {
  @apply w-full p-4 table font-light mt-4;
}

td,
th {
  @apply table-cell p-2 border border-dark-green text-sm;
}
</style>
