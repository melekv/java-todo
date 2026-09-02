<script setup>
import { useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import TodoForm from '../components/TodoForm.vue';

const router = useRouter();

const create = async (data) => {
  try {
    const response = await fetch('http://localhost:8080/api/v1/todos', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      body: JSON.stringify({
        title: data.title,
        description: data.description,
        userId: data.userId,
        categoryId: data.categoryId,
      })
    });

    if (!response.ok) {
      throw new Error('Error creating todo!');
    }

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
