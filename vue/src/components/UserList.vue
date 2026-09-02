<script setup>
import { ref, onMounted } from 'vue';
import { RouterLink } from 'vue-router';
import { getUsers, deleteUser } from '../services/api.js';

const list = ref([]);

const loading = ref(false);
const error = ref(null);

const removingId = ref(null);

onMounted(async () => {
  loading.value = true;
  error.value = null;

  try {
    list.value = await getUsers();
  } catch (error) {
    console.error(error);
    error.value = 'Failed to load users';
  } finally {
    loading.value = false;
  }
});

const remove = async (id) => {
  removingId.value = id;

  try {
    await deleteUser(id);

    const index = list.value.findIndex(user => user.id === id);

    if (index !== -1) {
      list.value.splice(index, 1);
    }
  } catch (error) {
    console.error(error);
  } finally {
    removingId.value = null;
  }
};
</script>

<template>
  <div v-if="loading">
    Loading...
  </div>

  <div v-else-if="error">
    {{ error }}
  </div>

  <div v-else-if="list.length === 0">
    No users found.
  </div>

  <template v-else>
    <div v-for="user in list" :key="user.id" class="container">
      <div>
        <div>{{ user.firstName }} {{ user.lastName }}</div>
        <div class="bold">{{ user.email }}</div>
      </div>
      <div class="modifiers">
        <RouterLink :to="`/users/${user.id}/edit`">Edit</RouterLink>
        <button type="button" @click="remove(user.id)">
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
