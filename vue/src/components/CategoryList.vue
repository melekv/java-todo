<script setup>
import { onMounted } from 'vue';
import { useCategoryStore } from '../stores/categoryStore.js';

const categoryStore = useCategoryStore();

onMounted(() => categoryStore.loadCategories());

</script>

<template>
  <div v-if="categoryStore.loading">
    Loading...
  </div>

  <div v-else-if="categoryStore.error">
    {{ categoryStore.error }}
  </div>

  <div v-else-if="categoryStore.total === 0">
    No categories found.
  </div>
  <template v-else>
    <div v-for="category in categoryStore.categories" :key="category.id" class="container">
      <div>{{ category.name }}</div>
      <div class="modifiers">
        <button type="button" @click="categoryStore.deleteCategory(category.id)">
          {{ categoryStore.removingId === category.id ? 'Deleting...' : 'Delete' }}
        </button>
      </div>
    </div>
  </template>
</template>

<style scoped>
.container {
  margin: 10px;
  display: flex;
  justify-content: space-between;
}

.modifiers {
  display: flex;
  gap: 10px;
}
</style>