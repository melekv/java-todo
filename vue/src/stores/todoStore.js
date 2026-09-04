import { defineStore } from 'pinia';
import { getTodos, getTodo, createTodo, updateTodo, deleteTodo } from '../services/api.js';

const CACHE_TTL = 60 * 1000;

export const useTodoStore = defineStore('todos', {
    state: () => ({
        todos: [],
        loading: false,
        error: null,
        removingId: null,
        saving: false,
        lastLoadedAt: null,
    }),

    getters: {
        total: (state) => state.todos.length,

        getTodoById: (state) => {
            return (id) => state.todos.find(todo => todo.id === id)
        }
    },

    actions: {
        async loadTodos() {
            if (this.lastLoadedAt && Date.now() - this.lastLoadedAt < CACHE_TTL) {
                return;
            }

            this.loading = true;
            this.error = null;

            try {
                this.todos = await getTodos();
                this.lastLoadedAt = Date.now();
            } catch (error) {
                console.error(error);
                this.error = 'Failed to load todos';
            } finally {
                this.loading = false;
            }
        },
        async loadTodo(id) {
            try {
                return await getTodo(id);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to load todo';
            }
        },
        async createTodo(data) {
            try {
                const todo = await createTodo(data);
                this.todos.push(todo);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to create todo';
                throw error;
            }
        },
        async updateTodo(data) {
            this.saving = true;

            try {
                const updatedTodo = await updateTodo(data);

                const index = this.todos.findIndex(todo => todo.id === updatedTodo.id);

                if (index !== -1) {
                    this.todos[index] = updatedTodo;
                }
            } catch (error) {
                console.error(error);
                this.error = 'Failed to update todo';
                throw error;
            } finally {
                this.saving = false;
            }
        },
        async deleteTodo(id) {
            this.removingId = id;

            try {
                await deleteTodo(id);

                this.todos = this.todos.filter(todo => todo.id !== id);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to delete todo';
                throw error;
            } finally {
                this.removingId = null;
            }
        }
    }
});
