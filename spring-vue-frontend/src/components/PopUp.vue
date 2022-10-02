<template>
    <div class="modal fade"
         id="modal"
         tabindex="-1"
         aria-labelledby="label"
         aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"
                        id="label">
                        {{ getTitle }}
                    </h5>
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div>
                        <СomparingLessons :lessons="lessons"/>
                    </div>
                    <div>
                        <ZamenaForm/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-success">
                        Отправить
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import ZamenaForm from "./ZamenaForm";
import СomparingLessons from "./СomparingLessons.vue";
import {computed, ref, watch} from "vue";

// eslint-disable-next-line no-undef
const props = defineProps({
    cellData: {
        type: Object,
    }
});

const time = ref("");
const dayOfWeek = ref(0);
const lessons = ref([]);
const group = ref("");

watch(() => props.cellData, (cellData) => {
    time.value = cellData.time;
    dayOfWeek.value = getFormattedDayOfWeekByIndex(cellData.dayOfWeekIndex);
    lessons.value = cellData.lessons;
    group.value = cellData.group;
});

const getTitle = computed(() => {
    return `Замена ${dayOfWeek.value} ${time.value} для ${group.value}`;
});

function getFormattedDayOfWeekByIndex(idx) {
    if (idx === 1) return "в понедельник";
    else if (idx === 2) return "во вторник";
    else if (idx === 3) return "в среду";
    else if (idx === 4) return "в четверг";
    else if (idx === 5) return "в пятницу";
    else if (idx === 6) return "в субботу";
}


</script>

<style scoped>

div.modal-content {
    background-color: white;
}

h5.modal-title {
    font-size: 1.1em;
}

div.modal-body {
    display: flex;
}

</style>