<script setup>
import { onMounted } from 'vue';
import { RouterLink } from 'vue-router';
import { useUsers } from '../composables/useUsers.js';

const {
  users,
  loading,
  error,
  removingId,
  loadUsers,
  removeUser
} = useUsers();

onMounted(loadUsers);
</script>

<template>
  <div v-if="loading">
    Loading...
  </div>

  <div v-else-if="error">
    {{ error }}
  </div>

  <div v-else-if="users.length === 0">
    No users found.
  </div>

  <template v-else>
    <div v-for="user in users" :key="user.id" class="container">
      <div>
        <div>{{ user.firstName }} {{ user.lastName }}</div>
        <div class="bold">{{ user.email }}</div>
      </div>
      <div class="modifiers">
        <RouterLink :to="`/users/${user.id}/edit`">Edit</RouterLink>
        <button type="button" @click="removeUser(user.id)">
          {{ removingId === user.id ? 'Deleting...' : 'Delete' }}
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
