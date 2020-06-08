<template>
    <v-container>
        <myHeader/>
        <myForm/>
        <myMap/>
    </v-container>
</template>

<script>
import {default as myHeader} from './header.vue';
import {default as myForm} from './form.vue';
import {default as myMap} from './map.vue';
import initMap from '../js/map.js';
//import updateMap from '../js/map.js';
import L from 'leaflet';
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';

let idSuivi;

const axios = require('axios')

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
            this.$store.commit("updateLat", position.coords.latitude)
            this.$store.commit("updateLon", position.coords.longitude)
            let coords = [position.coords.latitude, position.coords.longitude]
            //Ajout position
            //L.circle(coords, {radius: 5, color: "red", fillColor: "#fff", fillOpacity: 0.0}).addTo(this.$store.getters.getMapMap)
            if (this.$store.getters.getGameState) {
                axios.put(`${window.origin}/game/resources/` + 
                `${this.$store.getters.getIdUser}/position?position=${this.$store.getters.getPosition}`, undefined,
                {
                    headers: {
                        'Authentication': this.$store.getters.getToken
                    }
                })
                .then(response => {
                    this.$store.commit("updateGameData")
                    this.updateMap()
                })
                .catch(error => {
                    console.log(error)
                })
            } else {
                this.$store.commit("updateGameState")
            }
        },
        updateMap() {
            for (let mark of this.$store.getters.getMapMarkers) {
                this.$store.getters.getMapMap.removeLayer(mark)
            }
            let markArray = [];
            for (let resource of this.$store.getters.getGameData) {
                let marker = L.marker(resource.position.split(':')).addTo(this.$store.getters.getMapMap).bindPopup(resource.id)
                markArray.push(marker)
            }
            //let marker = L.marker([45.78207, 4.86559]).addTo(this.$store.getters.getMapMap).bindPopup('Entrée du bâtiment<br><strong>Nautibus</strong>.').openPopup();
            this.$store.commit("updateMarkers", markArray)
        }
    },
    mounted() {
        let mymap = L.map('map');
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

        mymap.setView([this.$store.state.form.labels[0].value, this.$store.state.form.labels[1].value], this.$store.state.form.labels[2].value);
        
        this.$store.commit('updateMap', mymap);
    },
    destroyed() {
        navigator.geolocation.clearWatch(idSuivi);
    }
}
</script>