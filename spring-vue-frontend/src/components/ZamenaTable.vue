<template>
    <table class="table">
        <col width="5%">
        <col width="15.5%">
        <col width="15.5%">
        <col width="15.5%">
        <col width="15.5%">
        <col width="15.5%">
        <col width="15.5%">
        <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Понедельник</th>
                <th scope="col">Вторник</th>
                <th scope="col">Среда</th>
                <th scope="col">Четверг</th>
                <th scope="col">Пятница</th>
                <th scope="col">Суббота</th>
            </tr>
        </thead>
        <ZamenaTableBody :group="group" />
    </table>
</template>

<script setup>
import ZamenaTableBody from "./ZamenaTableBody.vue";
import {defineProps, toRaw} from "vue";

const props = defineProps({
    group: {
        type: String,
        required: true,
    }
});

const group = toRaw(props.group).replace('_', ' ');

</script>

<style scoped lang="scss">

th[scope=col] {
    font-weight: bolder;
}

div.zamena-white {
    background: rgba(255, 255, 255, 1);
}

.zamena-black {
    background: rgba(16,153,0,0.34)
}
</style>