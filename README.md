# Disk Scheduling Visualizer

It's a Java-based repository that aims to implement and compare various disk scheduling algorithms. The project focuses on optimizing the movement of the disk head while accessing I/O blocks on cylinders. It provides a comprehensive solution for analyzing and visualizing the performance of different algorithms.

The implemented algorithms include:

- First-Come, First-Served (FCFS)
- Shortest Seek Time First (SSTF)
- SCAN
- C-SCAN
- LOOK
- C-LOOK

Additionally, a newly optimized algorithm is implemented to provide an optimized solution for disk scheduling.

## Usage

The program accepts inputs from the command line or a file. Follow the steps below to run the program:

1. Run the program using the following command:
   
   ```shell
   java -jar jar_files/jfreechart-1.0.1.jar
   ```
  
2. Enter the initial head start cylinder when prompted.

3. Enter the disk I/O request queue when prompted. The queue should be a comma-separated list of cylinder numbers.

4. The program will display the sequence of head movements and the total head movement for each implemented algorithm.

5. The program will also provide a visualization of the head movements using a graphical user interface (GUI).

## Implemented Algorithms

The project implements the following disk scheduling algorithms:

- First-Come, First-Served (FCFS): This algorithm processes the disk I/O requests in the order they arrive, without considering the distance between the requests.

- Shortest Seek Time First (SSTF): This algorithm selects the request with the shortest seek time from the current head position and processes it first.

- SCAN: This algorithm moves the head in one direction (up or down) and processes the requests in that direction until reaching the end of the disk. Then, it reverses direction and repeats the process.

- C-SCAN: Similar to the SCAN algorithm, C-SCAN moves the head in one direction and processes requests until reaching the end of the disk. However, instead of reversing direction, it jumps to the other end of the disk and continues processing requests in the same direction.

- LOOK: LOOK algorithm is similar to SCAN, but instead of moving to the end of the disk, it changes direction when there are no more requests in the current direction.

- C-LOOK: C-LOOK is similar to C-SCAN, but instead of moving to the end of the disk, it reverses direction when there are no more requests in the current direction.

- Newly Optimized Algorithm: This algorithm is based on a combination of SSTF and C-LOOK. It processes the requests that are closest to the current head position first, but it also jumps to the other end of the disk when there are no more requests in that direction. It minimizes both seek time and wait time for each request.

## Newly Optimized Algorithm

The project includes a newly optimized algorithm that aims to minimize head movements and improve disk scheduling efficiency. The algorithm works as follows:

1. Start by sorting the I/O requests in ascending order.

2. Find the closest request to the initial head start cylinder in the sorted list.

3. Process the requests in the following order:
    - Process the requests located before the closest request in the sorted list, moving towards the lower cylinder numbers.
    - Process the requests located after the closest request in the sorted list, moving towards the higher cylinder numbers.

4. Calculate the total head movement for the optimized algorithm.

The optimized algorithm aims to reduce the average seek time and improve the overall disk performance.


## GUI Visualization

The project includes a graphical user interface (GUI) to visualize the sequence of head movements. The GUI provides a graphical representation of the disk with the initial head start cylinder and the movement of the head as it processes the I/O requests.

To launch the GUI visualization, follow the usage instructions mentioned above. The GUI will automatically display the sequence of head movements once the program finishes processing the input.

## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request on the GitHub repository.

## Acknowledgments

The project is inspired by the concepts of disk scheduling algorithms in advanced operating systems course in my faculty.


## License

This program is licensed under the [MIT License](LICENSE.md).
