<template>

  <div class="w-full m-auto text-white">
    <div v-for="question in questions" :key="question.id">
      <p>{{question.id}} {{question.content}}</p>
      <p>{{question.answers[0].value}}. {{question.answers[0].content}}</p>
      <p>{{question.answers[1].value}}. {{question.answers[1].content}}</p>
      <p>{{question.answers[2].value}}. {{question.answers[2].content}}</p>
      <p>{{question.answers[3].value}}. {{question.answers[3].content}}</p>
    </div>
  </div>

</template>
<script>
import {authHTTP} from "@/http";

export default {
  name: 'AdminQuestionsView',
  data() {
    return {
      isDataReturned: false,
      questions: [],

    }
  },
  mounted() {
    authHTTP.get('/api/v3/admin/questions')
        .then((response) => {
          this.isDataReturned = true;
          this.questions = response.data;
        })
        .catch(() => {
          this.isDataReturned = false;
          console.log("Wystąpił błąd połączenia z serwerem");
        });
  }
}
</script>