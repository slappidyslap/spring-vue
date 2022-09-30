<template>
    <button @click="onClickAnon"
            class="btn btn-success mx-3">Anon</button>
    <button @click="onClickUser"
            class="btn btn-success mx-3">User</button>
    <button @click="onClickAdmin"
            class="btn btn-success mx-3">Admin</button>

    <h2 class="mt-3">{{ message }}</h2>
</template>

<script setup>
import axios from '@/plugins/axios';
// TODO
// import eventBus from "@/common/eventBus";
import { ref } from 'vue';
const message = ref('text');

async function onClickAnon() {
    try {
        const response = await axios.get('/api/test-auth/anon');
        message.value = response.data;
    } catch(error) {
        // console.error(error);
        // if (error.response && error.response.status === 403) {
        //     eventBus.dispatch("logout");
        // }
        return Promise.reject(error.message);
    }
}

async function onClickUser() {
    try {
        const response = await axios.get('/api/test-auth/user');
        message.value = response.data;
    } catch(error) {
        console.error(error);
    }
}

async function onClickAdmin() {
    try {
        const response = await axios.get('/api/test-auth/admin');
        message.value = response.data;
    } catch(error) {
        // console.error(error);
        // if (error.response && error.response.status === 403) {
        //     eventBus.dispatch("logout");
        // }
        return Promise.reject(error.message);
    }
}
</script>

<style lang="scss" scoped>

</style>