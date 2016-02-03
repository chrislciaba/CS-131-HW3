javac *.java
for x in 1000 10000 100000 1000000
do
    for y in 4 8 16
    do
	printf "\nSynchronized. Number of swaps: $x. Number of threads: $y \n"
	java UnsafeMemory Synchronized $y $x 6 5 6 3 0 3
	printf "\nGetNSet. Number of swaps: $x. Number of threads: $y \n"
	java UnsafeMemory GetNSet $y $x 6 5 6 3 0 3
	printf "\nBetterSafe. Number of swaps: $x. Number of threads: $y \n"
	java UnsafeMemory BetterSafe $y $x 6 5 6 3 0 3
	printf "\nBetterSorry. Number of swaps: $x. Number of threads: $y \n"
        java UnsafeMemory BetterSorry $y $x 6 5 6 3 0 3
	z=`expr $x / 1000`
	printf "\nUnsynchronized. Number of swaps: $z. Number of threads: $y \n"
	java UnsafeMemory Unsynchronized $y $z 6 5 6 3 0 3
    done
done
rm *.class