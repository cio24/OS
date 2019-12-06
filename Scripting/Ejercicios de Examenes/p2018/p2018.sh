#!bin/bash
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

resultados=$1
setGanadosEquipo1=0
setGanadosEquipo2=0

for set in $( cat $resultados ); do
    puntosEquipo1=0
    puntosEquipo2=0
    
    puntosEquipo1=$(echo $set | cut --fields=1 )+$( echo $set | cut --fields=2)
    puntosEquipo2=$( echo $set | cut --fields=3 )+$( echo $set | cut --fields=4 )

    if(($puntosEquipo1 > $puntosEquipo2));then
        let setGanadosEquipo1++
    else
        if (( $puntosEquipo1 < $puntosEquipo2 )); then
            let setGanadosEquipo2++
        fi
    fi
done

echo set ganados del equipo 1: $setGanadosEquipo1
echo set ganados del equipo 2: $setGanadosEquipo2

if (( $setGanadosEquipo1 > $setGanadosEquipo2 )); then
    echo el ganador es el equpo 1
else
    if (( $setGanadosEquipo1 < $setGanadosEquipo2 )); then
        echo el ganador es el equpo 2
    else
        echo empataron
    fi
fi 