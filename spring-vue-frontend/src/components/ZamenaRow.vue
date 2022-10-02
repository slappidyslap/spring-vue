<template>
    <tr>
        <th scope="row"
            time>
            <slot></slot>
        </th>
        <td scope="row">
            <PopUpLink @click="onClickPopupLink($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="onClickPopupLink($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="onClickPopupLink($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="onClickPopupLink($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="onClickPopupLink($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="onClickPopupLink($event)"/>
        </td>
    </tr>
</template>

<script setup>
import PopUpLink from "@/components/PopUpLink.vue";

// eslint-disable-next-line no-undef
const emit = defineEmits(['clickPopupLink']);

function onClickPopupLink(event) {

    const cellData = {};

    const thElement = event.target.parentElement.parentElement.parentElement;
    const thElementChildren = thElement.children;

    cellData.time = thElement.parentElement.children[0].innerText.replace(" ", " - ");
    cellData.dayOfWeekIndex = thElement.cellIndex;

    if (thElementChildren.length === 1) {  // Если правда, то это значит, что в ячейке нету урока (там только ссылка на попап)
        // И в попапе не с чем сравнивать
        emit('clickPopupLink', cellData);

    } else {
        // Уроки могут быть в числителе (белый) или в знаменателе (черный)
        const whiteAndBlackLessons = Array.from(thElementChildren[0].children);

        const responseLessons = {};

        // TODO Либо удалять пустые массивы, либо нет
        responseLessons.white = getFormattedLessonsByColor(whiteAndBlackLessons, "white");
        responseLessons.black = getFormattedLessonsByColor(whiteAndBlackLessons, "black");

        cellData.lessons = responseLessons;

        emit('clickPopupLink', cellData);
    }
}

// В один момент времени может быть так, что первой подгруппе другой урок и второй другой (поэтому цикл, чтоб всех перебрать)
function getFormattedLessonsByColor(whiteAndBlackLessons, color) {

    let lessons;

    if (color === "white") lessons = whiteAndBlackLessons[0];
    else if (color === "black") lessons = whiteAndBlackLessons[1];

    const arrOfFormattedLessons = [];
    Array.from(lessons.children).forEach(lessons => {
        const firstChild = lessons.children[0].outerText;
        const secondChild = lessons.children[1].outerText;
        if (firstChild && secondChild) {
            arrOfFormattedLessons.push(`${firstChild} ${secondChild}`);
        }
    });
    return arrOfFormattedLessons;
}
</script>

<style lang="scss" scoped>

td {
    position: relative;
    font-weight: normal;
}

</style>