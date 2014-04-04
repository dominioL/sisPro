#!/bin/bash

metodo=POST
uri=http://localhost:7000/clientes
tipoDeMidia=`echo Content-Type:application/json`
curl -i -X $metodo $uri -H $tipoDeMidia -d @../json/cliente.json
