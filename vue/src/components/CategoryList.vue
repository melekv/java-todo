<script setup>
import { ref, onMounted } from 'vue';
import { RouterLink } from 'vue-router';

const list = ref([]);

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8080/api/v1/categories', {
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
    const response = await fetch(`http://localhost:8080/api/v1/categories/${id}`, {
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