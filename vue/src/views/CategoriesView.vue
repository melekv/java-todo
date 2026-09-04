<script setup>
import { RouterLink } from 'vue-router';
import CategoryList from '../components/CategoryList.vue'
</script>

<template>
  <div>
    <h2 class="header">Categories</h2>

    <CategoryList />
    <RouterLink class="btn btn-primary" to="/categories/add">Add</RouterLink>
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>