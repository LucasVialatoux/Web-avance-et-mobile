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
        },
        data: () => {
            return {
                title: 'Position',
                labels: [
                    { title: 'Lattitude : ',id: 'lat' , type: 'text'  , value:45.782, name:'lat'},
                    { title: 'Longitude : ',id: 'lon' , type: 'text'  , value:4.8656, name:'lon'},
                    { title: 'Zoom : ',id: 'zoom', type: 'range' , value:15    , name:'zoom', min:1, max:20}
                ]
            }
        }
    }
</script>