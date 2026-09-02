<script setup>
import { ref, onMounted } from 'vue';

const list = ref([]);
const API_URL = import.meta.env.VITE_API_URL;

onMounted(async () => {
  try {
    const response = await fetch(`${API_URL}/categories`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      }
    });

    list.value = await response.json();
  } catch (error) {
    console.error(error);
  }
});

const remove = async (id) => {
  try {
    const response = await fetch(`${API_URL}/categories/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) {
      throw new Error(`HTTP error: ${response.status}`);
    }

    const index = list.value.findIndex(category => category.id === id);

    if (index !== -1) {
      list.value.splice(index, 1);
    }
  } catch (error) {
    console.error(error);
  }
};

</script>

<template>
  <div v-for="category in list" class="container">
    <div>{{ category.name }}</div>
    <div class="modifiers">
      <button type="button" @click="remove(category.id)">Delete</button>
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
</style>