<script setup>
import { RouterLink } from 'vue-router';
import { onMounted, ref } from 'vue';

const list = ref([]);

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8080/api/v1/todos');

    list.value = await response.json();
  } catch (error) {
    console.error(error);
  }
});

const remove = async (id) => {
  try {
    const response = await fetch(`http://localhost:8080/api/v1/todos/${id}`, {
      method: 'DELETE',
    });

    if (!response.ok) {
      throw new Error(`HTTP error: ${response.status}`);
    }

    const index = list.value.findIndex(todo => todo.id === id);

    if (index !== -1) {
      list.value.splice(index, 1);
    }
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <div v-for="todo in list" class="container">
    <div>
      <div>{{ todo.title }}</div>
    </div>
    <div class="modifiers">
      <RouterLink :to="`/todos/${todo.id}/edit`">Edit</RouterLink>
      <button type="button" @click="remove(todo.id)">Delete</button>
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