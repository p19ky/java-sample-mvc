package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {
    public boolean register(Course course, Student student){
        return false;
    };

    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> courses = new ArrayList<Course>();
        return courses;
    };

    public List<Student> retrieveStudentsEnrolledForACourse(Course course) {
        List<Student> students = new ArrayList<Student>();
        return students;
    }

    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<Course>();
        return courses;
    }
}
