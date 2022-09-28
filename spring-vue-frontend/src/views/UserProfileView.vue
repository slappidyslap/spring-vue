<template>
    <div v-if="isUserFound">
        <h1> {{ user.userId }} </h1>
        <h2> {{ user.username }}</h2>
        <h2> {{ user.fullname }}</h2>
        <h2> {{ user.email }}</h2>
        <h2> {{ getRoles }}</h2>
        <img :src="user.userPicUrl"
             alt="null">

        <UploadUserPic v-if="isAuthUserEqualsThisUser"
                       :username="getUsername"></UploadUserPic>
    </div>
    <div v-else>
        <h1> User with username <em> {{ getUsername }} </em> was not found </h1>
    </div>
</template>

<script setup>
import userService from '@/services/user.service';
import UploadUserPic from '@/components/UploadUserPic.vue';
import { computed, onMounted, reactive } from 'vue';
import { useRoute } from 'vue-router';
import authService from '@/services/auth.service';

const route = useRoute();

const user = reactive({
    username: route.params.username,
    userId: 0,
    fullname: "",
    userPicUrl: "",
    email: "",
    roles: [],
});

let isUserFound = true;

async function getUserDataByUsername() {
    try {
        const userData = await userService.getByUsername(user.username);
                
        user.userId = userData.id;
        user.fullname = userData.fullname;
        user.userPicUrl = userData.userPicUrl;
        user.email = userData.email;
        user.roles = userData.roles;

        isUserFound = true;

    } catch (error) {

        isUserFound = false;
        
        return Promise.reject(error);
    }
}

const getRoles = computed(() => {
    return `Roles: ${user.roles.join(' ')}`;
});

const getUsername = computed(() => {
    return user.username;
});

const isAuthUserEqualsThisUser = computed(() => {
    const authUserData = authService.getAuthUserData();

    if (authUserData == null) {
        return false;
    }

    const thisUserUsername = authUserData.username;

    return thisUserUsername == user.username;
});

onMounted(() => {
    getUserDataByUsername();
});

</script>

<style lang="scss" scoped>
</style>