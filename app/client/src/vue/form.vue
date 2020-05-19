<template>
    <fieldset>
        <h2>{{ getTitle }}</h2>
        <myLabel v-for="label in getLabels" v-bind:label="label" v-bind:key="label.id"/>
        <input type="submit" value="Afficher" class="pure-button pure-button-active"/>
    </fieldset>
</template>

<script>
    import {default as myLabel} from './label.vue';

    export default {
        name: "Form",
        components: {
            myLabel
        },
        data: () => {
            return {
                title: '',
                labels: []
            }
        },
        computed: {
            getLabels() {
                return this.$store.getters.getLabels;
            },
            getTitle() {
                return this.$store.getters.getTitle;
            }
        }
    }
</script>