<template>
    <v-form>
        <h2>{{ getTitle }}</h2>
        <myLabel v-for="label in getLabels" v-bind:label="label" v-bind:key="label.id"/>
        <input type="submit" value="Afficher" class="pure-button pure-button-active" />
    </v-form>
</template>

<script>
    import {default as myLabel} from './label.vue';
    import $ from 'jquery';
    export default {
        name: "Form",
        components: {
            myLabel
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