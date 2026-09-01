<script setup>
import TodoList from '../components/TodoList.vue'
</script>

<template>
  <div>
    <h2 class="header">Todos</h2>

    <TodoList />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>