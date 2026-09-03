<script setup>
import { onMounted } from 'vue';
import { useCategories } from '../composables/useCategories.js';

const {
  categories,
  loading,
  error,
  removingId,
  loadCategories,
  removeCategory
} = useCategories();

onMounted(loadCategories);

</script>

<template>
  <div v-if="loading">
    Loading...
  </div>

  <div v-else-if="error">
    {{ error }}
  </div>

  <div v-else-if="categories.length === 0">
    No categories found.
  </div>
  <template v-else>
    <div v-for="category in categories" :key="category.id" class="container">
      <div>{{ category.name }}</div>
      <div class="modifiers">
        <button type="button" @click="removeCategory(category.id)">
          {{ removingId === category.id ? 'Deleting...' : 'Delete' }}
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