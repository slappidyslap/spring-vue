<template>
    <div class="modal fade"
         id="modal"
         tabindex="-1"
         aria-labelledby="label"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title"
                        id="label">
                        Поставить замену
                    </h5>
                    <button type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <ZamenaForm></ZamenaForm>
                </div>
                <div class="modal-footer">
                    <button type="button"
                            class="btn btn-success">
                        Отправить
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import ZamenaForm from "./ZamenaForm";
</script>

<style scoped>

div.modal-content {
    background-color: white;
}

</style>