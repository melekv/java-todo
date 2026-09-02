<script setup>
import { useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import UserForm from "../components/UserForm.vue";
import { createUser } from '../services/api.js';

const router = useRouter();

const create = async (data) => {
  try {
    await createUser(data);

    toast.success('User created!');

    router.push('/users');
  } catch (error) {
    console.error(error);
  }
};

</script>

<template>
  <div>
    <h2 class="header">Add new user</h2>

    <UserForm @save="create" />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
