let express = require('express')
let router = express.Router()
let geo = require('../game-operation/controllers/Georesources.js');
const authenticate = require('../middleware')
const data = require('../game-operation/data').data

// Route page du jeu
router.get('/', function (req, res) {
    res.send('PAGE JEUX')
})

router.post('/start', function (req, res) {
    if (data.gameStarted) {
        res.sendStatus(401)
        return
    }

    try {
        data.fillData()
    } catch (e) {
        res.sendStatus(500)
    }
    res.sendStatus(204)
})

router.post('/end', function (req, res) {
    if (!data.gameStarted) {
        res.sendStatus(401)
        return
    }

    data.endGame()

    res.sendStatus(204)
})

router.get('/state', authenticate.authenticate, function (req, res) {
    res.send(data.gameStarted)
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