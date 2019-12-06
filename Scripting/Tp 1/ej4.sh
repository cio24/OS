#!bin/bash

FILE=$1;
REG='.*\.\(gz\|tar\|bz2\|zip\)'

for i in $( ls $FILE ); do
    if [[ $i == *.bz2 ]]; then
        echo COMPRESS
        echo $i
        bunzip2 $i
    else
        if [[ $i == *.zip ]]; then
            echo $i
            echo COMPRESS
            unzip $i
        else
            if [[ $i == *.tar ]]; then
                echo $i
                echo COMPRESS
                tar $i
            else
                if [[ $i == *.gz ]]; then
                    echo $i
                    echo COMPRESS
                    gunzip $i
                else
                    echo $i
                    echo FILE IS NOT COMPRESSED
                fi
            fi
        fi
    fi
done