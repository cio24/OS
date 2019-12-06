#!bin/bash

logs=$1
let lvl1=$2+1
let lvl2=$3+1
let lvl3=$4+1


#creo el directorio del arbol (si existe no se crea)
mkdir tree

for log in $( ls $logs ); do

    #obtengo las 3 variables del log según el orden de los parametros
    var1=$(echo $log | cut --delimiter=_ --fields=$lvl1)
    var2=$(echo $log | cut --delimiter=_ --fields=$lvl2)
    var3=$(echo $log | cut --delimiter=. --fields=1 |cut --delimiter=_ --fields=$lvl3) #le saco el .log

    #creo el nuevo nivel de arbol (si existe no se crea)
    mkdir --parents tree/$var1/$var2/$var3

    #copio el log en la hoja correspondiente
    cp $logs/$log tree/$var1/$var2/$var3
done