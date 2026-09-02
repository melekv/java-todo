<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import UserForm from "../components/UserForm.vue";

const route = useRoute();
const router = useRouter();

const user = ref(null);

onMounted(async () => {
  try {
    const response = await fetch(`http://localhost:8080/api/v1/users/${route.params.id}`);

    if (!response.ok) {
      throw new Error('Error fetching user!');
    }

    const data = await response.json();

    user.value = {
      id: data.id,
      firstName: data.firstName,
      lastName: data.lastName,
      email: data.email,
      active: data.active,
    };
  } catch (error) {
    console.error(error);
  }
});

const update = async (data) => {
  try {
    const response = await fetch(`http://localhost:8080/api/v1/users/${data.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      body: JSON.stringify({
        firstName: data.firstName,
        lastName: data.lastName,
        active: data.active,
      })
    });

    if (!response.ok) {
      throw new Error('Error updating user!');
    }

    toast.success('User changed!');

    router.push('/users');
  } catch (error) {
    console.error(error);
  }
};

</script>

<template>
  <div>
    <h2 class="header">Edit user</h2>

    <UserForm v-if="user" :user="user" @save="update" />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
