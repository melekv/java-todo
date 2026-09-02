<script setup>
import { ref, onMounted } from 'vue';
import { RouterLink } from 'vue-router';

const list = ref([]);
const API_URL = import.meta.env.VITE_API_URL;

onMounted(async () => {
  try {
    const response = await fetch(`${API_URL}/users`);

    list.value = await response.json();
  } catch (error) {
    console.error(error);
  }
});

const remove = async (id) => {
  try {
    const response = await fetch(`${API_URL}/users/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) {
      throw new Error(`HTTP error: ${response.status}`);
    }

    const index = list.value.findIndex(user => user.id === id);

    if (index !== -1) {
      list.value.splice(index, 1);
    }
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <div v-for="user in list" class="container">
    <div>
      <div>{{ user.firstName }} {{ user.lastName }}</div>
      <div class="bold">{{ user.email }}</div>
    </div>
    <div class="modifiers">
      <RouterLink :to="`/users/${user.id}/edit`">Edit</RouterLink>
      <button type="button" @click="remove(user.id)">Delete</button>
    </div>
  </div>
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
