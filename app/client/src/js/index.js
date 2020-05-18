var mylabel = Vue.component('mylabel', {
	props:['label'],
  	template:'<div> '+
  				'<label :for="`${label.for}`">{{ label.title }}</label>'+
  				'<input :id="`${label.id}`" :type="`${label.type}`" :name="`${label.name}`" :value="`${label.value}`" :min="`${label.min}`" :max="`${label.max}`">'+
  			'</div>'
});


var h1 = new Vue({ 
    el: '#h1',
    data: {
        title: 'MifMapApp'
    }
});

var form = new Vue({ 
    el: '#form',
    data: {
        title: 'Position',
        labels: [
	      { title: 'Lattitude : ' ,for:'lat'  ,id: 'lat' , type: 'text'  , value:45.782, name:'lat'},
	      { title: 'Longitude : ' ,for:'lon'  ,id: 'lon' , type: 'text'  , value:4.8656, name:'lon'},
	      { title: 'Zoom : '      ,for:'zoom' ,id: 'zoom', type: 'range' , value:15    , name:'zoom', min:1, max:20},
	    ]
    },
 components: {
  mylabel: mylabel
 }
});

var map = new Vue({ 
    el: '#titleMap',
    data: {
        title: 'Carte'
    }
});