<script setup>
import { RouterLink } from 'vue-router';
import { onMounted } from 'vue';
import { useTodos } from '../composables/useTodos.js';

const { todos, loading, error, removingId, loadTodos, removeTodo } = useTodos();

onMounted(loadTodos);
</script>

<template>
  <div v-if="loading">
    Loading...
  </div>

  <div v-else-if="error">
    {{ error }}
  </div>

  <div v-else-if="todos.length === 0">
    No todos found.
  </div>

  <template v-else>
    <div v-for="todo in todos" :key="todo.id" class="container">
      <div>
        <div>{{ todo.title }}</div>
      </div>
      <div class="modifiers">
        <RouterLink :to="`/todos/${todo.id}/edit`">Edit</RouterLink>
        <button type="button" @click="removeTodo(todo.id)">
          {{ removingId === todo.id ? 'Deleting...' : 'Delete' }}
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