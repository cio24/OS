#!bin/bash
IFS='
'
ciudades=$1
paisObservado=$2
for ciudad in $( cat $ciudades ); do
    pais=$(echo $ciudad | cut --fields=2)
    
    if [ "$pais" == "$paisObservado" ]; then
        temperatura=$(echo $ciudad | cut --fields=3)
        temperatura=$( echo "$temperatura*1.8+32" | bc)
        nombreCiudad=$(echo $ciudad | cut --fields=1)
        echo ciudad: $nombreCiudad temperatura: $temperatura 
    fi
done