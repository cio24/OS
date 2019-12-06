
SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

#AVERIGUAR COMO MIERDA ESCAPAR EL ~ QUE NO ME DEJA ESCRIBIR LAS RUTAS MÁS CORTAS LPM
function getPages { 
    PRINTERS_CONF='/home/horacio/etc/cups/printers.conf'
    TEXT=$1
    ID=$2
    FORMAT=$3
    
    #ESTO ES MEDIO CHANCHO PERO ANDA JAJA
    MAX_LINES=$(cat $PRINTERS_CONF | grep ^$ID:$FORMAT | cut -d ':' -f3)
    MAX_CHARS=$(cat $PRINTERS_CONF | grep ^$ID:$FORMAT | cut -d ':' -f4)

    #LOS MODULOS QUE SE CALCULAN A CONTINUACIÓN SE PODRÍAN AHORRAR
    #SI LA DIVISIÓN SE QUEDARA CON EL TECHO Y NO CON EL PISO JAJA

    LINES=0
    for i in $( cat $TEXT ); do
        LINE_LENGTH=${#i}
        let LINES=$LINES+$(( $LINE_LENGTH / $MAX_CHARS ))
        MOD=$(( $LINE_LENGTH % 80 ))
        if [[ !$MOD -eq 0 ]]; then
            let LINES++
        fi
    done
    PAGES=$(( $LINES / $MAX_LINES ))
        let MOD=$(( $LINES % $MAX_LINES ))
    if [[ !$MOD -eq 0 ]]; then
        let PAGES++
    fi
    echo Lines: $LINES
    echo Pages: $PAGES
}

getPages $1 $2 $3