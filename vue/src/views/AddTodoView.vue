<script setup>
import TodoForm from "../components/UserForm.vue";
</script>

<template>
  <div>
    <h2 class="add-todo__header">Add new todo</h2>

    <TodoForm />
  </div>
</template>

<style scoped>
.add-todo__header {
  font-size: 18px;
}
</style>
