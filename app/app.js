const express = require('express')
const app = express()
const port = 3000

//Error handler
app.use(function (err, req, res, next) {
  console.error(err.stack)
  res.status(500).send('[Erreur 500] : Something broke!')
  res.status(404).send('[Erreur 404] : Not Found!')
})

//Servir des fichiers static se trouvant dans le dossier public
app.use('/static', express.static('public'))



app.listen(port, () => console.log(`Example app listening on port ${port}!`))