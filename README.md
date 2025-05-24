# Course Scheduler (Java)

A command-line application that simulates a university course scheduling system with support for prerequisites, cycle detection, and topological sorting. Built using Java and graph traversal algorithms.

## Features

- **Add/Drop Courses:** Add new courses and remove existing ones
- **Set Prerequisites:** Define prerequisite relationships between courses
- **Cycle Detection:** Automatically detects and prevents circular dependencies using DFS
- **View Direct Prerequisites:** Display immediate prerequisites for any course
- **Get Full Prerequisite Path:** Display a valid sequence of courses to take before reaching a given course
- **Topological Sort:** Generate a valid order of all courses that satisfies all prerequisites

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Depth First Search (DFS) for cycle detection and topological sort
- ArrayLists used to represent a course graph via adjacency lists

## Sample Functionalities

- View direct prerequisites: The prerequisites for CS3110 are: CS2110, CS2115
- View all prerequisites in valid order: A path to take CS3110 is: CS1105, CS1110, CS2110, CS2115, CS3110
- Detect cyclic dependencies: Cycle detected: CS2110 cannot be a prerequisite for CS1105
- Topological course ordering: CS1105, CS1110, CS2110, CS2134, CS2115, CS3110, CS3130

## Time Complexity

Each method includes in-line comments with time complexity analysis (e.g., `O(V + E)` for DFS-based methods). This project focuses on correctness and clarity over raw performance.

## File Structure

- `CourseScheduler.java`: Main class containing course and prerequisite management
- All code is written following clean object-oriented principles

## Background

This project was developed as part of CSCI 2110 assignment

## How to Run

1. Clone the repo
2. Open in IntelliJ or any Java IDE
3. Run `CourseScheduler.java` to test the scheduler with pre-defined course data and prerequisite rules. You can modify the demo file to test different courses, prerequisites, and edge cases.
