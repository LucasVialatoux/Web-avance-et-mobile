<template>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
            <router-link to="/" class="nav-link">Accueil</router-link>
            </li>
            <li class="nav-item">
            <router-link to="/map" class="nav-link">Map</router-link>
            </li>
        </ul>
        </div>
    </nav>
    <router-view></router-view>
</div>
</template>

<script>
export default {
    name: "App"
}
</script>