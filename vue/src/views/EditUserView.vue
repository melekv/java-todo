<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import UserForm from '../components/UserForm.vue';
import { useUserStore } from '../stores/userStore.js';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

const user = ref(null);

onMounted(async () => {
  const id = route.params.id;

  try {
    user.value = userStore.total === 0
        ? await userStore.loadUser(id)
        : userStore.getUserById(id);
  } catch (error) {
    console.error(error);
  }
});

const update = async (data) => {
  try {
    await userStore.updateUser(data);

    toast.success('User changed!');

    router.push('/users');
  } catch (error) {
    console.error(error);
    toast.error('Failed to update user');
  }
};
</script>

<template>
  <div>
    <h2 class="header">Edit user</h2>

    <UserForm v-if="user" :user="user" :saving="userStore.saving" @save="update" />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
