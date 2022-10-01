<template>
    <ZamenaList></ZamenaList>
</template>

<script setup>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import ZamenaList from "@/components/ZamenaList";
import {onMounted} from "vue";

// eslint-disable-next-line no-unused-vars
function connectByWebSocket() {
    const socket = new SockJS("http://localhost:8080/ws");
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        stompClient.subscribe("/topic/zamenas", zamenaOffer => {
            console.log(zamenaOffer);
        });
    });
}

onMounted(() => {
    // connectByWebSocket();
});
</script>

<style scoped>

</style>