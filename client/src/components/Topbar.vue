<template>
  <div class="w-full sticky top-0 bg-light-green flex justify-between items-stretch border-b-2 border-green">
    <p class="p-4 text-xl">Testy florystyczne</p>
    <nav class="w-full self-stretch max-w-screen-lg text-right mx-auto">
      <ul class="uppercase h-full">
        <li class="inline-block h-full">
          <router-link :to="{name: 'Home'}"
                       class="block px-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-green">
            <span class="block self-center">Start</span>
          </router-link>
        </li>
        <li class="inline-block h-full">
          <router-link :to="{name: 'Test'}"
                       class="block px-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-green">
            <span class="block self-center">Test</span>
          </router-link>
        </li>
        <li class="inline-block h-full">
          <router-link :to="{name: 'SingleQuestion'}"
                       class="block px-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-green">
            <span class="block self-center">Szybko</span>
          </router-link>
        </li>
        <li class="inline-block h-full">
          <router-link :to="{name: 'Pdf'}"
                       class="block px-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-green">
            <span class="block self-center">Pdf</span>
          </router-link>
        </li>
        <li class="inline-block h-full">
          <router-link :to="{name: 'Info'}"
                       class="block px-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
                       exact-active-class="bg-green">
            <span class="block self-center">Info</span>
          </router-link>
        </li>
      </ul>
    </nav>
  </div>
</template>