<script setup>
import { useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import TodoForm from '../components/TodoForm.vue';
import { createTodo } from '../services/api.js';

const router = useRouter();

const create = async (data) => {
  try {
    await createTodo(data);

    toast.success('Todo created!');

    router.push('/todos');
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <div>
    <h2 class="header">Add new todo</h2>

    <TodoForm @save="create" />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
