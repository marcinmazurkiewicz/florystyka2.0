<template>
  <div>
    <banner />
    <main class="w-full max-w-screen-lg m-auto text-white">
      <section>
        <header
          class="text-center text-4xl text-red pt-16 pb-8 px-3 md:px-6 tracking-wider leading-relaxed"
        >
          Co to za strona?
        </header>

        <p class="text-justify tracking-wide leading-loose pb-8 px-6">
          Projekt powstał jako odpowiedź na brak dobrej i darmowej bazy testów
          dla technika florysty, jakie istnieją dla innych zawodów. Strona
          została napisana w 2015 roku jako projekt "po godzinach" dla
          starogardzkich florstek, przygotowujących się do testu R26 :)
        </p>

        <p class="text-justify tracking-wide leading-loose pb-8 px-6">
          Szkoda, aby praca włożona w stworzenie strony została zmarnowana,
          dlatego postanowiłem udostępnić ją innym osobom, które przygotowują
          się do egzaminu.
          <span v-if="isDataReturned"
            >W bazie znajdują się pytania z arkuszy egzaminacyjnych z lat
            {{ earliestQuestionYear }} - {{ latestQuestionYear }}.</span
          >
          Co roku w okresie przygotowań do egzaminów zawodowych korzystają z
          niej setki osób dziennie.
        </p>

        <p class="text-justify tracking-wide leading-loose pb-8 px-6">
          Jeżeli znajdziesz na stronie jakiś błąd, będę wdzięczny, jeżeli
          zgłosisz do mailowo na adres
          <a class="text-red" href="mailto:marcin@mazurkiewicz.dev"
            >marcin@mazurkiewicz.dev</a
          >. Przy opisie błędu podaj numer pytania, który znajduje się w szarym
          pasku pod każdym pytaniem. Pomoże mi to szybko i bezproblemowo
          poprawić wszystkie niedociągnięcia. Jeżeli masz dostęp do nowszych
          arkuszy testowych - dołóż cegiełkę do rozwoju strony i podziel się
          pytaniami!
        </p>
      </section>
    </main>
  </div>
</template>
<script lang="ts">
import Banner from "@/components/Banner.vue";
import { defineComponent } from "vue";
import useQuestionsStats from "../composables/useQuestionsStats";

export default defineComponent({
  name: "InfoView",
  components: {
    Banner
  },
  setup() {
    const {
      isDataReturned,
      earliestQuestionYear,
      latestQuestionYear
    } = useQuestionsStats();

    return {
      isDataReturned,
      earliestQuestionYear,
      latestQuestionYear
    };
  }
});
</script>
