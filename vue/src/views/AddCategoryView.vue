<script setup>
import { useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import CategoryForm from "../components/CategoryForm.vue";

const router = useRouter();

const save = async name => {
  if (!name.trim()) {
    toast.error('Category name required');
    return;
  }

  try {
    const response = await fetch('http://localhost:8080/api/v1/categories', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        name: name,
      })
    });

    if (!response.ok) {
      throw new Error(`HTTP error: ${response.status}`);
    }

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
