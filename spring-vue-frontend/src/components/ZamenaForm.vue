<template>
    <div class="body">

    </div>
    <Form>
        <div class="form-floating mb-3">
            <input type="email"
                   class="form-control"
                   id="floatingInput"
                   placeholder="name@example.com">
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating ">
            <input type="text"
                   class="form-control"
                   id="floatingInput"
                   name="login"/>
            <label for="floatingInput">Login</label>
            <ErrorMessage name="login"
                          class="error-feedback"/>
        </div>

    </Form>
</template>

<script>
</script>

<style lang="scss" scoped>
.body {
    display: flex;
}
</style>