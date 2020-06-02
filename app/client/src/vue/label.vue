<template>
    <v-row>
        <v-text-field v-if="label.type=='text'" :label="label.title" v-model="label.value" @input="update()"/>
        <v-slider v-if="label.type=='range'" :label="label.title" :min="label.min" :max="label.max" v-model="label.value" @input="update()"></v-slider>
    </v-row>
</template>

<script>
    export default {
        name: 'myLabel',
        props: ['label'],
        methods: {
            update() {
                this.$store.commit('changeLabel', {
                    'id': this.label.id,
                    'value': this.label.value
                })
            }
        }
    }
</script>