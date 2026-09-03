<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import TodoForm from '../components/TodoForm.vue';
import { createTodo } from '../services/api.js';

const router = useRouter();

const saving = ref(false);

const create = async (data) => {
  saving.value = true;

  try {
    await createTodo(data);

    toast.success('Todo created!');
    router.push('/todos');
  } catch (error) {
    console.error(error);
    toast.error('Failed to create todo');
  } finally {
    saving.value = false;
  }
};
</script>

<template>
  <div>
    <h2 class="header">Add new todo</h2>

    <TodoForm :saving="saving" @save="create" />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
