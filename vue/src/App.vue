<script setup>
import { RouterLink } from "vue-router";
</script>

<template>
  <div class="app">
    <header class="header">
      <h1>
        <RouterLink class="remove-decor" to="/">
          TODO APP
        </RouterLink>
      </h1>
      <p>Manage your tasks</p>
    </header>

    <div class="content">
      <aside>
        <nav class="nav">
          <RouterLink to="/">Dashboard</RouterLink>
          <RouterLink to="/todos">Todos</RouterLink>
          <RouterLink to="/categories">Categories</RouterLink>
          <RouterLink to="/users">Users</RouterLink>
        </nav>
      </aside>

      <main>
        <RouterView />
      </main>
    </div>
  </div>
</template>

<style scoped>
.app {
  width: 100%;
  max-width: 1000px;
  min-height: 100vh;
  margin: 0 auto;
  padding: 40px;
}

.content {
  display: flex;
}

aside {
  padding: 20px;
  width: 240px;
  flex-shrink: 0;
}

main {
  flex-grow: 1;
}

.nav {
  display: flex;
  flex-direction: column;
  gap: 18px;
  justify-content: center;
}

.nav a {
  text-decoration: none;
  color: var(--color-text);
  font-weight: 500;
  font-size: 18px;
}

.nav a:hover {
  color: var(--color-text-muted);
}

.nav a.router-link-active {
  color: var(--color-text-active-link);
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
  color: var(--color-text-muted);
}
</style>
