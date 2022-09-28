<template>
    <div>
        <div class="row">
            <div class="col-8">
                <label class="btn btn-default p-0">
                    <input type="file"
                           accept="image/*"
                           ref="fileInput"
                           @change="selectImage" />
                </label>
            </div>
            <div class="col-4">
                <button class="btn btn-success btn-sm float-right"
                        :disabled="!currentImage"
                        @click="upload">
                    Upload
                </button>
            </div>
        </div>
        <div v-if="currentImage"
             class="progress">
            <div class="progress-bar progress-bar-info"
                 role="progressbar"
                 :aria-valuenow="progress"
                 aria-valuemin="0"
                 aria-valuemax="100"
                 :style="{ width: progress + '%' }">
                {{ progress }}%
            </div>
        </div>
        <div v-if="previewImage">
            <div>
                <img class="preview my-3"
                     :src="previewImage"
                     alt="" />
            </div>
        </div>
        <div v-if="message"
             class="alert alert-secondary"
             role="alert">
            {{ message }}
        </div>
    </div>
</template>

<script setup>
import userService from '@/services/user.service';
import { ref, defineProps } from 'vue';

const props = defineProps({
    username: {
        type: String,
        required: true,
    }
});

const currentImage = ref(undefined);
const previewImage = ref(undefined);
const progress = ref(0);
const message = ref("");
const fileInput = ref(null);
const username = ref(props.username);

function selectImage() {

    currentImage.value = fileInput.value.file.files.item(0);
    progress.value = 0;
    message.value = "";
}

async function upload() {
    try {
        progress.value = 0;
    
        await userService.uploadUserPic(
            currentImage.value, 
            username, 
            (e) => progress.value = Math.round((100 * e.loaded) / e.total));
            
    } catch (error) {
        progress.value = 0;
        message.value = "Could not upload the image! " + error;
        currentImage.value = undefined;
    }
}
</script>

<style lang="scss" scoped>
</style>