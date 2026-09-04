import { defineStore } from 'pinia';
import { getCategories, createCategory, deleteCategory } from '../services/api.js';

const CACHE_TTL = 60 * 1000;

export const useCategoryStore = defineStore('categories', {
    state: () => ({
        categories: [],
        loading: false,
        error: null,
        removingId: null,
        lastLoadedAt: null,
    }),

    getters: {
        total: (state) => state.categories.length
    },

    actions: {
        async loadCategories() {
            if (this.lastLoadedAt && Date.now() - this.lastLoadedAt < CACHE_TTL) {
                return;
            }

            this.loading = true;
            this.error = null;

            try {
                this.categories = await getCategories();
                this.lastLoadedAt = Date.now();
            } catch (error) {
                console.error(error);
                this.error = 'Failed to load categories';
            } finally {
                this.loading = false;
            }
        },
        async createCategory(name) {
            try {
                const category = await createCategory(name);
                this.categories.push(category);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to create category';
                throw error;
            }
        },
        async deleteCategory(id) {
            this.removingId = id;

            try {
                await deleteCategory(id);

                this.categories = this.categories.filter(category => category.id !== id);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to delete category';
                throw error;
            } finally {
                this.removingId = null;
            }
        }
    }
});
