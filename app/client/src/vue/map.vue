<template>
<v-row justify="center">
        <h2 id="titleMap">{{ title }}</h2>
        <div id="map"></div>
</v-row>
</template>

<script>
export default {
    name: 'Map',
    data() {
        return {
            title: 'Carte'
        }
    }
}
</script>