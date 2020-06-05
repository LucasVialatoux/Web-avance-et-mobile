#!/bin/bash

IDENTIFIANT="etudiant"
PASSWORD="etudiant"
SERVER="192.168.75.38"
NAMEFILE="public"
LINK="./${NAMEFILE}"
URL="${IDENTIFIANT}@${SERVER}"
ROOT="${URL}:/var/www/html"

sshpass -p "${PASSWORD}" scp -r ${LINK} ${ROOT}
