#!bin/bash

IFS='
'

textos=$1
mkdir palabras

function getPalabras {
    texto=$1
    cat $textos/$texto | tr ',' ' ' | tr ':' ' ' | tr ';' ' ' | tr '.' ' ' | tr '-' ' ' | tr '!' ' ' | tr '\n' ' '| tr -s ' ' | tr ' ' '\n' | tr A-Z a-z
}


function getPalabrasDiferentes {
    texto=$1
    getPalabras $texto | sort | uniq
}

function getMayorAparicion {
    maxApariciones=0
    texto=$1
    for palabra in $( getPalabrasDiferentes $texto ); do
        apariciones=$( getPalabras $texto | grep -w $palabra | wc -l)
        if (( $apariciones > $maxApariciones )); then
            maxApacionios=$apariciones
        fi
    done
    echo $maxFreq
}

#main

for texto in $( ls $textos -1 ); do
    if [[ $texto != excluded.txt ]]; then
        maxApariciones=$( getMayorAparicion $texto )

        for palabra in $( getPalabrasDiferentes $texto ); do
            apariciones=$(getPalabras $textos | grep -w $palabra | wc -l)
            resultado=$(echo "($apariciones / $maxApacionios) > 0.5" | bc -l)
            if(( $resultado == 1 )); then
                mkdir palabras/$palabra
                cp $textos/$texto palabras/$palabra
            fi

        done
    fi
done