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
    <div v-for="user in userStore.users" :key="user.id" class="card" :class="{ 'inactive': !user.active }">
      <div class="info">
        <div class="name">{{ user.firstName }} {{ user.lastName }}</div>
        <div class="email">{{ user.email }}</div>
      </div>
      <div class="actions">
        <RouterLink class="btn btn-secondary" :to="`/users/${user.id}/edit`">Edit</RouterLink>
        <button class="btn btn-danger" type="button" @click="userStore.deleteUser(user.id)">
          {{ userStore.removingId === user.id ? 'Deleting...' : 'Delete' }}
        </button>
      </div>
    </div>
  </template>
</template>

<style scoped>
.inactive {
  background-color: #ff5a5a;
}

.name {
  font-weight: 700;
}

.email {
  font-size: 14px;
}
</style>
