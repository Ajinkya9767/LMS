package com.example.lms.service.impl;

import com.example.lms.entity.Course;
import com.example.lms.entity.Role;
import com.example.lms.entity.User;
import com.example.lms.model.GenericResponse;
import com.example.lms.model.UserRole;
import com.example.lms.repository.CourseRepository;
import com.example.lms.repository.RoleRepository;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.lms.utils.CommonConstants.CREATE;
import static com.example.lms.utils.CommonConstants.FAILED;
import static com.example.lms.utils.CommonConstants.SUCCESS;
import static com.example.lms.utils.CommonConstants.UPDATE;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    public GenericResponse<Course> createCourse(Course course) {
        logger.info("Creating a new course with title: {}", course.getTitle());
        return createOrUpdateCourse(course, CREATE);
    }

    private GenericResponse<Course> createOrUpdateCourse(Course course, String type) {
        GenericResponse<Course> genericResponse = new GenericResponse<>();

        if (type.equalsIgnoreCase(CREATE)) {
            course.setCreatedAt(LocalDateTime.now());
        } else {
            course.setUpdatedAt(LocalDateTime.now());
        }

        // Fetching user details (instructor)
        Optional<User> userDetails = userRepository.findById(course.getInstructor().getId());
        if (userDetails.isPresent()) {
            User user = userDetails.get();
            Role role = user.getRole();

            if (UserRole.INSTRUCTOR.getName().equalsIgnoreCase(role.getName()) || UserRole.TEACHER.getName().equalsIgnoreCase(role.getName())) {
                // If the user has a valid role (Instructor or Teacher), save the course
                Course courseResponse = courseRepository.save(course);
                genericResponse.setDescription("Course successfully " + type + "d with ID: " + courseResponse.getId());
                genericResponse.setStatus(SUCCESS);
                genericResponse.setStatusCode(HttpStatus.OK.value());
                genericResponse.setData(courseResponse);

                logger.info("Course {}d successfully with ID: {}", type, courseResponse.getId());
            } else {
                // If the user role is not valid (not an Instructor or Teacher)
                genericResponse.setDescription("Error: Course can only be assigned to a Teacher or Instructor!");
                genericResponse.setStatus(FAILED);
                genericResponse.setStatusCode(HttpStatus.FORBIDDEN.value());

                logger.warn("User with ID: {} does not have a valid role to create a course. Role: {}", user.getId(), role.getName());
            }
        } else {
            // If the user (instructor) doesn't exist
            genericResponse.setDescription("Error: User not found with ID: " + course.getInstructor().getId());
            genericResponse.setStatus(FAILED);
            genericResponse.setStatusCode(HttpStatus.NOT_FOUND.value());

            logger.error("User with ID: {} not found while trying to create the course.", course.getInstructor().getId());
        }
        return genericResponse;
    }

    public GenericResponse<Course> updateCourse(Long id, Course updatedCourse) {
        logger.info("Attempting to update course with ID: {}", id);

        GenericResponse<Course> genericResponse = new GenericResponse<>();
        if (courseRepository.existsById(id)) {
            updatedCourse.setId(id);
            // Proceed with update
            genericResponse = createOrUpdateCourse(updatedCourse, UPDATE);
        } else {
            // If the course doesn't exist
            genericResponse.setDescription("Error: Course with ID: " + id + " does not exist.");
            genericResponse.setStatus(FAILED);
            genericResponse.setStatusCode(HttpStatus.NOT_FOUND.value());

            logger.error("Course with ID: {} not found while attempting to update.", id);
        }
        return genericResponse;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
