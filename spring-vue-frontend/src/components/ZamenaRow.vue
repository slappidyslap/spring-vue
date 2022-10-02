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

function onClickPopupLink(event) {

    const response = {};

    const thElement = event.target.parentElement.parentElement.parentElement;
    const thElementChildren = thElement.children;

    response.time = thElement.parentElement.children[0].innerText.replace(" ", " - ");
    response.dayOfWeekIndex = thElement.cellIndex;

    if (thElementChildren.length === 1) {  // Если правда, то это значит, что в ячейке нету урока (там только ссылка на попап)
        // И в попапе не с чем сравнивать
        console.log(response);

    } else {
        // Уроки могут быть в числителе (белый) или в знаменателе (черный)
        const whiteAndBlackLessons = Array.from(thElementChildren[0].children);

        const responseLessons = {};

        // TODO Либо удалять пустые массивы, либо нет
        responseLessons.white = getFormattedLessonsByColor(whiteAndBlackLessons, "white");
        responseLessons.black = getFormattedLessonsByColor(whiteAndBlackLessons, "black");

        response.lessons = responseLessons;

        console.log(response);
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
/*
function getDayOfWeek(num) {
    if (num === 1) return "Понедельник";
    else if (num === 2) return "Вторник";
    else if (num === 3) return "Среда";
    else if (num === 4) return "Четверг";
    else if (num === 5) return "Пятница";
    else if (num === 6) return "Суббота";
}
*/
</script>

<style lang="scss" scoped>

td {
    position: relative;
    font-weight: lighter;
}

td {
    font-weight: bolder;
}

</style>