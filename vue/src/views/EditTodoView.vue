<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import TodoForm from '../components/TodoForm.vue';
import { getTodo, updateTodo } from '../services/api.js';

const route = useRoute();
const router = useRouter();

const todo = ref(null);

onMounted(async () => {
  try {
    const data = await getTodo(route.params.id);

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
    await updateTodo(data);

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
