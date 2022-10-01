<template>
    <tr>
        <th scope="row"
            time>
            <slot></slot>
        </th>
        <td scope="row">
            <PopUpLink @click="log($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="log($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="log($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="log($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="log($event)"/>
        </td>
        <td scope="row">
            <PopUpLink @click="log($event)"/>
        </td>
    </tr>
</template>

<script setup>
import PopUpLink from "@/components/PopUpLink.vue";

function log(event) {

    const response = {};

    const thElement = event.target.parentElement.parentElement.parentElement;
    const thElementChildren = thElement.children;

    response.time = thElement.parentElement.children[0].innerText.replace(" ", " - ");
    response.dayOfWeek = getDayOfWeek(thElement.cellIndex);

    if (thElementChildren.length < 1) {  // Если правда, то это значит, что в ячейке нету урока
        return; // И в попапе не с чем сравнивать
    }

    // Уроки могут быть в числителе (белый) или в знаменателе (черный)
    const whiteAndBlackLessons = Array.from(thElementChildren[0].children);

    // В один момент времени может быть так, что первой подгруппе другой урок и второй другой

    const responseLessons = {};

    // TODO Либо удалять пустые массивы, либо нет
    const whiteLessons = whiteAndBlackLessons[0];
    let arrForWhiteLessons = [];
    Array.from(whiteLessons.children).forEach(lessons => {
        const firstChild = lessons.children[0].outerText;
        const secondChild = lessons.children[1].outerText;
        if (firstChild && secondChild) {
            arrForWhiteLessons.push(`${firstChild} ${secondChild}`);
        }
    });
    responseLessons.white = arrForWhiteLessons;

    const blackLesson = whiteAndBlackLessons[1];
    let arrForBlackLessons = [];
    Array.from(blackLesson.children).forEach(lessons => {
        const firstChild = lessons.children[0].outerText;
        const secondChild = lessons.children[1].outerText;
        if (firstChild && secondChild) {
            arrForBlackLessons.push(`${firstChild} ${secondChild}`);
        }
    });
    responseLessons.black = arrForBlackLessons;

    response.lessons = responseLessons;

    console.log(response);
}

function getDayOfWeek(num) {
    if (num === 1) return "Понедельник";
    else if (num === 2) return "Вторник";
    else if (num === 3) return "Среда";
    else if (num === 4) return "Четверг";
    else if (num === 5) return "Пятница";
    else if (num === 6) return "Суббота";
}
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