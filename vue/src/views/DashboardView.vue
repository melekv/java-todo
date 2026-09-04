<script setup>
import { onMounted } from 'vue';
import { useTodoStore } from '../stores/todoStore.js';
import { useUserStore } from '../stores/userStore.js';
import { useCategoryStore } from '../stores/categoryStore.js';
import Card from '../components/Card.vue';

const todoStore = useTodoStore();
const userStore = useUserStore();
const categoryStore = useCategoryStore();

onMounted(() => {
  todoStore.loadTodos();
  userStore.loadUsers();
  categoryStore.loadCategories();
});
</script>

<template>
  <div>
    <h2>Dashboard</h2>

    <div class="container">
      <Card :item="todoStore" label="Todos" />
      <Card :item="userStore" label="Users" />
      <Card :item="categoryStore" label="Categories" />
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: space-around;
  gap: 20px;
}
</style>