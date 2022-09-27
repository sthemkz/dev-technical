# iKhokha Tech Check

 
##### . Concurrency

The idea behind the solution lies on couple of factors. An implementation is a threadpool where tasks have been put in a queue for consumption. This has direct link with the number of cores(cpu). while testing on a laptop with 4-cores it's evident that the optimal perfomance is gained when number of cores is equal to number of threads. There's no additional benefit on creating more threads than the number of CPUs available infact the perfomance drops due to factors like context switches. :)