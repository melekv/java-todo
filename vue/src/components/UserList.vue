<script setup>
import { onMounted } from 'vue';
import { RouterLink } from 'vue-router';
import { useUserStore } from '../stores/userStore.js';

const userStore = useUserStore();

onMounted(() => userStore.loadUsers());
</script>

<template>
  <div v-if="userStore.loading">
    Loading...
  </div>

  <div v-else-if="userStore.error">
    {{ userStore.error }}
  </div>

  <div v-else-if="userStore.total === 0">
    No users found.
  </div>

  <template v-else>
    <div v-for="user in userStore.users" :key="user.id" class="container">
      <div>
        <div>{{ user.firstName }} {{ user.lastName }}</div>
        <div class="bold">{{ user.email }}</div>
      </div>
      <div class="modifiers">
        <RouterLink :to="`/users/${user.id}/edit`">Edit</RouterLink>
        <button type="button" @click="userStore.deleteUser(user.id)">
          {{ userStore.removingId === user.id ? 'Deleting...' : 'Delete' }}
        </button>
      </div>
    </div>
  </template>
</template>

<style scoped>
.container {
  margin: 10px;
  display: flex;
  justify-content: space-between;
}

.modifiers {
  display: flex;
  gap: 10px;
}

.bold {
  font-weight: bold;
}
</style>
