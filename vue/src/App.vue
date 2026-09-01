<script setup>
import { RouterLink } from "vue-router";
</script>

<template>
  <main class="app">
    <header class="app__header">
      <h1 class="app__title">TODO APP</h1>
      <p class="app__subtitle">Manage your tasks</p>

      <nav class="nav">
        <RouterLink class="nav-link" to="/todos">Todos</RouterLink>
        <RouterLink class="nav-link" to="/categories">Categories</RouterLink>
        <RouterLink class="nav-link" to="/users">Users</RouterLink>
      </nav>
    </header>

    <RouterView />
  </main>
</template>

<style scoped>
.app {
  width: min(500px, 100%);
  min-height: 100vh;
  margin: 0 auto;
  padding: 32px 24px;
  background: #ffffff;
}

.nav {
  margin-top: 10px;
  display: flex;
  gap: 16px;
  justify-content: center;
}

.nav-link {
  text-decoration: none;
  color: #1f2937;
}

.app__header {
  margin-bottom: 32px;
  text-align: center;
}

.app__title {
  margin: 0;
  font-size: 24px;
  font-weight: 700;
}

.app__subtitle {
  margin: 6px 0 0;
  font-size: 14px;
  color: #6b7280
}
</style>
