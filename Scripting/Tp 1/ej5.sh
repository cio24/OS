#!bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")
FILE=$1
COUNTER=0
echo PROGRAM START
for i in $( ls $FILE ); do
    NAME=`basename $i`
    NAME_TR=$(echo "$NAME" | tr A-Z a-z | tr -s ' ' | tr ' ' '_') #tr - s te reemplaza una secuenda de lo siguiente ' ' por una sola ocurrencia de lo mismo
    echo $NAME_TR
    echo $i
    mv $i $NAME_TR
done