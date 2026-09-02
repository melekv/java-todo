<script setup>
import TodoList from '../components/TodoList.vue'
import { RouterLink } from 'vue-router';
</script>

<template>
  <div>
    <h2 class="header">Todos</h2>

    <TodoList />
    <RouterLink class="button" to="/todos/add">Add</RouterLink>
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}

.button {
  text-decoration: none;
  color: #1f2937;
  border: 1px solid #1f2937;
  border-radius: 3px;
  padding: 3px;
}
</style>