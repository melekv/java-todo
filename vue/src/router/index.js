import { createRouter, createWebHistory } from 'vue-router';
import AddTodoView from '../views/AddTodoView.vue';
import AddCategoryView from '../views/AddCategoryView.vue';
import CategoriesView from '../views/CategoriesView.vue';
import TodosView from '../views/TodosView.vue';
import UsersView from '../views/UsersView.vue';
import AddUserView from '../views/AddUserView.vue';

const routes = [
    {
        path: '/todos',
        component: TodosView,
    },
    {
        path: '/todos/add',
        component: AddTodoView,
    },
    {
        path: '/categories',
        component: CategoriesView,
    },
    {
        path: '/categories/add',
        component: AddCategoryView,
    },
    {
        path: '/users/add',
        component: AddUserView,
    },
    {
        path: '/users',
        component: UsersView,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
