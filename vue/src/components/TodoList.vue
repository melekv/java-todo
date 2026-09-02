<script setup>
import { RouterLink } from 'vue-router';
import { onMounted, ref } from 'vue';
import { getTodos, deleteTodo } from '../services/api.js';

const list = ref([]);

const loading = ref(false);
const error = ref(null);

const removingId = ref(null);

onMounted(async () => {
  loading.value = true;
  error.value = null;

  try {
    list.value = await getTodos();
  } catch (error) {
    console.error(error);
    error.value = 'Failed to load todos';
  } finally {
    loading.value = false;
  }
});

const remove = async (id) => {
  removingId.value = id;

  try {
    await deleteTodo(id);

    const index = list.value.findIndex(todo => todo.id === id);

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
    No todos found.
  </div>

  <template v-else>
    <div v-for="todo in list" :key="todo.id" class="container">
      <div>
        <div>{{ todo.title }}</div>
      </div>
      <div class="modifiers">
        <RouterLink :to="`/todos/${todo.id}/edit`">Edit</RouterLink>
        <button type="button" @click="remove(todo.id)">Delete</button>
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
</style>