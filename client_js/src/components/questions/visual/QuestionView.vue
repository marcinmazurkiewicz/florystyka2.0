<template>
  <section>
    <p :id="question.id" class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold">{{ number + 1 }}.
      <span v-html="question.content"></span>
      <img v-if="question.img" :src="imgUrl" class="pt-4 mx-auto" alt="obrazek do pytania"/>
    </p>
    <div v-for="answer in question.answers" :key="answer.value">
      <answer-span v-if="solved" :value="answer.value" :id='question.id +"_"+answer.value' :solution="solution"
                   :checked="isChecked(answer.value)">
        <span v-html="answer.content"></span>
      </answer-span>
      <answer-radio v-else :value="answer.value" :id='question.id +"_"+answer.value' :name='question.id+"_answer"'
                    :solution="solution" v-model="localSelectedAnswer">
        <span v-html="answer.content"></span>
      </answer-radio>
    </div>
    <span class="block py-2 px-5 mb-1 bg-dark-gray text-xs text-right ">Pyt. {{ question.id }}</span>
  </section>
</template>
<script>
import AnswerRadio from "@/components/questions/visual/AnswerRadio";
import AnswerSpan from "@/components/questions/visual/AnswerSpan";

export default {
  name: 'QuestionView',
  components: {
    AnswerSpan,
    AnswerRadio
  },
  props: {
    modelValue: {
      type: String,
      required: true
    },
    number: {
      type: Number,
      required: false,
      default: 0
    },
    question: {
      type: Object,
      required: true
    },
    solution: {
      type: Object
    }
  },
  computed: {
    localSelectedAnswer: {
      get() {
        return this.modelValue
      },
      set(localSelectedAnswer) {
        this.$emit('update:modelValue', localSelectedAnswer);
      }
    },
    imgUrl() {
      return `${process.env.VUE_APP_API_URL}${this.question.img}`;
    },
    solved() {
      return this.solution != null && this.solution.correct != null;
    }
  },
  methods: {
    isChecked(value) {
      return this.localSelectedAnswer === value;
    }
  }
}
</script>
<style>
table {
  @apply w-full p-4 table font-light mt-4;
}

td, th {
  @apply table-cell p-2 border border-dark-green text-sm;
}
</style>