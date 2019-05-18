#!/usr/bin/env bash
mvn clean package
scp -i /home/hector/Descargas/key1.pem /home/hector/Documentos/checkListDiario/backend/checkListDiarioApi/target/checklistDiarioApi-1.0.jar ec2-user@ec2-18-228-243-69.sa-east-1.compute.amazonaws.com:/opt/services/checkListDiario
