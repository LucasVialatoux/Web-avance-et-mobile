<template>
<div>
    <form>
        <div>
            <label for="login">Login</label>
            <input type="text" id="login" name="login" v-model="login"/>
        </div>
        <div>
            <label for="mdp">Mdp</label>
            <input type="password" id="mdp" name="mdp" v-model="mdp"/>
        </div>
        <button>Login</button>
    </form>
</div>
</template>

<script>
export default {
    name: "login",
    props: ['login', 'mdp'],
}
</script>

<style scoped>
    div {
        width: 100%;
    }
    form {
        margin: 3px auto;
    }
</style>