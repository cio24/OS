#!bin/bash
IFS='
'
TEXT=$1
COUNTER=0

for i in $( cat $TEXT ); do
    ARR[COUNTER]=$i
    let COUNTER++
done

for (( i=$COUNTER -1; i>=0; i-- )); do 
    echo ${ARR[$i]}
done