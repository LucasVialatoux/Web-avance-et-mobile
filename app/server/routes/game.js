var express = require('express')
var router = express.Router()
var geo = require('../game-operation/controllers/Georesources.js');

// Route page du jeu
router.get('/', function (req, res) {
  res.send('PAGE JEUX')
})

// GET method route : /resources
router.get('/resources', function (req, res) {
  geo.resourcesGET(req, res)
})

// PUT method route : /resources/{resourceid}/position
router.put('/resources/:resourceId/position', function (req, res) {
  geo.resourcesResourceIdPositionPUT(req, res)
})

// PUT method route : /resources/{resourceid}/image
router.put('/resources/:resourceId/image', function (req, res) {
  geo.resourcesResourceIdImagePUT(req, res)
})

module.exports = router