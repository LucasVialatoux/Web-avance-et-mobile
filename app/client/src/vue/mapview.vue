<template>
    <div>
        <myHeader/>
        <myForm/>
        <myMap/>
    </div>
</template>

<script>
import {default as myHeader} from './header.vue';
import {default as myForm} from './form.vue';
import {default as myMap} from './map.vue';
import initMap from '../js/map.js';
import updateMap from '../js/map.js';
import $ from 'jquery';

export default {
    name: 'MapView',
    components: {
        myHeader,
        myForm,
        myMap
    },
    mounted(){
        let mymap = L.map('map');
        L.Icon.Default.mergeOptions({
            iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png').default,
            iconUrl: require('leaflet/dist/images/marker-icon.png').default,
            shadowUrl: require('leaflet/dist/images/marker-shadow.png').default,
        });
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibTFpZjEzIiwiYSI6ImNqczBubmhyajFnMnY0YWx4c2FwMmRtbm4ifQ.O6W7HeTW3UvOVgjCiPrdsA', {
            maxZoom: 20,
            minZoom: 1,
            attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
                '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
                'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
            id: 'mapbox.streets'
        }).addTo(mymap);
        L.marker([45.78207, 4.86559]).addTo(mymap).bindPopup('Entrée du bâtiment<br><strong>Nautibus</strong>.').openPopup();
        mymap.setView([$('#lat').val(), $('#lon').val()], $('#zoom').val());
    }
}
</script>