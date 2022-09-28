// TODO
<template>
    <div>

        <font-awesome-icon icon="fa-solid fa-user-secret" />
    </div>
</template>

<script>
import FontAwesomeIcon from '@/libs/fontAwesome';

export default {
    methods: {
        test() {
            console.log(FontAwesomeIcon);
        }
    },

    mounted () {
        this.test();
    },
        
    components: { FontAwesomeIcon }
};
</script>

<style lang="scss" scoped>
</style>