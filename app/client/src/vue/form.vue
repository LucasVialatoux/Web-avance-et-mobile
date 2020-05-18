<template>
    <fieldset>
        <myLabel v-for="label in labels" v-bind:label="label" v-bind:key="label.id"/>
        <input type="submit" value="Afficher" class="pure-button pure-button-active"/>
    </fieldset>
</template>

<script>
    import {default as myLabel} from './label.vue';

    export default {
        name: "Form",
        components: {
            myLabel
        }
    }
</script>