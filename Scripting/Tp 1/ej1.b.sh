#!bin/bash

IFS='
'

texto=$1
caracter=$2

cat $texto | tr '0-9' $caracter