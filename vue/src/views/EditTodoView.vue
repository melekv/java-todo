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
const loading = ref(false);
const error = ref(null);
const saving = ref(false);

onMounted(async () => {
  loading.value = true;
  error.value = null;

  try {
    const data = await getTodo(route.params.id);

    todo.value = data;
  } catch (error) {
    console.error(error);
    error.value = 'Failed to load todo.';
  } finally {
    loading.value = false;
  }
});

const update = async (data) => {
  saving.value = true;

  try {
    await updateTodo(data);

    toast.success('Todo changed!');
    router.push('/todos');
  } catch (error) {
    console.error(error);
    toast.error('Failed to update todo');
  } finally {
    saving.value = false;
  }
};

</script>

<template>
  <div>
    <h2 class="header">Edit todo</h2>

    <div v-if="loading">
      Loading todo...
    </div>

    <div v-else-if="error">
      {{ error }}
    </div>

    <TodoForm
        v-else-if="todo"
        :todo="todo"
        :saving="saving"
        @save="update"
    />
  </div>
</template>

<style scoped>
.header {
  font-size: 18px;
}
</style>
