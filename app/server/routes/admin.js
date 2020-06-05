var conf = require('../config/config')
var express = require('express')
var router = express.Router()
const axios = require('axios')

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

router.get('/:id', (req, res) => {
    axios.get(`${conf.origin}/auth/user/${req.params.id}`)
    .then(response =>  {
        res.render('user', {user: response.data, origin: conf.origin})
    })
    .catch(error => {
        console.log(error)
    })
})

module.exports = router
