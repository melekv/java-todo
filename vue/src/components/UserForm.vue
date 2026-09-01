<script setup>
import { ref } from 'vue';

const title = ref('');
const description = ref('');

</script>

<template>
  <form class="add-todo">
    <div class="group">
      <label class="label" for="title">Title:</label>
      <input class="input" id="title" type="text" v-model="title" />
    </div>

    <div class="group">
      <label class="label" for="description">Description:</label>
      <input class="input" id="description" type="text" v-model="description" />
    </div>

    <div class="group">
      <label class="label" for="category">Category:</label>
      <select class="add-todo__select" id="category">
        <option>
          Select...
        </option>
        <option>
          Travel
        </option>
        <option>
          Work
        </option>
      </select>
    </div>

    <button class="button" type="button">Submit</button>
  </form>
</template>

<style scoped>
.add-todo {
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

.label {
  font-size: 14px;
  font-weight: 600;
}

.input,
.add-todo__select {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 6px;
  background: #333;
  color: #fff;
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
