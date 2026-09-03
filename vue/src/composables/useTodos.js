import { ref } from 'vue';
import { getTodos, deleteTodo } from '../services/api.js';

export const useTodos = () => {
    const todos = ref([]);
    const loading = ref(false);
    const error = ref(null);
    const removingId = ref(null);

    const loadTodos = async () => {
        loading.value = true;
        error.value = null;

        try {
            todos.value = await getTodos();
        } catch (error) {
            console.error(error);
            error.value = 'Failed to load todos';
        } finally {
            loading.value = false;
        }
    };

    const removeTodo = async (id) => {
        removingId.value = id;

        try {
            await deleteTodo(id);

            const index = todos.value.findIndex(todo => todo.id === id);

            if (index !== -1) {
                todos.value.splice(index, 1);
            }
        } catch (error) {
            console.error(error);
        } finally {
            removingId.value = null;
        }
    };

    return { todos, loading, error, removingId, loadTodos, removeTodo };
};