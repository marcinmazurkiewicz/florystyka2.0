<template>
  <div class="mx-1 md:mx-2 my-4">
    <div
      class="relative inline-block w-10 mr-2 align-middle select-none transition duration-200 ease-in"
    >
      <input
        type="radio"
        :name="name"
        :id="id"
        :value="modelValue"
        @input="$emit('update:modelValue', value)"
        :checked="isChecked"
        class="toggle-checkbox absolute block w-6 h-6 rounded-full bg-white border-4 appearance-none cursor-pointer"
      />
      <label
        :for="id"
        class="toggle-label block overflow-hidden h-6 rounded-full bg-gray-300 cursor-pointer"
      ></label>
    </div>
    <label :for="id" class="text-sm text-white">
      <slot />
    </label>
    <p v-if="error" class="text-red pt-3">
      {{ error }}
    </p>
  </div>
</template>
<script lang="ts">
import { computed, defineComponent } from "vue";

export default defineComponent({
  name: "SwitchRadio",
  props: {
    name: {
      required: true
    },
    id: {
      required: true
    },
    value: {
      required: true
    },
    modelValue: {
      required: true
    },
    error: {
      type: String,
      required: false
    }
  },
  setup(props) {
    const isChecked = computed(() => {
      return props.value === props.modelValue;
    });
    return { isChecked };
  }
});
</script>
<style scoped>
.toggle-checkbox:checked {
  @apply right-0 border-dark-green;
}

.toggle-checkbox:checked + .toggle-label {
  @apply bg-dark-green;
}
</style>
