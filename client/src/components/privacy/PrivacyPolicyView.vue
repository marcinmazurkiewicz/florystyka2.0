<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4 mt-6">

    <privacy-settings/>

    <h1 class="text-center font-bold text-xl block py-6">Polityka prywatności i plików cookie</h1>

    <p class="paragraph">Niniejsza polityka prywatności i plików cookie odnosi się do witryny egzamin-florystyka.pl,
      zwanej dalej Stroną.</p>
    <p class="paragraph">Polityka prywatności i plików cookie określa zbieranie, przetwarzanie i wykorzystywanie danych
      osobowych
      użytkownika, który korzysta ze Strony, a także wyjaśnia praktyki odnoszące się do wstawiania plików
      cookie oraz sposobu ich wykorzystania.</p>

    <h3 class="header">Zbieranie, przetwarzanie i wykorzystywanie danych osobowych</h3>

    <p class="paragraph">Stronę egzamin-florystyka.pl można odwiedzać z zachowaniem anonimowości oraz bez podawania
      swoich danych osobowych.
      Jednak podczas pierwszej wizyty na Stronie użytkownik zostanie poproszony o wyrażenie zgody na umieszczenie plików
      cookie na
      urządzeniu oraz śledzenie zachowania w witrynie za pomocą Google Analytics. Dzięki tym informacjom jestem w stanie
      monitorować ruch na Stronie i dostosowywać bieżące zasoby serwerowe do aktualnego zapotrzebowania.</p>

    <p class="paragraph">Jeżeli użytkownik nie wyraża zgody na powyższe praktyki i chce pozostać całkowicie anonimowy,
      może nie wyrazić
      zgody (lub cofnąć już wyrażoną zgodę) w ustawieniach prywatności. W takim przypadku nie zostaną zebrane żadne dane
      o ruchu użytkownika na Stronie.</p>

    <h3 class="header">Pliki cookie</h3>

    <p class="paragraph">Cookies to małe pliki, zapisywane przez przeglądarkę na urządzeniu. Pliki cookies są kojarzone
      wyłącznie z przeglądarką konkretnego urządzenia, bez podawania imienia, nazwiska ani innych danych osobowych
      użytkownika.
    </p>

    <p class="paragraph">Strona korzysta z Google Analytics, usługi internetowej dostarczanej przez Google Inc. Usługa
      Google Analytics wykorzystuje pliki cookie do analizowania sposobu korzystania ze Strony przez użytkowników, w tym liczby
      użytkowników odwiedzających, czasu spędzonego na Stronie oraz zewnętrznych stron internetowych, z których użytkownik został
      przekierowany. Te informacje są wykorzystywane do analizy ruchu oraz zapwenienia ciągłej dostępności Strony.
      Zebrane informacje dotyczące korzystania ze Strony są przekazywane i przechowywane przez firmę Google na serwerach w
      Stanach Zjednoczonych. Adres IP przekazywany przez przeglądarkę w ramach usługi Google Analytics nie jest powiązany z
      żadnymi innymi danymi przechowywanymi przez firmę Google.</p>

    <p class="paragraph">Można zrezygnować ze śledzenia przez Google Analytics, zmieniając swoje ustawienia
      prywatności.</p>

    <h3 class="header">3. Przechowywanie danych osobowych i bezpieczeństwo danych</h3>

    <p class="paragraph">3.1 Za wyjątkiem danych zbieranych przez usługę Google Analytics, Strona nie zbiera oraz nie
      przechowuje żadnych
      danych użytkownika.</p>

  </div>

</template>
<script>
import PrivacySettings from "@/components/privacy/PrivacySettings";

export default {
  name: 'PrivacyPolicyView',
  components: {
    PrivacySettings
  }
}
</script>
<style>
.paragraph {
  @apply text-justify leading-7 py-3;
}

.header {
  @apply py-6 text-center font-semibold;
}
</style>