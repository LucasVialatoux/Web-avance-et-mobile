'use strict';

var utils = require('../utils/writer.js');
var Georesources = require('../service/GeoresourcesService');

module.exports.resourcesGET = function resourcesGET (req, res, next) {
  Georesources.resourcesGET()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.resourcesResourceIdImagePUT = function resourcesResourceIdImagePUT (req, res, next) {
  var resourceId = req.params.resourceId;
  var url = req.query.url;
  Georesources.resourcesResourceIdImagePUT(resourceId,url)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.resourcesResourceIdPositionPUT = function resourcesResourceIdPositionPUT (req, res, next) {
  var resourceId = req.params.resourceId;
  var position = req.query.position;
  Georesources.resourcesResourceIdPositionPUT(resourceId,position)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
