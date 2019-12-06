#!bin/bash

SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
#las dos lineas anteriores hacen que el for no te itere por espacios sino por saltos de linea (enter) 
DIR=$1

for i in $( ls $DIR | grep java$ ); do # me quedo con todos los archivos ls que termien en java
    cat                     "$DIR/$i" | grep ^import | wc -l #el -wc es word count
done