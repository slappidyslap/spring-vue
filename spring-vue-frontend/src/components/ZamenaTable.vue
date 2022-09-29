<template>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Время</th>
                <th scope="col">Понедельник</th>
                <th scope="col">Вторник</th>
                <th scope="col">Среда</th>
                <th scope="col">Четверг</th>
                <th scope="col">Пятница</th>
                <th scope="col">Суббота</th>
            </tr>
        </thead>
        <tbody ref="contentTable">
            <tr>
                <th scope="row"
                    time>
                    07:30 09:00</th>
                <th scope="row" ></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    09:05 10:35</th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    11.00 12.30</th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    12.35 14.05</th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    14.10 15.40</th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    15.45 17.15</th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    17.20 18.50</th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    18.55 20.25</th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
            </tr>
        </tbody>
    </table>
</template>

<script setup>
import {defineProps, onMounted, ref, toRaw} from 'vue';
import zamenaService from "@/services/zamena.service";

const contentTable = ref(null);

const props = defineProps({
    group: {
        type: String,
        required: true,
    }
});
const group = toRaw(props.group).replace('_', ' ');

onMounted(() => {
    const timetable = zamenaService.getByGroup(group);

    const contentTableChildren = Array.from(contentTable.value.children);
    for (let dayOfWeekIdx = 0; dayOfWeekIdx < 6 - 1; dayOfWeekIdx++) {
        for (let lessonIdx = 0; lessonIdx < 8 - 1; lessonIdx++) {

            const th = contentTableChildren[lessonIdx].children[dayOfWeekIdx + 1]; // Плюсуем чтобы сместить направо, т.к. в первом столбце время
            const timetableData = timetable[dayOfWeekIdx][lessonIdx];

            if (isExists(timetableData)) {
                putTimetableData(th, timetableData);
            } else {
                break;
            }
        }

    }
});

function putTimetableData(th, timetableData) {
    th.innerHTML = `
        <div>
            <div style="background: rgba(255, 255, 255, 1);">
                ${getTimetableData(timetableData, "white")}
            </div>
            <div style="background: rgba(16,153,0,0.34)">
                ${getTimetableData(timetableData, "black")}
            </div>
        </div> `;
}

function getTimetableData(timetableData, color) {
    let resultString = "";
    if (timetableData[color] === null || timetableData[color] === undefined) {
        if (isExists(timetableData.white) && !isExists(timetableData.black))
            return "";
        else if (!isExists(timetableData.white) && !isExists(timetableData.black))
            return "";
        return "НЕТ";
    }
    timetableData[color].forEach((data) => {

        const result =
            `
            <div>
                ${data.subject} ${data.to !== "all" ? data.to : ""} ${data.audience}
            </div>
            <div>
                ${data.teacher}
            </div>
            `;
        resultString = resultString.concat(result);
    });
    return resultString;
}

function isExists(object) {
    return Boolean(object);
}

</script>

<style scoped lang="scss">

th[scope=col] {
    font-weight: bolder;
}
* {
    font-weight: normal;
}

th[time] {
    font-weight: bolder;
    width: 5%;
}
div.zamena-white {
    background: rgba(255, 255, 255, 1);
}

.zamena-black {
    background: rgba(16,153,0,0.34)
}
</style>