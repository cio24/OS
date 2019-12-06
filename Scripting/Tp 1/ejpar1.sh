#!bin/bash

TEXT=$1
MAX=0

function comando {
    tr '\n' ' '  < $1 | tr ',' ' ' | tr '.' ' ' |tr -s ' ' | tr ' ' '\n' | sort | uniq
}
#echo $COMMAND

for pal in $(comando $TEXT); do
    #grep machea con las palabras que son iguales a $pal
    FREQ=$(tr '\n' ' '  < $TEXT | tr ',' ' ' | tr '.' ' ' |tr -s ' ' | tr ' ' '\n' |grep ^$pal$ |wc -l)
    if [[ $FREQ > $MAX ]]; then
        let MAX=FREQ
       # echo el maximo ahora es: $MAX
    fi
done

for pal in $(comando $TEXT); do
    #grep machea con las palabras que son iguales a $pal
    FREQ=$(tr '\n' ' '  < $TEXT | tr ',' ' ' | tr '.' ' ' |tr -s ' ' | tr ' ' '\n' |grep ^$pal$ |wc -l)
    echo $pal $(echo $FREQ / $MAX | bc -l)
done