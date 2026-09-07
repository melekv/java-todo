<script setup>
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import { useUserStore } from '../stores/userStore.js';
import { useRouter } from 'vue-router';
import LoginForm from '../components/LoginForm.vue';

const router = useRouter();
const userStore = useUserStore();

const login = async (data) => {
  try {
    await userStore.login(data);

    toast.success('Login successful');

    router.push('/');
  } catch (error) {
    console.error(error);
    toast.error('Login error');
  }
}
</script>

<template>
  <div>
    <h2 class="header">Login</h2>

    <LoginForm @login="login" />
  </div>
</template>

<style scoped>

</style>
