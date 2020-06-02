<template>
    <v-header>
		<h1 id="h1">{{ this.$store.state.title }}</h1>
	</v-header>
</template>

<script>
export default {
    name: 'Header',
}
</script>