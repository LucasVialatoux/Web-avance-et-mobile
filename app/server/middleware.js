const conf = require('./config/config')
const axios = require('axios')

exports.authenticate = (req, res, next) => {
    axios.get(`${conf.origin}/auth/authenticate`, {
        headers: {
            'Authentication': req.headers['authentication'],
            'Origin': conf.origin
        }
    })
    .then(response => {
        next()
    })
    .catch(error => {
        console.log(error)
    })
}