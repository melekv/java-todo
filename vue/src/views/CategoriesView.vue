<script setup>
import { RouterLink } from 'vue-router';
import CategoryList from '../components/CategoryList.vue'
</script>

<template>
  <div>
    <h2 class="header">Categories</h2>

    <CategoryList />
    <RouterLink class="button" to="/categories/add">Add</RouterLink>
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