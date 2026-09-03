import { ref } from 'vue';
import {deleteCategory, getCategories} from '../services/api.js';

export const useCategories = () => {
    const categories = ref([]);
    const loading = ref(false);
    const error = ref(null);
    const removingId = ref(null);

    const loadCategories = async () => {
        loading.value = true;
        error.value = null;

        try {
            categories.value = await getCategories();
        } catch (error) {
            console.error(error);
            error.value = 'Failed to load categories';
        } finally {
            loading.value = false;
        }
    }

    const removeCategory = async (id) => {
        removingId.value = id;

        try {
            await deleteCategory(id);

            const index = categories.value.findIndex(category => category.id === id);

            if (index !== -1) {
                categories.value.splice(index, 1);
            }
        } catch (error) {
            console.error(error);
        } finally {
            removingId.value = null;
        }
    }

    return { categories, loading, error, removingId, loadCategories, removeCategory };
};
