import { createRouter, createWebHistory } from 'vue-router';
import AddTodoView from '../views/AddTodoView.vue';
import AddCategoryView from '../views/AddCategoryView.vue';
import CategoriesView from '../views/CategoriesView.vue';
import TodosView from '../views/TodosView.vue';
import UsersView from '../views/UsersView.vue';
import AddUserView from '../views/AddUserView.vue';
import EditUserView from '../views/EditUserView.vue';
import EditTodoView from '../views/EditTodoView.vue';
import DashboardView from '../views/DashboardView.vue';

const routes = [
    {
        path: '/',
        component: DashboardView
    },
    {
        path: '/todos',
        component: TodosView,
    },
    {
        path: '/todos/add',
        component: AddTodoView,
    },
    {
        path: '/todos/:id/edit',
        component: EditTodoView,
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
        path: '/users/:id/edit',
        component: EditUserView,
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
