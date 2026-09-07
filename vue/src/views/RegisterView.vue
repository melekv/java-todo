<script setup>
import RegisterForm from '../components/RegisterForm.vue';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import { useUserStore } from '../stores/userStore.js';
import { useRouter } from 'vue-router';

const router = useRouter();
const userStore = useUserStore();

const save = async (data) => {
  try {
    await userStore.createUser(data);

    toast.success('User created successfully');

    router.push('/auth/login');
  } catch (error) {
    console.error(error);
    toast.error('Error creating user');
  }
}
</script>

<template>
  <div>
    <h2 class="header">Register</h2>

    <RegisterForm @save="save" />
  </div>
</template>

<style scoped>

</style>
