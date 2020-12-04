<template>
  <div
      class="w-full sticky top-0 bg-light-green flex flex-col-reverse md:flex-row justify-between items-stretch border-b-2 border-dark-green">
    <p v-if='this.$route.path ==="/test"' class="p-4 pl-6 text-xl" id="countdown"></p>
    <p class="hidden md:block p-4 pl-6 text-xl" v-if='this.$route.path !=="/test"'>Testy florystyczne</p>
    <nav class="self-stretch max-w-screen-lg text-center md:text-right md:pr-6">
      <ul class="uppercase h-full">
        <li class="inline-block h-full">
          <router-link :to="{name: 'Home'}"
                       class="block p-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-dark-green">
            <span class="block self-center">Start</span>
          </router-link>
        </li>
        <li class="inline-block h-full">
          <router-link :to="{name: 'Test'}"
                       class="block p-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-dark-green">
            <span class="block self-center">Test</span>
          </router-link>
        </li>
        <li class="inline-block h-full">
          <router-link :to="{name: 'RandomQuestion'}"
                       class="block p-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-dark-green">
            <span class="block self-center">Szybko</span>
          </router-link>
        </li>
        <!--        <li class="inline-block h-full">-->
        <!--          <router-link :to="{name: 'Pdf'}"-->
        <!--                       class="block p-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"-->
        <!--                       exact-active-class="bg-dark-green">-->
        <!--            <span class="block self-center">Pdf</span>-->
        <!--          </router-link>-->
        <!--        </li>-->
        <li class="inline-block h-full">
          <router-link :to="{name: 'Info'}"
                       class="block p-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-dark-green">
            <span class="block self-center">Info</span>
          </router-link>
        </li>
      </ul>
    </nav>
  </div>
</template>