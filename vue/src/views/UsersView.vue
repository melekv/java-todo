<script setup>
import { RouterLink } from 'vue-router';
import UserList from '../components/UserList.vue'
</script>

<template>
  <div>
    <h2 class="header">Users</h2>

    <UserList />
    <RouterLink class="btn btn-primary" to="/users/add">Add</RouterLink>
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>