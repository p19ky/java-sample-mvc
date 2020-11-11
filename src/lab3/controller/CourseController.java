package lab3.controller;

import lab3.repository.CourseRepository;

public class CourseController {
    private CourseRepository courseRepository = new CourseRepository("courses.txt");

    public CourseController() {}

    public void print() {
        this.courseRepository.printCourses();
    }
}
