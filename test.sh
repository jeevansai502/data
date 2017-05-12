#!/bin/bash          

rec(){
	if [[ $2 -eq "0" ]];
	then 
		var=$((2#$1))
		cd cod
		rm .h.bin

		for i in {0..7}
	do
		start=$(($i * 8))
#		end=$(($start + 8))
#		start=$(($start - 1))
#		val=$(echo $1 | cut -c$start-$end)
			val=${i:$start:8}
	printf "\\$(printf '%o' "$((2#$val))")"
		done

#		printf "\\$(printf '%o' "$((2#$1))")" > .h.bin
		cd ..
		rm -rf cod.zip
		zip -r cod.zip cod
		var1=$(md5sum cod.zip)

		if [[ $var1 == bbff79b* ]]
			then
				echo $var1
				echo $1
				fi
		else
			rec $1"0"  $(($2 - 1))
				rec $1"1"  $(($2 - 1)) 		
				fi 

}

rec "" 64

#cd cod
#echo "a" > .h
#cd ..
#zip -r cod.zip cod
#md5sum cod.zip > a.txt   
