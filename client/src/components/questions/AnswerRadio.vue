<template>
  <div>
    <label
      :for="id"
      :class="color"
      class="block w-full py-3 px-5 mb-1 bg-gray border"
    >
      <slot />
      <input
        :name="name"
        :id="id"
        type="radio"
        :value="value"
        :checked="isChecked"
        @input="$emit('update:modelValue', value)"
        class="hidden"
      />
    </label>
  </div>
</template>
<script lang="ts">
import { defineComponent, computed, toRefs } from "vue";

export default defineComponent({
  name: "AnswerRadio",
  props: {
    name: {
      type: String
    },
    id: {
      type: String
    },
    value: {
      type: String,
      required: true
    },
    modelValue: {
      type: String,
      required: true
    }
  },
  setup(props) {
    const { value, modelValue } = toRefs(props);

    const isChecked = computed(() => {
      return value.value === modelValue.value;
    });

    const color = computed(() => {
      const defaultStyle =
        "text-white border-dark-gray hover:text-azure hover:border-azure";
      const checked = "text-azure border-azure";

      if (isChecked.value) {
        return checked;
      } else {
        return defaultStyle;
      }
    });

    return { color, isChecked };
  }
});
</script>
