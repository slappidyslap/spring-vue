<template>
    <main>
        <div class="body">
            <div class="col-md-12">
                <div class="card card-container">
                    <img
                        id="profile-img"
                        src="@/assets/favicon.png"
                        class="profile-img-card"
                    />
                    <Form @submit="onSubmit"
                          :validation-schema="signUpSchema">
                        <div class="form-group mb-3">
                            <label for="username">Username</label>
                            <Field name="username"
                                   type="text"
                                   class="form-control" />
                            <ErrorMessage name="username"
                                          class="error-feedback" />
                        </div>
                        <div class="form-group mb-3">
                            <label for="email">Email</label>
                            <Field name="email"
                                   type="text"
                                   class="form-control" />
                            <ErrorMessage name="email"
                                          class="error-feedback" />
                        </div>
                        <div class="form-group mb-3">
                            <label for="fullname">Fullname</label>
                            <Field name="fullname"
                                   type="text"
                                   class="form-control" />
                            <ErrorMessage name="fullname"
                                          class="error-feedback" />
                        </div>
                        <div class="form-group mb-3">
                            <label for="password">Password</label>
                            <Field name="password"
                                   type="password"
                                   class="form-control" />
                            <ErrorMessage name="password"
                                          class="error-feedback" />
                        </div>
                        
                        <div class="form-group d-flex justify-content-center">
                            <button class="btn btn-success btn-block"
                                    :disabled="isLoading">
                                <span v-show="isLoading"
                                      class="spinner-border spinner-border-sm"
                                      style="margin-right: 5px;"></span>
                                <span>Sign Up</span>
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
        </div>
    </main>
</template>

<script setup>
import * as yup from 'yup';
import { markRaw, ref } from 'vue';
import { useStore } from 'vuex';

const store = useStore();

const isLoading = ref(false);
const message = ref('');
const signUpSchema = markRaw(yup.object().shape({
    username: yup.string().required("Введите никнейм!"),
    password: yup.string().required("Введите пароль!"),
    email: yup.string().email().required("Введите эл-почту!"),
    fullname: yup.string().required("Введите полное имя!"),
}));

async function onSubmit(signUpData) {

    isLoading.value = true;

    try {
        await store.dispatch('auth/signUp', signUpData);
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
