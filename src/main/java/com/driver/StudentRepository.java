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
    Map<String, List<Student>> teacherStudentsMap  = new HashMap<>(); // "teacher name" : <stu1.name,stu2.name>
            
    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }


    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {

        Teacher teacher1 = teacherMap.get(teacher);
        teacher1.setNumberOfStudents(teacher1.getNumberOfStudents() + 1);
        teacherMap.put(teacher,teacher1);

        Student student1  = studentMap.get(student);


        List<Student> studentList = teacherStudentsMap.get(teacher);
        studentList.add(student1);
        teacherStudentsMap.put(teacher,studentList);
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
        List<String> ans = new ArrayList<>();
        List<Student> studentList = teacherStudentsMap.getOrDefault(teacher,new ArrayList<>());
        for (Student student : studentList) {
            ans.add(student.getName());
        }
        return ans;
    }
}
