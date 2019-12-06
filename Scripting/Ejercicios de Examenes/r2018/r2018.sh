#!bin/bash

IFS='
'
file=$1
maxPalabras=$2

for linea in $( cat $file | tr '-' ' ' ); do
    palabras=$( echo $linea | wc --words )
    echo $palabras
    if (( $palabras <= $maxPalabras )); then
        echo $linea >> resultado
    fi
done

lineasEntrada=$( wc --lines $file | cut --delimiter=' ' --fields=1)
lineasSalida=$( wc --lines resultado | cut --delimiter=' ' --fields=1)
echo lineas de entrada $lineasEntrada
echo lineas de salida $lineasSalida
