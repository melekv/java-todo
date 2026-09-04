<script setup>
import { ref, defineEmits } from 'vue';

defineEmits(['save']);

const name = ref('');

</script>

<template>
  <form class="form" @submit.prevent="$emit('save', name)">
    <div class="group">
      <label class="label" for="name">Name:</label>
      <input class="input" id="name" type="text" v-model="name" />
    </div>

    <button class="btn btn-primary" type="submit">Submit</button>
  </form>
</template>

<style scoped>
</style>
