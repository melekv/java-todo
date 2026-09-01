<script setup>
import { ref, defineProps, defineEmits } from 'vue';

const props = defineProps({
  category: {
    type: Object,
    default: null
  }
});

defineEmits(['save']);

const name = ref(props.category?.name ?? '');

</script>

<template>
  <form class="form" @submit.prevent="$emit('save', name)">
    <div class="group">
      <label class="label" for="name">Name:</label>
      <input class="input" id="name" type="text" v-model="name" />
    </div>

    <button class="button" type="submit">Submit</button>
  </form>
</template>

<style scoped>
.form {
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 0 24px;
}

.group {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.label {
  font-size: 14px;
  font-weight: 600;
}

.input {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
  background: #333;
  color: #fff;
}

.button {
  padding: 10px 16px;
  border: 0;
  border-radius: 6px;
  background: #333;
  color: #fff;
  cursor: pointer;
}
</style>
