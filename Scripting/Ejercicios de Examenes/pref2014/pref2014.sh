#!bin/bash

IFS='
'

#logs=$1
#fechaInicio=$2
#fechaFin=$3



function separarFechaHora {
    fechaHora=$1
    primerCampo=$(echo $fechaHora | cut --delimiter=':' --fields=1)
    segundoCampo=$(echo $fechaHora | cut --delimiter=':' --fields=2)
    tercerCampo=$(echo $fechaHora | cut --delimiter=':' --fields=3)
}


#function fecha1MenorFecha2 {
#    fecha1=$1
#    fecha2=$2
#
#    separarFechaHora $fecha1
#
#    dias1=$primerCampo
#    mes1=$separarFechaHora
#    anios1=$tercerCampo
#
#    dias2=$primerCampo
#    mes2=$separarFechaHora
#    anios2=$tercerCampo
#
#    if (( $anios1 < $anios2 )); then
#        echo 1
#    else
#        if (( $ )); then
#
#        fi
#    fi
#
#}

$date --date="19/01/1234"