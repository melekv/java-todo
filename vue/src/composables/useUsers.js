import { ref } from 'vue';
import {deleteUser, getUsers} from '../services/api.js';

export const useUsers = () => {
    const users = ref([]);
    const loading = ref(false);
    const error = ref(null);
    const removingId = ref(null);

    const loadUsers = async () => {
        loading.value = true;
        error.value = null;

        try {
            users.value = await getUsers();
        } catch (error) {
            console.error(error);
            error.value = 'Failed to load users';
        } finally {
            loading.value = false;
        }
    }

    const removeUser = async (id) => {
        removingId.value = id;

        try {
            await deleteUser(id);

            const index = users.value.findIndex(user => user.id === id);

            if (index !== -1) {
                users.value.splice(index, 1);
            }
        } catch (error) {
            console.error(error);
        } finally {
            removingId.value = null;
        }
    };

    return { users, loading, error, removingId, loadUsers, removeUser };
};
