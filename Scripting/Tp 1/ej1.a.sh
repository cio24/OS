#!bin/bash

IFS='
'

texto=$1


resultado=$(tr 'a-z' 'A-Z' < $texto)

echo "$resultado" > $texto