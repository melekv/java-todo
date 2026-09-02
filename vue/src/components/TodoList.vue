<script setup>
import { RouterLink } from 'vue-router';
import { onMounted, ref } from 'vue';
import { getTodos, deleteTodo } from '../services/api.js';

const list = ref([]);

onMounted(async () => {
  try {
    list.value = await getTodos();
  } catch (error) {
    console.error(error);
  }
});

const remove = async (id) => {
  try {
    await deleteTodo(id);

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