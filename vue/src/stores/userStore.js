import { defineStore } from 'pinia';
import {createUser, deleteUser, getUser, getUsers, updateUser} from '../services/api.js';

const CACHE_TTL = 60 * 1000;

export const useUserStore = defineStore('users', {
    state: () => ({
        users: [],
        loading: false,
        error: null,
        removingId: null,
        saving: false,
        lastLoadedAt: null,
    }),

    getters: {
        total: (state) => state.users.length,

        getUserById: (state) => {
            return (id) => state.users.find(user => user.id === id)
        }
    },

    actions: {
        async loadUsers() {
            if (this.lastLoadedAt && Date.now() - this.lastLoadedAt < CACHE_TTL) {
                return;
            }

            this.loading = true;
            this.error = null;

            try {
                this.users = await getUsers();
                this.lastLoadedAt = Date.now();
            } catch (error) {
                console.error(error);
                this.error = 'Failed to load users';
            } finally {
                this.loading = false;
            }
        },
        async loadUser(id) {
            this.error = null;

            try {
                return await getUser(id);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to load user';
            }
        },
        async createUser(data) {
            try {
                const user = await createUser(data);
                this.users.push(user);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to create user';
                throw error;
            }
        },
        async updateUser(data) {
            this.saving = true;

            try {
                const updatedUser = await updateUser(data);

                const index = this.users.findIndex(user => user.id === updatedUser.id);

                if (index !== -1) {
                    this.users[index] = updatedUser;
                }
            } catch (error) {
                console.error(error);
                this.error = 'Failed to update user';
                throw error;
            } finally {
                this.saving = false;
            }
        },
        async deleteUser(id) {
            this.removingId = id;

            try {
                await deleteUser(id);

                this.users = this.users.filter(user => user.id !== id);
            } catch (error) {
                console.error(error);
                this.error = 'Failed to delete user';
                throw error;
            } finally {
                this.removingId = null;
            }
        }
    }
});
