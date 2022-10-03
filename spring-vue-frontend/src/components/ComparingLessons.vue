<template>
    <div v-if="isLessonsExist">
        <Form>
            <div class="input-group input-group-sm mb-3">
                <input type="text"
                       class="form-control"
                       aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-sm"
                       disabled
                       :value="teacherFullName">
            </div>
            <div class="input-group input-group-sm mb-3">
                <input type="text"
                       class="form-control"
                       aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-sm"
                       disabled
                       :value="subject">
            </div>
            <div class="d-flex gap-3">
                <div class="input-group input-group-sm mb-3">
                    <input type="text"
                           class="form-control"
                           aria-label="Sizing example input"
                           aria-describedby="inputGroup-sizing-sm"
                           disabled
                           :value="audience">
                </div>
                <div style="flex: 1 0 auto;">
                    <div class="input-group input-group-sm mb-3">
                        <input type="text"
                               class="form-control"
                               aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm"
                               disabled
                               :value="to">
                    </div>
                </div>
            </div>
            <div>
                <select class="form-select form-select-sm"
                        aria-label=".form-select-sm example"
                        @change="selectedLesson = $event.target.value">
                    <option disabled
                            selected
                    >
                        Выберите в какое место хотите поставить замену
                    </option>
                    <optgroup label="Белая (Числитель)">
                        <option value="white-1">первая белая</option>
                        <option value="white-2">вторая белая</option>
                    </optgroup>
                    <optgroup label="Черная (Знаменатель)">
                        <option value="black-1">первая черная</option>
                        <option value="black-2">вторая черная</option>
                    </optgroup>
                </select>
            </div>
        </Form>
    </div>
    <div v-else>
        <span class="span">Тут свободно!</span>
    </div>
</template>

<script setup>
import {ref, watch} from "vue";

// eslint-disable-next-line no-undef
const props = defineProps({
    lessons: {
        type: Object,
    }
});

// eslint-disable-next-line no-unused-vars
const lessons = ref(null);
const isLessonsExist = ref(false);
const selectedLesson = ref("");
const formattedLessons = ref({});
const subject = ref("");
const audience = ref("");
const to = ref("");
const teacherFullName = ref("");


watch(() => props.lessons, (lessons) => {
    if (lessons.none !== true) {
        isLessonsExist.value = true;

        formattedLessons.value = {
            white: [...getFormattedLessonsByColor(lessons, "white")],
            black: [...getFormattedLessonsByColor(lessons, "black")]
        };
    }
    else {
        isLessonsExist.value = false;
    }

});

watch(() => selectedLesson.value, (newValue) => {
    try {
        if (newValue === "white-1") {
            const firstWhiteLesson = formattedLessons.value.white[0];
            subject.value = firstWhiteLesson.subject;
            audience.value = firstWhiteLesson.audience;
            to.value = firstWhiteLesson.to;
            teacherFullName.value = firstWhiteLesson.teacherFullName;

        } else if (newValue === "white-2") {
            const firstWhiteLesson = formattedLessons.value.white[1];
            subject.value = firstWhiteLesson.subject;
            audience.value = firstWhiteLesson.audience;
            to.value = firstWhiteLesson.to;
            teacherFullName.value = firstWhiteLesson.teacherFullName;

        } else if (newValue === "black-1") {
            const firstWhiteLesson = formattedLessons.value.black[0];
            subject.value = firstWhiteLesson.subject;
            audience.value = firstWhiteLesson.audience;
            to.value = firstWhiteLesson.to;
            teacherFullName.value = firstWhiteLesson.teacherFullName;

        } else if (newValue === "black-2") {
            const firstWhiteLesson = formattedLessons.value.black[1];
            subject.value = firstWhiteLesson.subject;
            audience.value = firstWhiteLesson.audience;
            to.value = firstWhiteLesson.to;
            teacherFullName.value = firstWhiteLesson.teacherFullName;
        }
    } catch (_) {
        subject.value = "";
        audience.value = "";
        to.value = "";
        teacherFullName.value = "";
    }
});

function getFormattedLessonsByColor(lessons, color) {

    const arrayFormattedLessons = [];
    if (lessons[color]) {
        lessons[color].forEach(lesson => {
            const splitLesson = lesson.split(/\s+/);
            if (splitLesson.length === 5) {
                arrayFormattedLessons.push({
                    subject: splitLesson[0],
                    to: formatAudienceValue(splitLesson[1]),
                    audience: splitLesson[2],
                    teacherFullName: `${splitLesson[3]} ${splitLesson[4]}`,
                });

            } else if (splitLesson.length === 6) { // в случае Кырг.язык и литер.
                // TODO
                arrayFormattedLessons.push({
                    teacherFullName: `${splitLesson.at(-2)} ${splitLesson.at(-1)}`,
                    audience: splitLesson.at(-3),
                    subject: `${splitLesson.at(-6)} ${splitLesson.at(-5)} ${splitLesson.at(-4)}`,
                    to: 'Обеим',
                });
            } else {
                arrayFormattedLessons.push({
                    subject: splitLesson[0],
                    audience: splitLesson[1],
                    teacherFullName: `${splitLesson[2]} ${splitLesson[3]}`,
                    to: 'Обеим',
                });
            }
        });
    }
    return arrayFormattedLessons;
}

function formatAudienceValue(audience) {
    if (audience === "1пд") return "1 подгруппе";
    else if (audience === "2пд") return "2 подгруппе";
    else if (audience === "лк") return "лк че это";
}

</script>

<style scoped>
.span {
    font-size: 2.5rem;
    font-weight: bolder;
    margin: auto 0;
}

</style>