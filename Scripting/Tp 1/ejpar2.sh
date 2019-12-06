#!bin/bash

function fibonacci {
    N=$1
    if [[ $N -eq 1 || $N -eq 0 ]]; then
        echo 1
    else
        RESULT=$(getFibonacci $N)
        if [[ $RESULT == "" ]]; then
            let A=$N-1
            let B=$N-2
            let C=$(fibonacci $A)+$(fibonacci $B)
            setFibonacci $N $C
            echo $C
        else
            echo $RESULT 
        fi
    fi
}

function getFibonacci { #getting fibonacci from computed
    N=$1
    RESULT=$(cat ~/computed | grep ^$N: | cut -d ':' -f2)
    echo $RESULT
}

function setFibonacci { #adding fibonacci to computed
    N=$1
    RESULT=$2
    echo $N:$RESULT >> ~/computed
}

fibonacci $1

