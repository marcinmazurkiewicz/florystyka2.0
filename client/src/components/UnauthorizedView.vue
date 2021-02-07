<template>
  <div class="w-auto m-6 text-white flex flex-col justify-center">
    <h1 class="text-9xl text-center font-bold text-navy-700 leading-48">403</h1>
    <p class="text-2xl text-navy-700 text-center px-16 pt-4 text-center">
      Nie posiadasz odpowiednich uprawnień, aby przejść na stronę {{showBlockedUrl}}.
    </p>
  </div>
</template>
<script>
export default {
  name: 'Unauthorized',
  props: ["nextUrl"],
  computed: {
    showBlockedUrl() {
      return this.nextUrl ? ` '${this.nextUrl}'` : '';
    }
  }
}
</script>