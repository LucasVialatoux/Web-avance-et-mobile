<template>
    <div>
        <v-label :for="label.id">{{label.title}}</v-label>
        <v-input :type="label.type" :id="label.id" :name="label.name" v-model="label.value" :min="label.min" :max="label.max" @input="update()"/>
    </div>
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