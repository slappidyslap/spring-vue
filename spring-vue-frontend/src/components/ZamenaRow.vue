<template>
    <th scope="row"
        time>
        <slot></slot>
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
    <th scope="row">
        <PopUp>
            <ZamenaForm/>
        </PopUp>
    </th>
</template>

<script setup>
import PopUp from "@/components/PopUp.vue";
import ZamenaForm from "./ZamenaForm.vue";
</script>

<style lang="scss" scoped>

</style>