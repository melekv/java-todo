<script setup>
import { RouterLink } from 'vue-router';
import { onMounted } from 'vue';
import { useTodoStore } from '../stores/todoStore.js';

const todoStore = useTodoStore();

onMounted(() => todoStore.loadTodos());
</script>

<template>
  <div v-if="todoStore.loading">
    Loading...
  </div>

  <div v-else-if="todoStore.error">
    {{ todoStore.error }}
  </div>

  <div v-else-if="todoStore.total === 0">
    No todos found.
  </div>

  <template v-else>
    <div v-for="todo in todoStore.todos" :key="todo.id" class="container">
      <div>
        <div>{{ todo.title }}</div>
      </div>
      <div class="modifiers">
        <RouterLink :to="`/todos/${todo.id}/edit`">Edit</RouterLink>
        <button type="button" @click="todoStore.deleteTodo(todo.id)">
          {{ todoStore.removingId === todo.id ? 'Deleting...' : 'Delete' }}
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
</style>