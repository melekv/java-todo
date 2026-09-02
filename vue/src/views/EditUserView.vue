<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import UserForm from '../components/UserForm.vue';
import { getUser, updateUser } from '../services/api.js';

const route = useRoute();
const router = useRouter();

const user = ref(null);

onMounted(async () => {
  try {
    const data = await getUser(route.params.id);

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
    await updateUser(data);

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
