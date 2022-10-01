<template>
    <tr>
        <th scope="row"
            time>
            <slot></slot>
        </th>
        <th scope="row">
            <PopUpLink/>
        </th>
        <th scope="row">
            <PopUpLink/>
        </th>
        <th scope="row">
            <PopUpLink/>
        </th>
        <th scope="row">
            <PopUpLink/>
        </th>
        <th scope="row">
            <PopUpLink/>
        </th>
        <th scope="row">
            <PopUpLink/>
        </th>
    </tr>
</template>

<script setup>
import PopUpLink from "@/components/PopUpLink.vue";
</script>

<style lang="scss" scoped>

th:not([time]) {
    position: relative;
    font-weight: normal;
}

th[time] {
    font-weight: bolder;
}

</style>