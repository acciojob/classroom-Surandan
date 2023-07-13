package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    
    Map<String,Student> studentMap = new HashMap<>();  // "name" : Student
    Map<String,Teacher> teacherMap = new HashMap<>();  // "name" : Teacher
    Map<String, List<String>> teacherStudentsMap  = new HashMap<>(); // "teacher name" : <stu1.name,stu2.name>
            
    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }


    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> currStudents = teacherStudentsMap.getOrDefault(teacher,new ArrayList<>());
        currStudents.add(student);
        teacherMap.get(teacher).setNumberOfStudents(currStudents.size());
        teacherStudentsMap.put(teacher,currStudents);
    }

    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        teacherMap.remove(teacher);
        teacherStudentsMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        teacherMap.clear();
        teacherStudentsMap.clear();
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherStudentsMap.getOrDefault(teacher,new ArrayList<>());
    }
}
