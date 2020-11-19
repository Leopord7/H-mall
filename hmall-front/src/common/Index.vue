<template>
    <div class="index">
        <y-header></y-header>
        <router-view></router-view>
        <y-footer></y-footer>
    </div>

</template>

<script>
    import YHeader from './Header'
    import YFooter from './Footer'
    export default {

        components: {
            YHeader,
            YFooter
        }
    }
</script>

<style>
    .index {
        height: 100%;
    }
</style>