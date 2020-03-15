var express = require('express')
var router = express.Router()

// Route page du jeu
router.get('/', function (req, res) {
  res.send('PAGE JEUX')
})

module.exports = router