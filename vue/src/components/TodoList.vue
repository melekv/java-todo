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
    <div v-for="todo in todoStore.todos" :key="todo.id" class="card">
      <div class="info">
        <div class="title">{{ todo.title }}</div>
        <div class="description">{{ todo.description }}</div>
        <div class="status">{{ todo.status }}</div>
      </div>
      <div class="actions">
        <RouterLink class="btn btn-secondary" :to="`/todos/${todo.id}/edit`">Edit</RouterLink>
        <button class="btn btn-danger" type="button" @click="todoStore.deleteTodo(todo.id)">
          {{ todoStore.removingId === todo.id ? 'Deleting...' : 'Delete' }}
        </button>
      </div>
    </div>
  </template>
</template>

<style scoped>
.title {
  font-weight: 600;
  font-size: 16px;
}

.description {
  color: #6b7280;
}

.status {
  font-size: 13px;
  font-weight: 600;
}
</style>
