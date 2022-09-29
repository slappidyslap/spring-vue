<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
        <div class="container">
            <a class="navbar-brand"
               @click="this.$router.push('/')">
                <img src="@/assets/favicon.png"
                     alt=""
                     width="60"
                     height="60">
            </a>
            <button class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end"
                 id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link"
                       @click="this.$router.push('/zamenas')">Замены</a>
                    <a class="nav-link"
                       @click="this.$router.push('/test-auth')">Authentication testing </a>
                    <a class="nav-link"
                       @click="this.$router.push('/signup')">Sign Up</a>
                    <a class="nav-link"
                       @click="this.$router.push('/login')">Sign In</a>
                    <a class="nav-link"
                       @click="onClickLogout">Logout</a>
                </div>
            </div>
        </div>
    </nav>
</template>

<script setup>
import eventBus from '@/common/eventBus';
import { onBeforeUnmount, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

const store = useStore();
const router = useRouter();

function onClickLogout() {
    store.dispatch('auth/logout');
    router.push('/login');
}

onMounted(() => eventBus.on("logout", () => this.logOut()));

onBeforeUnmount(() => eventBus.remove("logout"));

</script>

<style lang="scss" scoped>
a.nav-link {
    font-size: 24px;
    cursor: pointer;
    
}
</style>