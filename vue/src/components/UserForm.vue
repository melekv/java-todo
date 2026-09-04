<script setup>
import { ref, defineProps, defineEmits } from 'vue';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const props = defineProps({
  user: {
    type: Object,
    default: null,
  },
  saving: {
    type: Boolean,
    default: false,
  }
});

const firstName = ref(props.user?.firstName ?? '');
const lastName = ref(props.user?.lastName ?? '');
const email = ref(props.user?.email ?? '');
const active = ref(props.user?.active ?? false);

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

  if (!props.user && email.value === '') {
    toast.error('Please enter email');
    return;
  }

  const data = {
    firstName: firstName.value,
    lastName: lastName.value,
    ...(props.user
        ? { id: props.user.id, active: active.value }
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
      <input class="input" id="email" type="email" :disabled="props.user" v-model="email" />
    </div>

    <div v-if="props.user" class="group row">
      <label class="label" for="active">Active:</label>
      <input class="input" id="active" type="checkbox" v-model="active" />
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
