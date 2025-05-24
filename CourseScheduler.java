// Jina Walbourne
// B00930225

import java.util.*;

public class CourseScheduler {
    // attributes
    private final List<String> courses;
    private final List<List<String>> prerequisites;

    // constructor
    public CourseScheduler() {
        courses = new ArrayList<>();
        prerequisites = new ArrayList<>();
    }

    // method that adds a course by checking if a course is already in the list
    // time complexity: O(n): n is the number of courses, searches linearly
    public void addCourse(String course) {
        if(!courses.contains(course)) {
            courses.add(course);
            prerequisites.add(new ArrayList<>());
        }
    }

    // method that removes a course by iterating through list of prerequisite
    // time complexity: O(n^2): there's a nested for loop where n is the number of courses
    public void removeCourse(String course) {
        int index = courses.indexOf(course);
        if(index != -1) {
            courses.remove(index);
            prerequisites.remove(index);

            // iterates through list and removes a course from the prerequisite list
            for(int i = 0; i < prerequisites.size(); i++) {
                List<String> list = prerequisites.get(i);
                for(int j = 0; j < list.size(); j++) {
                    if(list.get(j).equals(course)) {
                        list.remove(j);
                        j--;
                    }
                }
            }
        } else {
            System.out.println("Course does not exist: " + course);
        }
    }

    // method that sets prerequisites by adding the course and its prerequisite to schedule
    // time complexity: O(n^2): each addCourse(course) and addCourse(prerequisite) takes O(n), but the method
    public void setPrerequisite(String course, String prerequisite) {
        addCourse(course);
        addCourse(prerequisite);

        int index = courses.indexOf(course);
        prerequisites.get(index).add(prerequisite);

        // removes prerequisite if there is a cycle
        if (isCyclic()) {
            prerequisites.get(index).remove(prerequisite);
            System.out.println("Cycle detected: " + course + " cannot be a prerequisite for " + prerequisite);
        }
    }

    // method that checks if there's a cycle in graph
    // time complexity: O(n^2): loop runs n times * traversing through all nodes to check for edges = n*n
    private boolean isCyclic() {
        boolean[] checked = new boolean[courses.size()];
        boolean[] recStack = new boolean[courses.size()];

        // iterates through courses and calls helper method to check for cycle
        for(int i = 0; i < courses.size(); i++) {
            if(isCyclicCheck(i, checked, recStack)) {
                return true;
            }
        }
        return false;
    }

    // method that helps isCyclic() by using recursion recStack to check for cycle
    private boolean isCyclicCheck(int courseIndex, boolean[] checked, boolean[] recStack) {
        // if cycle is detected
        if(recStack[courseIndex]) {
            return true;
        }
        // if cycle is not detected
        if(checked[courseIndex]) {
            return false;
        }
        checked[courseIndex] = true;
        recStack[courseIndex] = true;

        // loop through all prerequisites of current course and check for cycle
        List<String> list = prerequisites.get(courseIndex);
        for(int i = 0; i < list.size(); i++) {
            int prereqIndex = courses.indexOf(list.get(i));
            if(isCyclicCheck(prereqIndex, checked, recStack)) {
                return true;
            }
        }

        recStack[courseIndex] = false;
        return false;
    }

    // method that returns prerequisites
    // time complexity: O(n): linear search
    public List<String> getPrerequisites(String course) {
        int index = courses.indexOf(course);
        if(index != -1) {
            return new ArrayList<>(prerequisites.get(index));
        } else {
            System.out.println("Course does not exist: " + course);
            return new ArrayList<>();
        }
    }

    // method that recursively gets all prerequisites
    // time complexity: O(n^2): linear search * traversing through list = n*n
    public List<String> getAllPrerequisites(String course) {
        List<String> result = new ArrayList<>();
        boolean[] checked = new boolean[courses.size()];
        int index = courses.indexOf(course);

        if(index != -1) {
            getAllPrerequisitesCheck(index, checked, result);
        } else {
            System.out.println("Course does not exist: " + course);
        }
        return result;
    }

    // method that helps getAllPrerequisites() by using recursion to find all prerequisites
    public void getAllPrerequisitesCheck(int courseIndex, boolean[] checked, List<String> result) {
        if(!checked[courseIndex]) {
            checked[courseIndex] = true;

            // loops through list and gets its prerequisite recursively
            List<String> list = prerequisites.get(courseIndex);
            for(int i = 0; i < list.size(); i++) {
                int prereqIndex = courses.indexOf(list.get(i));
                if(prereqIndex != -1) {
                getAllPrerequisitesCheck(prereqIndex, checked, result);
                }
            }
            // if course is not already in the result list, it adds it
            if(!result.contains(courses.get(courseIndex))) {
            result.add(courses.get(courseIndex));
            }
        }
    }

    // method that returns topological order of courses
    // time complexity: O(n^2): linear search * traversing through list = n*n
    public List<String> getCourseOrder() {
        int[] indegree = new int[courses.size()];

        // loops through list of prerequisites and calculates indegree
        for(int i = 0; i < prerequisites.size(); i++) {
            List<String> list = prerequisites.get(i);
            for(int j = 0; j < list.size(); j++) {
                int prereqIndex = courses.indexOf(list.get(j));
                indegree[prereqIndex]++;
            }
        }
        // loop through courses with no prerequisites
        List<Integer> reqList = new ArrayList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                reqList.add(i);
            }
        }
        List<String> result = new ArrayList<>();
        int front = 0;

        // loop while performing topological sorting
        while(front < reqList.size()) {
            int courseIndex = reqList.get(front);
            front++;

            // adds course to result
            result.add(0, courses.get(courseIndex));

            // loop through list and finds index, then decreases the indegree
            for(int i = 0; i < prerequisites.get(courseIndex).size(); i++) {
                int prereqIndex = courses.indexOf(prerequisites.get(courseIndex).get(i));
                indegree[prereqIndex]--;
                if(indegree[prereqIndex] == 0) {
                    reqList.add(prereqIndex);
                }
            }
        }
        // there's a cycle if the result does not have all courses
        if(result.size() != courses.size()) {
            System.out.println("Cycle detected: No valid course order possible.");
            return new ArrayList<>();
        }
        return result;
    }
}