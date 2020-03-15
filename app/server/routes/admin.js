var express = require('express')
var router = express.Router()

// Route page d'administration
router.get('/', function (req, res) {
  res.send('PAGE ADMIN')
})

module.exports = router