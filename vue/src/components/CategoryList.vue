<script setup>
import { ref, onMounted } from 'vue';
import { deleteCategory, getCategories } from '../services/api.js';

const list = ref([]);

const loading = ref(false);
const error = ref(null);

const removingId = ref(null);

onMounted(async () => {
  loading.value = true;
  error.value = null;

  try {
    list.value = await getCategories();
  } catch (error) {
    console.error(error);
    error.value = 'Failed to load categories';
  } finally {
    loading.value = false;
  }
});

const remove = async (id) => {
  removingId.value = id;

  try {
    await deleteCategory(id);

    const index = list.value.findIndex(category => category.id === id);

    if (index !== -1) {
      list.value.splice(index, 1);
    }
  } catch (error) {
    console.error(error);
  } finally {
    removingId.value = null;
  }
};

</script>

<template>
  <div v-if="loading">
    Loading...
  </div>

  <div v-else-if="error">
    {{ error }}
  </div>

  <div v-else-if="list.length === 0">
    No categories found.
  </div>
  <template v-else>
    <div v-for="category in list" :key="category.id" class="container">
      <div>{{ category.name }}</div>
      <div class="modifiers">
        <button type="button" @click="remove(category.id)">Delete</button>
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