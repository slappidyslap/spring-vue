<template>
    <div></div>
</template>

<script setup>
import {ref} from "vue";

// eslint-disable-next-line no-undef
const props = defineProps({
    lessons: {
        type: Array,
        required: true,
    }
});

const lessons = ref(props.lessons);

console.log(lessons);

</script>

<style scoped>

</style>