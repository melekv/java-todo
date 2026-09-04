<script setup>
import { ref, defineProps, defineEmits, onMounted } from 'vue';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import { useCategoryStore } from '../stores/categoryStore.js';
import { useUserStore } from '../stores/userStore.js';

const categoryStore = useCategoryStore();
const userStore = useUserStore();

onMounted(() => {
  userStore.loadUsers();
  categoryStore.loadCategories();
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
          class="select"
          id="user"
          v-model="userId"
          :disabled="props.todo || userStore.loading"
      >
        <option value="" disabled>
          {{ userStore.loading ? 'Loading users...' : 'Select...' }}
        </option>
        <option v-for="user in userStore.users" :key="user.id" :value="user.id">
          {{ user.email }}
        </option>
      </select>
      <div v-if="userStore.error">
        {{ userStore.error }}
      </div>
    </div>

    <div class="group">
      <label class="label" for="category">Category:</label>

      <select
          class="select"
          id="category"
          v-model="categoryId"
          :disabled="categoryStore.loading"
      >
        <option value="" disabled>
          {{ categoryStore.loading ? 'Loading categories...' : 'Select...' }}
        </option>
        <option v-for="category in categoryStore.categories" :key="category.id" :value="category.id">
          {{ category.name }}
        </option>
      </select>
      <div v-if="categoryStore.error">
        {{ categoryStore.error }}
      </div>
    </div>

    <div v-if="props.todo" class="group">
      <label class="label" for="status">Status:</label>
      <select class="select" id="status" v-model="status">
        <option value="" disabled>
          Select...
        </option>
        <option v-for="status in statuses" :key="status.id" :value="status.name">
          {{ status.name }}
        </option>
      </select>
    </div>

    <button
        class="btn btn-primary"
        type="submit"
        :disabled="props.saving"
    >
      {{ props.saving ? 'Saving...' : 'Submit' }}
    </button>
  </form>
</template>

<style scoped>

</style>
