<template>
    <v-form>
        <h2>{{ getTitle }}</h2>
        <myLabel v-for="label in getLabels" v-bind:label="label" v-bind:key="label.id"/>
        <v-btn>Afficher</v-btn>
    </v-form>
</template>

<script>
    import {default as myLabel} from './label.vue';

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
                return this.$store.state.form.title;
            }
        }
    }
</script>