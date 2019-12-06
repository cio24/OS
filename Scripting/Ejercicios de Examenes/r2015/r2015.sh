#!bin/bash

IFS='
'
resultados=$1

function getCiudades {
    ciudades=$1
    for ciudad in $( ls $ciudades -1 ); do
        cat $resultados/$ciudad 
    done
}

function getPresidentes {
    for presidente in $( getCiudades $resultados | sort -k1 | grep -w "presidente" | cut --fields=1 | uniq ); do
        echo $presidente
    done
}

function getVotosTotales {
    votos=0
    for ciudad in $( getCiudades $resultados | grep -w "presidente" | cut --fields=3); do
         let votos=$ciudad+$votos
    done
    echo $votos
}

function getVotosPresidente {
    presidente=$1
    votosTotales=0
    for votos in $( getCiudades $resultados | grep "$presidente" | cut --fields=3 ); do
        let votosTotales=$votos+$votosTotales
    done
    echo $votosTotales
}

votosTotales=$( getVotosTotales )
mayor1=0
mayor2=0


for presidente in $( getPresidentes ); do
    votos=$( getVotosPresidente $presidente )
    if(( $votos > $mayor1 )); then
        mayor1=$votos
        presidente1=$presidente
    else
        if (( $votos > $mayor2 )); then
            mayor2=$votos
            presidente2=$presidente
        fi
    fi
done


booleanResultado=$(echo "$mayor1>$votosTotales*0.45" | bc -l)
if (( $booleanResultado == 1 )); then
    echo hay balotaje por culpa del presidente $presidente1 con votos $mayor1
else
    booleanResultado=$(echo "$mayor1>$votosTotales*0.4" | bc -l)
    if (( $booleanResultado == 1 )); then
        echo dana tiene mas del 40
        booleanResultado=$(echo "$mayor1-$mayor2>$votosTotales*0.10" | bc -l)
        if (( $booleanResultado == 1 )); then
            echo hay balotaje entre $presidente1 y $presidente2 con votos $mayor1 y $mayor2 respectivamente
        fi
    else
        echo NO TIENE MAS DEL 40 $votosTotales
    fi

fi