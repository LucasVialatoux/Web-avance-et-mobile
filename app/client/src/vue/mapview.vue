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

export default {
    name: 'MapView',
    components: {
        myHeader,
        myForm,
        myMap
    }
}
</script>