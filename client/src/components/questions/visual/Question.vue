<template>
  <section>
    <p :id="localQuestion.id" class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold">{{ number + 1 }}.
      {{ localQuestion.content }}</p>
    <answer-radio value="A" :id='localQuestion.id +"_answerA"' :name='localQuestion.id+"_answer"'
                  v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerA
      }}
    </answer-radio>
    <answer-radio value="B" :id='localQuestion.id+"_answerB"' :name='localQuestion.id+"_answer"'
                  v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerB
      }}
    </answer-radio>
    <answer-radio value="C" ::id='localQuestion.id+"_answerC"' :name='localQuestion.id+"_answer"'
                  v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerC
      }}
    </answer-radio>
    <answer-radio value="D" :id='localQuestion.id+"_answerD"' :name='localQuestion.id+"_answer"'
                  v-model="localQuestion.selectedAnswer">{{
        localQuestion.answerD
      }}
    </answer-radio>
    <span class="block py-2 px-5 mb-1 bg-dark-gray text-xs text-right ">Pyt. {{ localQuestion.id }}</span>
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
      type: Object,
      required: true
    },
    number: {
      type: Number,
      required: false,
      default: 0
    }
  },
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