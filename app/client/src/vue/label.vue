<template>
    <div>
        <label :for="label.id">{{label.title}}</label>
        <input :type="label.type" :id="label.id" :name="label.name" :value="label.value" :min="label.min" :max="label.max">
    </div>
</template>

<script>
    export default {
        name: 'myLabel',
        props: ['label'],
        data: () => {
            return this.label;
        }
    }
</script>