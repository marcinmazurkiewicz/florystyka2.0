<template>
  <div class="w-full h-full bg-deep-cyan font-lato overflow-y-auto">
    <Topbar/>
    <router-view class="min-h-4/5"/>
    <footer class="w-full bg-deep-cyan text-white text-right text-sm px-6 mt-6 mx-0 py-4">
      Florystyka 3.0.1 &copy; 2020 <a class="text-red" href="https://mazurkiewicz.dev">Marcin Mazurkiewicz</a>
    </footer>
  </div>
</template>

<script>
import Topbar from '@/components/Topbar'

export default {
  name: 'App',
  components: {
    Topbar
  }
}
</script>
