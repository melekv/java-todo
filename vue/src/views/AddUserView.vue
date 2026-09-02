<script setup>
import { useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import UserForm from "../components/UserForm.vue";

const router = useRouter();

const create = async (data) => {
  try {
    const response = await fetch('http://localhost:8080/api/v1/users', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      body: JSON.stringify({
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
      })
    });

    if (!response.ok) {
      throw new Error('Error creating user!');
    }

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
