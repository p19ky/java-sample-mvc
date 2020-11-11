package lab3.repository;

import lab3.DeleteSpecificFileLines;
import lab3.ModelReader;
import lab3.ModelWriter;
import lab3.model.Course;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class CourseRepository implements ICrudRepository<Course> {
    private String fileName; //= "courses.txt";
    private List<Course> courses;

    public CourseRepository(String fileName) {
        this.fileName = fileName;
    }

    public void fillCourseRepositoryWithCoursesFromFile() {
        List<String> listOfLines = new ModelReader().getLinesFromFile(this.fileName);
        for (String line : listOfLines) {
            String[] words = line.split(", ");
            String name = words[0];
            Long courseId = Long.parseLong(words[1]);

            //TODO
        }
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return The course with the given id or null if none was found.
     */
    @Override
    public Course findOne(Long id) {
        for (Course course : this.courses) {
            if (course.getCourseId() == id) {
                //Course with this the given id was found.
                return course;
            }
        }

        //Course with given id does not exist.
        return null;
    }

    /**
     * @return A list of all the courses.
     */
    @Override
    public List<Course> findAll() {
        return this.courses;
    }

    /**
     *
     * @param course
     * @return course if it already exists or null if given course was saved successfully to database.
     */
    @Override
    public Course save(Course course) {
        List<String> listOfLines = new ModelReader().getLinesFromFile(this.fileName);

        for (String line : listOfLines) {
            String[] words = line.split(", ");
            for (Course c : this.courses) {
                if (c.getCourseId() == Long.parseLong(words[1])) {
                    //Course given already exists.
                    return c;
                }
            }
        }

        String newLine = course.customToString();
        ModelWriter mw = new ModelWriter();
        mw.writeToFile(this.fileName, newLine);
        courses.add(course);

        //Course given successfully saved to database.
        return null;
    }

    /**
     *
     * @param id id must be not null
     * @return Course that was deleted or null if there is no Course with given id.
     */
    @Override
    public Course delete(Long id) {
        Course courseToReturn = null;

        for (int i = 0; i < this.courses.size(); i++) {
            if (this.courses.get(i).getCourseId() == id) {
                courseToReturn = this.courses.get(i);
                this.courses.remove(i);
            }
        }

        if (courseToReturn != null)
        {
            DeleteSpecificFileLines df = new DeleteSpecificFileLines();
            df.deleteLines(this.fileName, String.valueOf(courseToReturn.getCourseId()));
            return courseToReturn;
        }

        return null;
    }

    @Override
    public Course update(Course entity) {
        return null;
    }
}
