import _ from 'lodash';
import $ from 'jquery';
import 'leaflet';
import 'purecss';
import '../../node_modules/leaflet/dist/leaflet.css';
import '../css/style.css';

L.Map.addInitHook(function () {
  // Store a reference of the Leaflet map object on the map container,
  // so that it could be retrieved from DOM selection.
  // https://leafletjs.com/reference-1.3.4.html#map-getcontainer
  this.getContainer()._leaflet_map = this;
});
let mymap;
// initialisation de la map
function initMap(){
	mymap = L.map('map');
	//updateMap();

	// Création d'un "tile layer" (permet l'affichage sur la carte)
	/*L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibTFpZjEzIiwiYSI6ImNqczBubmhyajFnMnY0YWx4c2FwMmRtbm4ifQ.O6W7HeTW3UvOVgjCiPrdsA', {
			maxZoom: 20,
			minZoom: 1,
			attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
				'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
			id: 'mapbox.streets'
		}).addTo(mymap);
*/
	// Ajout d'un marker
	//L.marker([45.78207, 4.86559]).addTo(mymap).bindPopup('Entrée du bâtiment<br><strong>Nautibus</strong>.').openPopup();
}





// Mise à jour de la map
export default function updateMap() {
	// Affichage à la nouvelle position
	mymap.setView([$('#lat').val(), $('#lon').val()], $('#zoom').val());

	// La fonction de validation du formulaire renvoie false pour bloquer le rechargement de la page.
	return false;
}

/*function component() {
	const element = document.createElement('div');

	// Lodash, now imported by this script
	element.innerHTML = _.join(['Test', 'webpack css'], ' ');
	element.classList.add('hello');

	return element;
}
document.body.appendChild(component());*/