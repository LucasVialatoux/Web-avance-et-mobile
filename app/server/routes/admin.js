var express = require('express')
var router = express.Router()
const axios = require('axios')

// Route page d'administration
router.get('/', async (req, res) => {
  let response = await axios.get('http://localhost:8080/users')

  res.render('admin', {users: response.data})
})

module.exports = router