<template>
    <section>
        <h2 id="titleMap">{{ title }}</h2>
        <div id="map"></div>
    </section>
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