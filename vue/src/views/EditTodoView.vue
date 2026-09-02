<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import TodoForm from '../components/TodoForm.vue';

const route = useRoute();
const router = useRouter();

const todo = ref(null);
const API_URL = import.meta.env.VITE_API_URL;

onMounted(async () => {
  try {
    const response = await fetch(`${API_URL}/todos/${route.params.id}`);

    if (!response.ok) {
      throw new Error('Error fetching user!');
    }

    const data = await response.json();

    todo.value = {
      id: data.id,
      title: data.title,
      description: data.description,
      status: data.status,
      userId: data.userId,
      categoryId: data.categoryId,
    };
  } catch (error) {
    console.error(error);
  }
});

const update = async (data) => {
  try {
    const response = await fetch(`${API_URL}/todos/${data.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
      body: JSON.stringify({
        title: data.title,
        description: data.description,
        status: data.status,
        categoryId: data.categoryId,
      })
    });

    if (!response.ok) {
      throw new Error('Error updating todo!');
    }

    toast.success('Todo changed!');

    router.push('/todos');
  } catch (error) {
    console.error(error);
  }
};

</script>

<template>
  <div>
    <h2 class="header">Edit todo</h2>

    <TodoForm v-if="todo" :todo="todo" @save="update" />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
