<script setup>
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import TodoForm from '../components/TodoForm.vue';
import { useTodoStore } from '../stores/todoStore.js';

const route = useRoute();
const router = useRouter();

const todoStore = useTodoStore();

const todo = ref(null);

onMounted(async () => {
  const id = route.params.id;

  try {
    todo.value = todoStore.total === 0
        ? await todoStore.loadTodo(id)
        : todoStore.getTodoById(id);
  } catch (error) {
    console.error(error);
  }
});

const update = async (data) => {
  try {
    await todoStore.updateTodo(data);

    toast.success('Todo changed!');
    router.push('/todos');
  } catch (error) {
    console.error(error);
    toast.error('Failed to update todo');
  }
};

</script>

<template>
  <div>
    <h2 class="header">Edit todo</h2>

    <div v-if="todoStore.loading">
      Loading todo...
    </div>

    <div v-else-if="todoStore.error">
      {{ todoStore.error }}
    </div>

    <TodoForm
        v-else-if="todo"
        :todo="todo"
        :saving="todoStore.saving"
        @save="update"
    />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
