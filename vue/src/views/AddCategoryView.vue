<script setup>
import { useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import CategoryForm from '../components/CategoryForm.vue';
import { useCategoryStore } from '../stores/categoryStore.js';

const router = useRouter();
const categoryStore = useCategoryStore();

const save = async name => {
  if (!name.trim()) {
    toast.error('Category name required');
    return;
  }

  try {
    await categoryStore.createCategory(name);

    toast.success('Category added!');

    router.push('/categories');
  } catch (error) {
    console.error(error);
    toast.error('Failed to add category!');
  }
};
</script>

<template>
  <div>
    <h2 class="header">Add new category</h2>

    <CategoryForm @save="save" />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
