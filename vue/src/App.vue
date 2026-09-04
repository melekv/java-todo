<script setup>
import { RouterLink } from "vue-router";
</script>

<template>
  <main class="app">
    <header class="header">
      <h1>
        <RouterLink class="remove-decor" to="/">
          TODO APP
        </RouterLink>
      </h1>
      <p>Manage your tasks</p>

      <nav class="nav">
        <RouterLink to="/todos">Todos</RouterLink>
        <RouterLink to="/categories">Categories</RouterLink>
        <RouterLink to="/users">Users</RouterLink>
      </nav>
    </header>

    <RouterView />
  </main>
</template>

<style scoped>
.app {
  width: 100%;
  max-width: 1000px;
  min-height: 100vh;
  margin: 0 auto;
  padding: 40px;
  background: #fff;
}

.nav {
  display: flex;
  gap: 24px;
  justify-content: center;
}

.nav a {
  text-decoration: none;
  color: #374151;
  font-weight: 500;
}

.nav a:hover {
  color: #111827;
}

.nav a.router-link-active {
  color: #2563eb;
  font-weight: 700;
}

.header {
  text-align: center;
  margin-bottom: 40px;
}

.header h1 {
  margin: 0 0 8px;
  font-size: 28px;
  letter-spacing: 0.5px;
}

.header h1 a {
  text-decoration: none;
  color: inherit;
}

.header p {
  margin: 0 0 20px;
  color: #6b7280;
}
</style>
