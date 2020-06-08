var conf = require('../config/config')
const axios = require('axios')

const template = {
    "role" : "infected",
    "id" : "toto",
    "position" : "",
    "ttl" : 0,
    "url" : "http://example.com/users/toto/avatar.png",
    "blurred" : true,
    "status" : "alive",
    "trophys" : [ {
        "action" : "infected",
        "id" : ""
    }, {
        "action" : "infected",
        "id" : ""
    }]
}

class Data {
    constructor() {
        this.gameStarted = false
        this.data = []
    }
    
    fillData() {
        this.gameStarted = true
        axios.get(`${conf.origin}/auth/users`)
        .then(response =>  {
            for(let user of response.data) {
                axios.get(`${conf.origin}/auth/user/${user}`)
                .then(response =>  {
                    if (response.data.connected) {
                        let tmp = {
                            "role" : "infected",
                            "id" : response.data.login,
                            "position" : "",
                            "ttl" : 0,
                            "url" : "http://example.com/users/toto/avatar.png",
                            "blurred" : true,
                            "status" : "alive",
                            "trophys" : [ {
                                "action" : "infected",
                                "id" : ""
                            }, {
                                "action" : "infected",
                                "id" : ""
                            }]
                        }
                        this.data.push(tmp)
                    }
                })
                .catch(error => {
                    throw 'prob requete user'
                })
            }
        })
        .catch(error => {
            throw 'Prob requete users'
        })

        let cible = {
            "role" : 'cible',
            "id" : 'cible',
            "position" : '45.78:4.86',
            "ttl" : 0,
            "url" : "http://example.com/users/toto/avatar.png",
            "blurred" : false,
            "status" : "alive",
            "trophys" : []
        }
        this.data.push(cible)
    }

    endGame() {
        this.gameStarted = false
        this.data = []
    }
}

const data = new Data()

exports.data = data