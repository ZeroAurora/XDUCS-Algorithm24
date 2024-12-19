JAVA_OPTS="-Xmx1G -Xms1G -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+AlwaysPreTouch -cp ../lib/algs4.jar:."

do_test() {
    echo "$2 Numbers"

    echo "Run for 10 times"

    for i in {1..10}
    do
        java $JAVA_OPTS DataGenerator $2 | java $JAVA_OPTS $1
    done
}

echo "===== Compile ====="

javac -cp ../lib/algs4.jar:. *.java

echo "===== Insertion Sort ====="

do_test InsertionSort 1000
do_test InsertionSort 10000
do_test InsertionSort 100000
# do_test InsertionSort 1000000 # too slow

echo "===== Top-down Merge Sort ====="

do_test TopDownMergeSort 1000
do_test TopDownMergeSort 10000
do_test TopDownMergeSort 100000
do_test TopDownMergeSort 1000000

echo "===== Bottom-up Merge Sort ====="

do_test BottomUpMergeSort 1000
do_test BottomUpMergeSort 10000
do_test BottomUpMergeSort 100000
do_test BottomUpMergeSort 1000000

echo "===== Random Quick Sort ====="

do_test RandomQuickSort 1000
do_test RandomQuickSort 10000
do_test RandomQuickSort 100000
do_test RandomQuickSort 1000000

echo "===== Quicksort with Dijkstra 3-way Partition ====="

do_test ThreeWayQuickSort 1000
do_test ThreeWayQuickSort 10000
do_test ThreeWayQuickSort 100000
do_test ThreeWayQuickSort 1000000

echo "===== Done ====="
