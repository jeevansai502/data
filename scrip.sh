#!/bin/bash

for i in {0..497}
do

j=$((500-$i))
echo $j
dtrx --one here $j
cd top_secret 
j=$(($j-1))
mv $j /home/placements2017/$j
cd ..
rm -rf top_secret

done

