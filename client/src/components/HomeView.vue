<template>
  <div>
    <banner/>
    <main class="w-full max-w-screen-lg m-auto text-white">
      <section>
        <header class="text-center text-4xl text-red pt-16 pb-8 px-3 md:px-6 tracking-wider leading-relaxed">
          Przygotowujesz się do egzaminu kwalifikacyjnego z florystyki? Poćwicz testy!
        </header>

        <p v-if="isDataReturned" class="text-justify tracking-wide leading-loose px-6">W bazie znajdują się pytania z arkuszy egzaminacyjnych
          z lat <span class="text-red">{{ earliestQuestionYear }} - {{ latestQuestionYear }}</span>. Łączna liczba pytań
          w bazie to
          obecnie <span class="text-red">{{ questionNumber }}</span>. </p>
        <p class="text-justify tracking-wide leading-loose px-6">Testy możesz rozwiązywać na trzy sposoby: </p>

        <div class="py-6 w-full grid grid-col-1 md:grid-cols-3">
          <div class="text-center p-6">
            <h5 class="text-red text-2xl">Symulacja testu</h5>
            <i class="material-icons text-7xl text-light-green py-6">receipt</i>
            <p class="text-justify">Losowane jest 40 pytań, na rozwiązanie których jest 60 minut. Po upływie
              czasu test zostaje przerwany, tak jak na prawdziwym egzaminie.</p>
          </div>
          <div class="text-center p-6">
            <h5 class="text-red text-2xl">Szybki test</h5>
            <i class="material-icons text-7xl text-light-green py-6">av_timer</i>
            <p class="text-justify">Opcja dla zabieganych. Losowane jest jedno pytanie, które od razu można
              sprawdzić.</p>
          </div>
          <div class="text-center p-6">
            <h5 class="text-red text-2xl">PDF</h5>
            <i class="material-icons text-7xl text-light-green py-6">print</i>
            <p class="text-justify">Przydatne dla nauczycieli. Generuje gotowy do druku test z 40 pytaniami i
              kluczem odpowiedzi.</p>
          </div>
        </div>

        <p class="text-justify tracking-wide leading-loose pb-8 px-6">
          Jeżeli znajdziesz na stronie jakiś błąd, będę wdzięczny, jeżeli zgłosisz do mailowo na adres
          <a class="text-red" href="mailto:marcin@mazurkiewicz.dev">marcin@mazurkiewicz.dev</a>. Przy
          opisie błędu podaj numer pytania, który znajduje się w szarym pasku pod każdym pytaniem. Pomoże
          mi to szybko i bezproblemowo poprawić wszystkie niedociągnięcia.</p>
      </section>

    </main>
  </div>
</template>
<script>
import Banner from "@/components/visual/Banner";
import {HTTP} from "@/http";

export default {
  name: 'HomeView',
  components: {
    Banner
  },
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