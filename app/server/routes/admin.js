var conf = require('../config/config')
var express = require('express')
var router = express.Router()
const axios = require('axios')
const data = require('../game-operation/data').data

// Route page d'administration
router.get('/', (req, res) => {
    axios.get(`${conf.origin}/auth/users`)
    .then(response =>  {
        res.render('admin', {users: response.data, origin: conf.origin})
    })
    .catch(error => {
        console.log(error)
    })
})

router.get('/options', (req, res) => {
    res.render('options', {game: data, origin: conf.origin})
})

router.get('/user/:id', (req, res) => {
    axios.get(`${conf.origin}/auth/user/${req.params.id}`)
    .then(response =>  {
        res.render('user', {user: response.data, origin: conf.origin})
    })
    .catch(error => {
        console.log(error)
    })
})

module.exports = router
