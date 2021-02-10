<template>
  <div class="w-full m-auto text-white p-4">
    <div class="block w-full flex items-center">
      <router-link :to="{name: 'AddQuestion'}">Dodaj pytanie</router-link>
      <ul class="block">
        <li class="p-4 inline-block" @click="goToPage(1)">&#10092;&#10092;</li>
        <li :class="n === currentPage ? 'bg-light-green' : 'bg-light-gray'" class="p-4 inline-block"
            v-for="n in nearbyPages()" :key="n">
          {{ n }}
        </li>
        <li class="p-4 inline-block" @click="goToPage(this.totalPages)">&#10093;&#10093;</li>
      </ul>
    </div>

    <div class="w-full table mb-4">
      <table class="w-full text-center text-sm break-words table-auto">
        <thead>
        <tr class="border-b-2 border-gray-300">
          <th class="pt-4 pb-3 px-2 pl-4">Lp</th>
          <th class="cell">ID</th>
          <th class="cell">Treść pytania</th>
          <th class="cell">Odpowiedź A</th>
          <th class="cell">Odpowiedź B</th>
          <th class="cell">Odpowiedź C</th>
          <th class="cell">Odpowiedź D</th>
          <th class="cell">Poprawna odpowiedź</th>
          <th class="cell">Rok egzaminu</th>
          <th class="cell">Miesiąc egzaminu</th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <tr
            class="border-b border-gray-300 odd:bg-honey-100"
            v-for="(question, index) in questions"
            :key="question.id"
        >
          <td class="cell pl-4" @click="goToDetails(question.id)">{{ index + 1 }}.</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.id }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.content }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.answerA }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.answerB }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.answerC }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.answerD }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.correct }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.year }}</td>
          <td class="cell" @click="goToDetails(question.id)">{{ question.month }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import {authHTTP} from "@/http";

export default {
  name: 'AdminQuestionsView',
  data() {
    return {
      questions: [],
      totalPages: 0,
      currentPage: 0
    }
  },
  methods: {
    nearbyPages() {
      const result = [];
      if (this.totalPages < 5) {
        for (let i = 1; i <= this.totalPages; i++) {
          result.push(i);
        }
      } else if (this.currentPage < 2) {
        result.push(1, 2, 3, 4, 5);
      } else if (this.currentPage > this.totalPages - 2) {
        result.push(this.totalPages - 4, this.totalPages - 3, this.totalPages - 2, this.totalPages - 1, this.totalPages);
      } else {
        result.push(this.currentPage - 2, this.currentPage - 1, this.currentPage, this.currentPage + 1, this.currentPage + 2);
      }
      return result;
    },
    goToDetails(questionId) {
      this.$router.push({name: 'AdminQuestionView', params: {questionId: questionId}});
    },
    goToPage(page) {
      authHTTP.get(`/api/v3/admin/questions?page=${page - 1}`)
          .then((response) => {
            this.questions = response.data.content;
            this.totalPages = response.data.totalPages;
            this.currentPage = response.data.number + 1
          })
          .catch(() => {
            console.log("Wystąpił błąd połączenia z serwerem");
          });
    }
  },
  mounted() {
    this.goToPage(1);
  }
}
</script>
<style>
.cell {
  @apply pt-4 pb-3 px-2 cursor-pointer;
}
</style>
