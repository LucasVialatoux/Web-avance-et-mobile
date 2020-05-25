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
import L from 'leaflet';
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';

let idSuivi;
let mymap;

export default {
    name: 'MapView',
    components: {
        myHeader,
        myForm,
        myMap
    },
    methods: {
        updatePos: function(position) {
            //MàJ store
            this.$store.commit("updateLat", position.coords.latitude);
            this.$store.commit("updateLon", position.coords.longitude);
            let coords = [position.coords.latitude, position.coords.longitude];
            console.log(coords);
            //Ajout position
            L.circle(coords, {radius: 5, color: "red", fillColor: "#fff", fillOpacity: 0.0}).addTo(mymap);
        }
    },
    mounted(){
        mymap = L.map('map');
        //connaître géolocalisation
        idSuivi = navigator.geolocation.watchPosition(position => {
                this.updatePos(position);
            });
        let DefaultIcon = L.icon({
            iconUrl: icon,
            shadowUrl: iconShadow
        });

        L.Marker.prototype.options.icon = DefaultIcon;
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
    },
    destroyed() {
        navigator.geolocation.clearWatch(idSuivi);
    }

}
</script>