<script setup>
import TodoList from '../components/TodoList.vue'
import { RouterLink } from 'vue-router';
</script>

<template>
  <div>
    <h2 class="header">Todos</h2>

    <TodoList />
    <RouterLink class="btn btn-primary" to="/todos/add">Add</RouterLink>
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>