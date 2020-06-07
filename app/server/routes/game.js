let express = require('express')
let router = express.Router()
let geo = require('../game-operation/controllers/Georesources.js');
const authenticate = require('../middleware')

// Route page du jeu
router.get('/', function (req, res) {
    res.send('PAGE JEUX')
})

// GET method route : /resources
router.get('/resources', authenticate.authenticate, function (req, res) {
    geo.resourcesGET(req, res)
})

// PUT method route : /resources/{resourceid}/position
router.put('/resources/:resourceId/position', authenticate.authenticate, function (req, res) {
    geo.resourcesResourceIdPositionPUT(req, res)
})

// PUT method route : /resources/{resourceid}/image
router.put('/resources/:resourceId/image', authenticate.authenticate, function (req, res) {
    geo.resourcesResourceIdImagePUT(req, res)
})

module.exports = router