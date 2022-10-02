<template>
    <Form>
        <div class="input-group input-group-sm mb-3">
            <input type="text"
                   class="form-control"
                   aria-label="Text input with checkbox"
                   :disabled="!isFirstCheckBox"
                   :value="getFullName">
            <div class="input-group-text">
                <input class="form-check-input mt-0"
                       type="checkbox"
                       value="true"
                       v-model="isFirstCheckBox"
                       aria-label="Checkbox for following text input">
            </div>
        </div>
        <div class="input-group input-group-sm mb-3">
            <input type="text"
                   class="form-control"
                   aria-label="Text input with checkbox"
                   :disabled="!isSecondCheckBox"
                   :value="getTeachingSubject">
            <div class="input-group-text">
                <input class="form-check-input mt-0"
                       type="checkbox"
                       value="true"
                       v-model="isSecondCheckBox"
                       aria-label="Checkbox for following text input">
            </div>
        </div>
        <div class="d-flex gap-3">
            <div class="input-group input-group-sm mb-3">
                <input type="text"
                       class="form-control"
                       aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-sm"
                       placeholder="Кабинет">
            </div>
            <div style="flex: 1 0 auto;">
                <select class="form-select form-select-sm"
                        aria-label=".form-select-sm example">
                    <option selected
                            disabled
                    >
                        Кому
                    </option>
                    <option value="1">1 подгруппе</option>
                    <option value="2">2 подгруппе</option>
                    <option value="3">Обеим</option>
                </select>
            </div>
        </div>
        <div>
            <textarea class="form-control"
                      id="exampleFormControlTextarea1"
                      rows="2"
                      placeholder="Комментарий"></textarea>
        </div>
    </Form>
</template>

<script setup>
import {computed, ref} from "vue";

const isFirstCheckBox = ref(false);
const isSecondCheckBox = ref(false);

// TODO Получить с vuex
const getFullName = computed(() => {
    return "Бубнева Вера Борисовна";
});

const getTeachingSubject = computed(() => {
    return "Русский язык";
});
</script>
<style lang="scss" scoped>
</style>