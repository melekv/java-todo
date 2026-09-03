<script setup>
import { onMounted } from 'vue';
import { useTodoStore } from '../stores/todoStore.js';
import { useUserStore } from '../stores/userStore.js';
import { useCategoryStore } from '../stores/categoryStore.js';

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
      <article>
        <div v-if="todoStore.loading">
          Loading...
        </div>

        <div v-else-if="todoStore.error">
          {{ todoStore.error }}
        </div>

        <div v-else>
          <div class="card">
            <div class="label">Todos</div>
            <div class="value">{{ todoStore.total }}</div>
          </div>
        </div>
      </article>

      <article>
        <div v-if="userStore.loading">
          Loading...
        </div>

        <div v-else-if="userStore.error">
          {{ userStore.error }}
        </div>

        <div v-else>
          <div class="card">
            <div class="label">Users</div>
            <div class="value">{{ userStore.total }}</div>
          </div>
        </div>
      </article>

      <article>
        <div v-if="categoryStore.loading">
          Loading...
        </div>

        <div v-else-if="categoryStore.error">
          {{ categoryStore.error }}
        </div>

        <div v-else>
          <div class="card">
            <div class="label">Categories</div>
            <div class="value">{{ categoryStore.total }}</div>
          </div>
        </div>
      </article>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  gap: 20px;
}
</style>