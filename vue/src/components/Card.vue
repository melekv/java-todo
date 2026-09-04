<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  item: Object,
  label: String,
});
</script>

<template>
  <article>
    <div v-if="props.item.loading">
      Loading...
    </div>

    <div v-else-if="props.item.error">
      {{ props.item.error }}
    </div>

    <div v-else>
      <div class="card card-dashboard">
        <div class="label">Todos</div>
        <div class="value">{{ props.item.total }}</div>
      </div>
    </div>
  </article>
</template>

<style scoped>
.card-dashboard {
  flex-direction: column;
  gap: 10px;
}
</style>