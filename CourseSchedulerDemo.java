// Jina Walbourne
// B00930225

public class CourseSchedulerDemo {
    public static void main(String[] args) {
        CourseScheduler scheduler = new CourseScheduler();

        scheduler.addCourse("CS1105");
        scheduler.addCourse("CS1110");
        scheduler.addCourse("CS2110");
        scheduler.addCourse("CS2115");
        scheduler.addCourse("CS2134");
        scheduler.addCourse("CS3110");
        scheduler.addCourse("CS3130");

        scheduler.setPrerequisite("CS1110", "CS1105");
        scheduler.setPrerequisite("CS2110", "CS1110");
        scheduler.setPrerequisite("CS2115", "CS1110");
        scheduler.setPrerequisite("CS2134", "CS1110");
        scheduler.setPrerequisite("CS3110", "CS2115");
        scheduler.setPrerequisite("CS3110", "CS2110");
        scheduler.setPrerequisite("CS3130", "CS2134");

        // check
        System.out.println("The prerequisites for CS3110 are: " + scheduler.getPrerequisites("CS3110"));
        scheduler.removeCourse("CS2115");;
        System.out.println("A path to take CS3110 is: " + scheduler.getPrerequisites("CS3110"));

        System.out.println("Valid course order: " + scheduler.getCourseOrder());
    }
}