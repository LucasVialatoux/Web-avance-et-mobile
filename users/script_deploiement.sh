#!/bin/bash

IDENTIFIANT="etudiant"
PASSWORD="etudiant"
SERVER="192.168.75.38"
NAMEFILE="usersspringboot-0.0.1-SNAPSHOT.jar"
LINK="target/${NAMEFILE}"
URL="${IDENTIFIANT}@${SERVER}"
ROOT="${URL}:${NAMEFILE}"
SCRIPT="java -jar ${NAMEFILE} &"

mvn package

sshpass -p "${PASSWORD}" scp -r ${LINK} ${ROOT}

sshpass -p "${PASSWORD}" ssh ${URL} "${SCRIPT}"
