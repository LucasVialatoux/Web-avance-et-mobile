'use strict';

var utils = require('../utils/writer.js');

function validURL(str) {
    var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
        '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ // domain name
        '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
        '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
        '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
        '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
    return !!pattern.test(str);
}

function validPosition(str){
    var pattern = new RegExp('^[0-9]*.?[0-9]*:[0-9]*.?[0-9]*$')
    return !!pattern.test(str);
}


/**
 * Get all living resources
 * Returns an array containing the representations of all existing resources in the game if the user is alive, or only that of the user if she is dead
 *
 * returns List
 **/
exports.resourcesGET = () => {
    const data = require('../data').data.data

    return new Promise(function(resolve, reject) {
        if (data.length > 0) {
            resolve(utils.respondWithCode(200, data))
        } else {
            resolve();
        }
    });
}

/**
 * (re)set user's image URL
 * Sets or updates the user's photo/icon/... image file URL
 *
 * resourceId String User's login
 * url String 
 * no response value expected for this operation
 **/
exports.resourcesResourceIdImagePUT = (resourceId,url) => {
    const data = require('../data').data.data

    return new Promise(function(resolve, reject) {
        //URL de l'image est valide
        if (validURL(url)){
            const goodOne = false;

            const tableau = data
            
            //Il y a des ressources
            if (tableau.length > 0) {
                //on parcours chaque objet
                for (const objet in tableau) {
                    //on vérifie qu'on a le bon id
                    if (tableau[objet].id==resourceId){
                        tableau[objet].url = url;
                        resolve(utils.respondWithCode(204,"successful operation"));
                    }
                }
            }
            resolve(utils.respondWithCode(404,"User not found"));
            //url non valide
        } else {
            resolve(utils.respondWithCode(400,"Invalid image URL object"));
        }
    });
}


/**
 * Update user's position
 * Send a LatLng object to the server.
 *
 * resourceId String User's login
 * position LatLng User's position
 * no response value expected for this operation
 **/
exports.resourcesResourceIdPositionPUT = (resourceId,position) => {
    const data = require('../data').data.data

    return new Promise(function(resolve, reject) {
        //Position valide
        if (validPosition(position)){
            const goodOne = false;
            const tableau = data
            //Il y a des ressources
            if (tableau.length > 0) {
                //on parcours chaque objet
                for (const objet in tableau) {
                    //on vérifie qu'on a le bon id
                    if (tableau[objet].id==resourceId){
                        tableau[objet].position = position;
                        resolve(utils.respondWithCode(204,"successful operation"));
                    }
                }
            }
            resolve(utils.respondWithCode(404,"User not found"));
            //position invalide
        } else {
            resolve(utils.respondWithCode(400,"Invalid position object"));
        }
    });
}

