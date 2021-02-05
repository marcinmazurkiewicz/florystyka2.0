<template>
  <div class=" grid grid-cols-dashboard">
    <nav id="admin-nav" class="bg-light-green">
      <ol>
        <li><router-link :to="{name: 'AdminQuestionsView'}"
                         class="block py-3 px-2 md:px-4 md:py-4 md:py4hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                         active-class="bg-dark-green">Pytania</router-link></li>
        <li><a href="#">Użytkownicy</a></li>
        <li><a href="#">Ustawienia</a></li>
        <li><a href="#" @click="logout">Wyloguj</a></li>
      </ol>

    </nav>
    <div class="w-full m-auto text-white">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
import {HTTP} from "@/http";
import userUtils from "@/mixins/userUtils";

export default {
  name: 'AdminDashboard',
  mixins: [userUtils],
  data() {
    return {
      isDataReturned: false,
      questionNumber: 0,
      earliestQuestionYear: 0,
      latestQuestionYear: 0
    }
  },
  mounted() {
    HTTP.get('/api/v3/questions/info')
        .then((response) => {
          this.isDataReturned = true;
          this.questionNumber = response.data.questionNumber;
          this.earliestQuestionYear = response.data.earliestQuestionYear;
          this.latestQuestionYear = response.data.latestQuestionYear;
        })
        .catch(() => {
          this.isDataReturned = false;
          console.log("Wystąpił błąd połączenia z serwerem");
        });
  }
}
</script>