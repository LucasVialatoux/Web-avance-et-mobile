#!/bin/bash

IDENTIFIANT="etudiant"
PASSWORD="etudiant"
SERVER="192.168.75.38"
NAMEFILE="index.nginx-debian.html"
LINK="./${NAMEFILE}"
URL="${IDENTIFIANT}@${SERVER}"
ROOT="${URL}:/var/www/html"

sshpass -p "${PASSWORD}" scp -r ${LINK} ${ROOT}