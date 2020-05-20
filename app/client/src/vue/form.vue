<template>
    <fieldset>
        <h2>{{ getTitle }}</h2>
        <myLabel v-for="label in getLabels" v-bind:label="label" v-bind:key="label.id"/>
        <input type="submit" value="Afficher" class="pure-button pure-button-active" />
        <input type="button" value="Test Load VueX" class="pure-button pure-button-active" v-on:click="loadLabels()"/>
    </fieldset>
</template>

<script>
    import {default as myLabel} from './label.vue';
    import $ from 'jquery';
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
        methods: {
            loadLabels() {
                let labels = [];
                for (let i = 0; i < 2; i++) {
                    let val = 42;
                    labels.push({value: val });
                }
                this.$store.dispatch("loadLabels", labels);

                //Récupérer instance de la map
                var map = document.getElementById('map')._leaflet_map;
                //MàJ de la map
                map.setView([$('#lat').val(), $('#lon').val()], $('#zoom').val());

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