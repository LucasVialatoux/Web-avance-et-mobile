const express = require('express')
//const app = express()
const port = 3000

var admin = require('./routes/admin.js')
var game = require('./routes/game.js')
var jeux = require('./game-operation/index.js')

var fs = require('fs'),
    path = require('path'),
    http = require('http');

var app = require('connect')();
var swaggerTools = require('./node_modules/swagger-tools');
var jsyaml = require('js-yaml');

// swaggerRouter configuration
var options = {
  swaggerUi: path.join(__dirname, './game-operation/swagger.json'),
  controllers: path.join(__dirname, './game-operation/controllers'),
  useStubs: process.env.NODE_ENV === 'development' // Conditionally turn on stubs (mock mode)
};

// The Swagger document (require it, build it programmatically, fetch it from a URL, ...)
var spec = fs.readFileSync(path.join(__dirname,'./game-operation/api/swagger.yaml'), 'utf8');
var swaggerDoc = jsyaml.safeLoad(spec);

//Error handler
app.use(function (err, req, res, next) {
  console.error(err.stack)
  res.status(500).send('[Erreur 500] : Something broke!')
  res.status(404).send('[Erreur 404] : Not Found!')
})

//Servir des fichiers static se trouvant dans le dossier public
app.use('/static', express.static('public'))



//app.listen(port, () => console.log(`Example app listening on port ${port}!`))

app.use('/admin', admin)
app.use('/game', game)



// Initialize the Swagger middleware
swaggerTools.initializeMiddleware(swaggerDoc, function (middleware) {

  // Interpret Swagger resources and attach metadata to request - must be first in swagger-tools middleware chain
  app.use(middleware.swaggerMetadata());

  // Validate Swagger requests
  app.use(middleware.swaggerValidator());

  // Route validated requests to appropriate controller
  app.use(middleware.swaggerRouter(options));

  // Serve the Swagger documents and Swagger UI
  app.use(middleware.swaggerUi());

  // Start the server
  http.createServer(app).listen(port, function () {
  	console.log('##### APP IS RUNNING #####')
    console.log('Your server is listening on port %d (http://localhost:%d)', port, port);
    console.log('Your game is available on (http://localhost:%d/game)', port);
    console.log('Your admin panel is available on (http://localhost:%d/admin)', port);
    console.log('Swagger-ui is available on http://localhost:%d/docs', port);
  });

});
