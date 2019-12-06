#!bin/bash
echo ej3 start

for i in $( find ~/Desktop/SO/tp1/ej3DirTest -size -100k ); do
    echo $i
    echo d to delete c to compress
    read response
    if [ $response == "d" ]; then
        echo $i >> loger.txt
        rm $i
    else
        if [ $response == "c" ]
        then
            gzip $i
        
        else
            echo no action
        fi
    fi
done
