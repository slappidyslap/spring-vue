<template>
    <div class="accordion-item">
        <h2 class="accordion-header"
            :id="`${group}-header`">
            <button class="accordion-button"
                    type="button"
                    data-bs-toggle="collapse"
                    :data-bs-target="`#${group}`"
                    aria-expanded="true"
                    :aria-controls="group">
                {{ getClearNameGroup }}
            </button>
        </h2>
        <div :id="group"
             class="accordion-collapse collapse"
             :aria-labelledby="`${group}-header`">
            <div class="accordion-body">
                <ZamenaTable :group="group"/>
            </div>
        </div>
    </div>

</template>

<script setup>
import {defineProps, toRaw, computed} from 'vue';
import ZamenaTable from '@/components/ZamenaTable';

const props = defineProps({
    group: {
        type: String,
        required: true,
    }
});
const group = toRaw(props.group);

const getClearNameGroup = computed(() => {
    return group.replace('_', ' ');
});
</script>

<style scoped>

</style>