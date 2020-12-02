<template>
  <section>
    <p :id="question.id" class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold">{{ number + 1 }}.
      {{ question.content }}</p>
    <div v-for="answer in question.answers" :key="answer.value">
      <answer-radio :value="answer.value" :id='question.id +"_"+answer.value' :name='question.id+"_answer"'
                    v-model="localSelectedAnswer">{{
          answer.content
        }}
      </answer-radio>
    </div>
    <span class="block py-2 px-5 mb-1 bg-dark-gray text-xs text-right ">Pyt. {{ question.id }}</span>
  </section>
</template>
<script>
import AnswerRadio from "@/components/questions/visual/AnswerRadio";

export default {
  name: 'Question',
  components: {
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

    }
  }
}
</script>