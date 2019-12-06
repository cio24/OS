#!bin/bash

file=$1
S=$2
E=$3


if (( $S < 1 )); then
    echo S debe ser mayor o igual a 1
else
    if (( $S > $E )); then
        echo E debe ser mayor que S
    else
        let lines=$( wc --lines $file | cut --delimiter=' ' --fields=1 )+1
        if (( $E > $lines )); then
            echo el limite superior supera las lineas maximas
        fi 
    fi
fi

sed -n "$S,$E p" $file