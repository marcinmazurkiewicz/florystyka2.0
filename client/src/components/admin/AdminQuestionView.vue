<template>
  <div class="mb-4 w-full h-full box-border lg:px-4 xl:px-10">
    <div class=" w-full table p-4 box-border lg:my-10 xl:p-8">
      <section>
        <h4 class="text-center text-red text-3xl">Pytanie {{question.id}}</h4>
        <p class="py-4 px-5 mt-5 mb-1 bg-dark-gray text-right"
           style="word-spacing: 0.25rem"
            v-if="question.year">Egzamin: {{question.month}}.{{question.year}}</p>
        <p class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold">
          <span v-html="question.content"></span>
          <img v-if="question.image" :src="imgUrl" class="pt-4 mx-auto" alt="obrazek do pytania"/>
        </p>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>A. <span v-html="question.answerA"></span></p>
        </div>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>B. <span v-html="question.answerB"></span></p>
        </div>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>C. <span v-html="question.answerC"></span></p>
        </div>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>D. <span v-html="question.answerD"></span></p>
        </div>
        <p class="py-4 px-5 mt-5 mb-1 bg-light-green text-black font-bold">
          Prawidłowa odpowiedź: {{question.correct}}
        </p>

      </section>
    </div>
  </div>
</template>
<script>
import {authHTTP} from "@/http";
import {useRoute} from "vue-router";

export default {
  name: 'AdminQuestionView',
  data() {
    return {
      isDataReturned: false,
      isConnectError: false,
      is404: false,
      question: {}
    }
  },
  computed: {
    imgUrl() {
      return `${process.env.VUE_APP_API_URL}${this.question.image}`;
    },
  },
  methods: {
    getQuestion(id) {
      authHTTP.get(`/api/v3/admin/questions/${id}`)
          .then((response) => {
            this.isDataReturned = true;
            this.isConnectError = false;
            this.question = response.data;
          })
          .catch((error) => {
            if (error.response) {
              this.is404 = error.response.status === 404;
            }
            this.isDataReturned = false;
            this.isConnectError = true;
          });
    }
  },
  mounted() {
    const {
      params: {questionId}
    } = useRoute();
    this.id = questionId;
    this.getQuestion(questionId);
  }
}
</script>
