IFS='
'
match=$2
file=$1

function separador {

    hours=$(echo $1 | cut -d ' ' -f4 | cut -d ':' -f1)
    minutes=$(echo $1 | cut -d ' ' -f4 | cut -d ':' -f2) 
    seconds=$(echo $1 | cut -d ' ' -f4 | cut -d ':' -f3) 
    if (( $hours > 0 || $minutes > 30 || $minutes == 30 && $seconds>0)); then
     echo $1
    fi
}



if [[ -n $match ]]; then #el -n viene del comando test (man test para investigar)
    for i in $( cat $file ); do
        s=$(echo $i | grep $match)
        if [[ $s != "" ]]; then
            separador $s
        fi
    done
else
    for i in $( cat $file ); do
        separador $i
    done
fi

#XQ ME MATCHEA BIEN CON LOS SUBSTRINGS SI NO LE PUSE NADA!
#xq el grep solo es magico
