<script setup>
import { ref, defineProps, defineEmits } from 'vue';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const props = defineProps({
  todo: {
    type: Object,
    default: null,
  }
});

const firstName = ref(props.todo?.firstName ?? '');
const lastName = ref(props.todo?.lastName ?? '');
const email = ref(props.todo?.email ?? '');
const active = ref(props.todo?.active ?? false);

const emit = defineEmits(['save']);

const submit = () => {
  if (firstName.value === '') {
    toast.error('Please enter first name');
    return;
  }

  if (lastName.value === '') {
    toast.error('Please enter last name');
    return;
  }

  if (!props.todo && email.value === '') {
    toast.error('Please enter email');
    return;
  }

  const data = {
    firstName: firstName.value,
    lastName: lastName.value,
    ...(props.todo
        ? { id: props.todo.id, active: active.value }
        : { email: email.value }),
  };

  emit('save', data);
};

</script>

<template>
  <form class="form" @submit.prevent="submit">
    <div class="group">
      <label class="label" for="first-name">First name:</label>
      <input class="input" id="first-name" type="text" v-model="firstName" />
    </div>

    <div class="group">
      <label class="label" for="last-name">Last name:</label>
      <input class="input" id="last-name" type="text" v-model="lastName" />
    </div>

    <div class="group">
      <label class="label" for="email">Email:</label>
      <input class="input max-width" id="email" type="email" :disabled="props.todo" v-model="email" />
    </div>

    <div class="group row">
      <label class="label" for="active">Active:</label>
      <input class="input" id="active" type="checkbox" v-model="active" />
    </div>

    <button class="button" type="submit">Submit</button>
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
