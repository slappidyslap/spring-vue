<template>
    <main>
        <div class="body">
            <div class="card card-container">
                <img
                    id="profile-img"
                    src="@/assets/favicon.png"
                    class="profile-img-card"
                    alt="..."/>
                <Form @submit="onSubmit"
                      :validation-schema="loginSchema">
                    <div class="form-group">
                        <label for="login">Login</label>
                        <Field name="login"
                               type="text"
                               class="form-control"/>
                        <ErrorMessage name="login"
                                      class="error-feedback"/>
                    </div>
                    <div class="form-group mb-3">
                        <label for="password">Password</label>
                        <Field name="password"
                               type="password"
                               class="form-control"/>
                        <ErrorMessage name="password"
                                      class="error-feedback"/>
                    </div>

                    <div class="form-group d-flex justify-content-center">
                        <button class="btn btn-success btn-block"
                                :disabled="isLoading">
                            <span
                                v-show="isLoading"
                                class="spinner-border spinner-border-sm"
                                style="margin-right: 5px;">
                            </span>
                            <span>Login</span>
                        </button>
                    </div>

                    <div class="form-group">
                        <div v-if="message"
                             class="alert alert-danger"
                             role="alert">
                            {{ message }}
                        </div>
                    </div>
                </Form>
            </div>
        </div>
    </main>
</template>

<script setup>
import {markRaw, ref} from 'vue';
import {useStore} from 'vuex';
import * as yup from 'yup';

const store = useStore();

const isLoading = ref(false);
const message = ref('');

const loginSchema = markRaw(yup.object().shape({
    login: yup.string().required("Введите логин!"),
    password: yup.string().required("Введите пароль!"),
}));

async function onSubmit(loginData) {

    isLoading.value = true;

    try {
        await store.dispatch('auth/signIn', loginData);
        isLoading.value = false;
        message.value = '';

    } catch (error) {
        isLoading.value = false;
        message.value = error.message;
    }

}
</script>

<style lang="scss" scoped>
.card-container {
    padding: 0 50px 30px 50px;
}

main {
    display: flex;
    justify-content: center;
}

.body {
    width: 400px;
}
</style>
