<template>
  <section>
    <p class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold">{{ localQuestion.content }}</p>
    <answer-radio value="A" id="answerA" name="answer" v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerA
      }}
    </answer-radio>
    <answer-radio value="B" id="answerB" name="answer" v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerB
      }}
    </answer-radio>
    <answer-radio value="C" id="answerC" name="answer" v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerC
      }}
    </answer-radio>
    <answer-radio value="D" id="answerD" name="answer" v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerD
      }}
    </answer-radio>
    <span class="block py-2 px-5 mb-1 bg-dark-gray text-xs text-right ">Pyt. {{ localQuestion.id }}</span>
  </section>
</template>
<script>
import AnswerRadio from "@/components/visual/AnswerRadio";

export default {
  components: {
    AnswerRadio
  },
  props: ['modelValue'],
  computed: {
    localQuestion: {
      get() {
        return this.modelValue
      },
      set(localQuestion) {
        this.$emit('update:modelValue', localQuestion);
      }

    }
  }
}
</script>