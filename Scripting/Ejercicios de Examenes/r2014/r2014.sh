#!bin/bash

reporte=$1
desdePuerto=$2
hastaPuerto=$3
protocolo1=$4
protocolo2=$5

function crearReporteIp {
    reporte=$1
    desdePuerto=$2
    hastaPuerto=$3
    protocolo=$4
    mkdir reportesIp
    
    for conexion in $( grep -w $protocolo $reporte | sort --key=6 ); do
        direccion=$( echo $conexion | cut --delimiter=' ' --fields=6)
        puerto=$(echo $direccion | cut --delimiter=':' --fields=2)
        if (( $puerto >= $desdePuerto && $puerto <= $hastaPuerto )); then
            ip=$(echo $direccion | cut --delimiter=':' --fields=1)
            echo $ip >> reportesIp/$protocolo.txt
        fi
    done
}

crearReporteIp $reporte $desdePuerto $hastaPuerto $protocolo1
crearReporteIp $reporte $desdePuerto $hastaPuerto $protocolo2