<template>
    <tbody ref="tableBody">
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 07:30 09:00 </ZamenaRow>
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 09:05 10:35 </ZamenaRow>
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 11.00 12.30 </ZamenaRow>
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 12.35 14.05 </ZamenaRow>
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 14.10 15.40 </ZamenaRow>
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 15.45 17.15 </ZamenaRow>
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 17.20 18.50 </ZamenaRow>
        <ZamenaRow @clickPopupLink="onClickPopupLink"> 18.55 20.25 </ZamenaRow>
    </tbody>
    <PopUp :cell-data="cellDataForForm"></PopUp>
</template>

<script setup>
import ZamenaRow from "./ZamenaRow.vue";
import PopUp from "./PopUp.vue";
import {onMounted, ref, toRaw} from "vue";
import zamenaService from "@/services/zamena.service";

const tableBody = ref(null);

// eslint-disable-next-line no-undef
const props = defineProps({
    group: {
        type: String,
        required: true,
    }
});
const group = toRaw(props.group).replace('_', ' ');

const cellDataForForm = ref(null);

function onClickPopupLink(cellData) {
    cellDataForForm.value = cellData;
}

onMounted(() => {
    fillTable();
});

// eslint-disable-next-line no-unused-vars
function fillTable() {
    const timetable = zamenaService.getByGroup(group);

    const contentTableChildren = Array.from(tableBody.value.children);
    for (let dayOfWeekIdx = 0; dayOfWeekIdx < 6 - 1; dayOfWeekIdx++) {
        for (let lessonIdx = 0; lessonIdx < 8 - 1; lessonIdx++) {
            const th = contentTableChildren[lessonIdx].children[dayOfWeekIdx + 1]; // Плюсуем чтобы сместить направо, т.к. в первом столбце время
            const timetableData = timetable[dayOfWeekIdx][lessonIdx];

            if (isTimeTableNotEmpty(timetableData)) {
                putTimetableData(th, timetableData);
            } else {
                continue;
            }
        }
    }
}

function putTimetableData(th, timetableData) {
    th.insertAdjacentHTML('afterbegin',  `
        <div>
            <div style="background: rgba(255, 255, 255, 1);">
                ${getTimetableData(timetableData, "white")}
            </div>
            <div style="background: rgba(16,153,0,0.34)">
                ${getTimetableData(timetableData, "black")}
            </div>
        </div> `);
}

function getTimetableData(timetableData, color) {

    let resultString = "";

    if (!timetableData[color]) {
        // Эти условия нужны, чтобы не было лишник "НЕТ"
        if (timetableData.white && !timetableData.black)
            return "";
        else if (!timetableData.white && !timetableData.black)
            return "";
        return "НЕТ";
    }
    timetableData[color].forEach((data) => {

        const result =
            `
            <div>
                <div>
                    ${data.subject} ${data.to !== "all" ? data.to : ""} ${data.audience}
                </div>
                <div>
                    ${data.teacher}
                </div>
            </div>
            `;
        resultString = resultString.concat(result);
    });
    return resultString;
}

function isTimeTableNotEmpty(timeTable) {
    return timeTable && (timeTable?.white || timeTable?.black );
}
</script>

<style lang="scss" scoped>

</style>