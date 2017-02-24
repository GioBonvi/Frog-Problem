# Frog Problem

This is a quick and dirty MATLAB/Octave script to solve [The Frog Problem](https://www.youtube.com/watch?v=X3HDnrehyDM&t=0s).

## Approach adopted

My first approach was a simple bruteforce of the problem via recursion: this proved quite effective for little ponds, however it was extremely slow and inefficient for ponds of length of 20 or more.  
This led me to a small but outstandingly effective improvement. I realized that I was losing a lot of time checking unnecessary moves. In my first version when a pile of frogs was moved from index A to index B of a pond the program had a new pond to analyze and started evaluating each move from index 1.

Here is an example to clarify:
 - starting pond ````101101````
 - check if index 1 can be moved: no, check next index
 - check if index 2 can be moved: no, check next index
 - check if index 3 can be moved: yes, move it
 - new pond ````100201````
 - check if index 1 can be moved: no, check next index
 - check if index 2 can be moved: no, check next index
 - check if index 3 can be moved: no, check next index
 - check if index 4 can be moved: yes, move it
 - new pond ````100003````

and so on...

In fact I realized it was sufficient to check like this:
 - starting pond ````101101````
 - check if index 1 can be moved: no, check next index
 - check if index 2 can be moved: no, check next index
 - check if index 3 can be moved: yes, move it
 - new pond ````100201````, start the search from index 3+1 as previous piles were not changed and so we know they cannot be moved
 - check if index 4 can be moved: yes, move it
 - new pond ````100003````, start the search from index 4+1 as previous piles were not changed and so we know they cannot be moved

and so on...

This improvement lets the program analyze ponds of length 5 to 35 in less than 10 minutes compared to the previous result of 5 to 20 in the same amount of time.

## The files

The file ````checkPond.m```` contains the function ````checkPond```` and the function ````move````, used by ````checkPond````.  

The file ````test.m```` is a simple script which runs ````checkPond```` against ponds of increasing length outputting wether or not they are solvable.

## Results

I've tested all ponds of the form ````1011...1101```` with length 5-39 (included) and they all are solvable except those with length 13 or between 5 and 11 (included).

## License

This project is released under the MIT License: see the ````LICENSE```` file for details.
