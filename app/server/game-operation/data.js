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
        this.data = [{
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
        }]
    }
    
    fillData() {
        this.gameStarted = true
        axios.get(`${conf.origin}/auth/users`)
        .then(response =>  {
            for(let user of response.data) {
                axios.get(`${conf.origin}/auth/user/${user}`)
                .then(response =>  {
                    if (response.data.connected) {
                        let tmp = template;
                        tmp.id = response.data.login
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
    }

    endGame() {
        this.gameStarted = false
        this.data = []
    }
}

const data = new Data()

exports.data = data