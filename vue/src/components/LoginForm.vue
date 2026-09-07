<script setup>
import { ref, defineEmits } from 'vue';
import {toast} from 'vue3-toastify';

const emit = defineEmits(['save']);

const email = ref('');
const password = ref('');

const login = () => {
  if (email.value === '') {
    toast.error('Please enter email');
    return;
  }

  if (password.value === '') {
    toast.error('Please enter password');
    return;
  }

  const data = {
    email: email.value,
    password: password.value,
  };

  emit('login', data);
};

</script>

<template>
  <form class="form" @submit.prevent="login">
    <div class="group">
      <label class="label" for="email">Email:</label>
      <input class="input" id="email" type="email" v-model="email" />
    </div>

    <div class="group">
      <label class="label" for="password">Password:</label>
      <input class="input" id="password" type="password" v-model="password" />
    </div>

    <button class="btn btn-primary" type="submit">Login</button>
  </form>
</template>

<style scoped>
</style>
