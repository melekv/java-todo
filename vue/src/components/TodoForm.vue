<script setup>
import { ref, defineProps, defineEmits, onMounted } from 'vue';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

import { useCategories } from '../composables/useCategories.js';
import { useUsers } from '../composables/useUsers.js';

const {
  categories,
  loading: categoriesLoading,
  error: categoriesError,
  loadCategories
} = useCategories();

const {
  users,
  loading: usersLoading,
  error: usersError,
  loadUsers
} = useUsers();

onMounted(() => {
  loadCategories();
  loadUsers();
});

const props = defineProps({
  todo: {
    type: Object,
    default: null,
  },
  saving: {
    type: Boolean,
    default: false,
  }
});

const title = ref(props.todo?.title ?? '');
const description = ref(props.todo?.description ?? '');
const categoryId = ref(props.todo?.categoryId ?? '');
const userId = ref(props.todo?.userId ?? '');
const status = ref(props.todo?.status ?? '');
const statuses = [
  { id: 1, name: 'TODO'},
  { id: 2, name: 'IN_PROGRESS'},
  { id: 3, name: 'DONE'},
];

const emit = defineEmits(['save']);

const submit = () => {
  if (title.value === '') {
    toast.error('Please enter title');
    return;
  }

  const data = {
    title: title.value,
    description: description.value,
    status: status.value,
    userId: userId.value,
    categoryId: categoryId.value,
    ...(props.todo ? { id: props.todo.id } : {})
  };

  emit('save', data);
};

</script>

<template>
  <form class="form" @submit.prevent="submit">
    <div class="group">
      <label class="label" for="title">Title:</label>
      <input class="input" id="title" type="text" v-model="title" />
    </div>

    <div class="group">
      <label class="label" for="description">Description:</label>
      <input class="input" id="description" type="text" v-model="description" />
    </div>

    <div class="group">
      <label class="label" for="user">User:</label>

      <select
          class="input"
          id="user"
          v-model="userId"
          :disabled="props.todo || usersLoading"
      >
        <option value="" disabled>
          {{ usersLoading ? 'Loading users...' : 'Select...' }}
        </option>
        <option v-for="user in users" :key="user.id" :value="user.id">
          {{ user.email }}
        </option>
      </select>
      <div v-if="usersError">
        {{ usersError }}
      </div>
    </div>

    <div class="group">
      <label class="label" for="category">Category:</label>

      <select
          class="input"
          id="category"
          v-model="categoryId"
          :disabled="categoriesLoading"
      >
        <option value="" disabled>
          {{ categoriesLoading ? 'Loading categories...' : 'Select...' }}
        </option>
        <option v-for="category in categories" :key="category.id" :value="category.id">
          {{ category.name }}
        </option>
      </select>
      <div v-if="categoriesError">
        {{ categoriesError }}
      </div>
    </div>

    <div v-if="props.todo" class="group">
      <label class="label" for="status">Status:</label>
      <select class="input" id="status" v-model="status">
        <option value="" disabled>
          Select...
        </option>
        <option v-for="status in statuses" :key="status.id" :value="status.name">
          {{ status.name }}
        </option>
      </select>
    </div>

    <button
        class="button"
        type="submit"
        :disabled="props.saving"
    >
      {{ props.saving ? 'Saving...' : 'Submit' }}
    </button>
  </form>
</template>

<style scoped>
.form {
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 0 24px;
}

.group {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.row {
  flex-direction: row;
  justify-content: flex-start;
}

.label {
  font-size: 14px;
  font-weight: 600;
}

.input {
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
  background: #333;
  color: #fff;
}

.max-width {
  width: 100%;
}

.button {
  padding: 10px 16px;
  border: 0;
  border-radius: 6px;
  background: #333;
  color: #fff;
  cursor: pointer;
}
</style>
