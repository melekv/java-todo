<script setup>
import { ref, defineEmits } from 'vue';
import {toast} from 'vue3-toastify';

const emit = defineEmits(['save']);

const firstName = ref('');
const lastName = ref('');
const email = ref('');
const password = ref('');

const save = () => {
  if (firstName.value === '') {
    toast.error('Please enter first name');
    return;
  }

  if (lastName.value === '') {
    toast.error('Please enter last name');
    return;
  }

  if (email.value === '') {
    toast.error('Please enter email');
    return;
  }

  if (password.value === '') {
    toast.error('Please enter password');
    return;
  }

  const data = {
    firstName: firstName.value,
    lastName: lastName.value,
    email: email.value,
    password: password.value,
  };

  emit('save', data);
};

</script>

<template>
  <form class="form" @submit.prevent="save">
    <div class="group">
      <label class="label" for="first-name">First name:</label>
      <input class="input" id="first-name" type="text" v-model="firstName" />
    </div>

    <div class="group">
      <label class="label" for="last-name">Last name:</label>
      <input class="input" id="last-name" type="text" v-model="lastName" />
    </div>

    <div class="group">
      <label class="label" for="email">Email:</label>
      <input class="input" id="email" type="email" v-model="email" />
    </div>

    <div class="group">
      <label class="label" for="password">Password:</label>
      <input class="input" id="password" type="password" v-model="password" />
    </div>

    <button class="btn btn-primary" type="submit">Register</button>
  </form>
</template>

<style scoped>
</style>
