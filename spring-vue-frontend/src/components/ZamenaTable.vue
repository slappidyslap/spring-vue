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
            <tr titles>
                <th scope="col">Время</th>
                <th scope="col">Понедельник</th>
                <th scope="col">Вторник</th>
                <th scope="col">Среда</th>
                <th scope="col">Четверг</th>
                <th scope="col">Пятница</th>
                <th scope="col">Суббота</th>
            </tr>
        </thead>
        <!-- Кошмар начинается! (не могу сделать так чтобы рендерилась через обычную строку)-->
        <tbody ref="contentTable">
            <tr>
                <th scope="row"
                    time>
                    07:30 09:00</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    09:05 10:35</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    11.00 12.30</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    12.35 14.05</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    14.10 15.40</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    15.45 17.15</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    17.20 18.50</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
            <tr>
                <th scope="row"
                    time>
                    18.55 20.25</th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
                <th scope="row">
                    <PopUp>
                        <ZamenaForm/>
                    </PopUp>
                </th>
            </tr>
        </tbody>
    </table>
    <div class="modal fade"
         id="exampleModal"
         tabindex="-1"
         aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"
                        id="exampleModalLabel">Modal title</h5>
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    
                    <slot></slot>

                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal">Close</button>
                    <button type="button"
                            class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import PopUp from "@/components/PopUp.vue";
import {defineProps, onMounted, ref, toRaw} from 'vue';
import zamenaService from "@/services/zamena.service";
import ZamenaForm from "./ZamenaForm.vue";

const contentTable = ref(null);

const props = defineProps({
    group: {
        type: String,
        required: true,
    }
});
const group = toRaw(props.group).replace('_', ' ');

onMounted(() => {
    fillTable();
});

// eslint-disable-next-line no-unused-vars
function fillTable() {
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

    if (!isExists(timetableData[color])) {
        // Эти условия нужны, чтобы не было лишник "НЕТ"
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

// Это те элементы который имеют кнопку попап (th)
th:not([time]):not([scope=col]) {
    position: relative;
}

th[scope=col] {
    font-weight: bolder;
}

* {
    font-weight: normal;
}

th[time] {
    font-weight: bolder;
}
div.zamena-white {
    background: rgba(255, 255, 255, 1);
}

.zamena-black {
    background: rgba(16,153,0,0.34)
}
</style>