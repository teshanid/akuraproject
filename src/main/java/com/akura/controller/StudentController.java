package com.akura.controller;

import com.akura.dao.StudentDao;
import com.akura.entity.Student;
import com.akura.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentDao dao;

/*    @GetMapping
    public Student findAll(@PathVariable String number, @CookieValue String username, @CookieValue String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }*/

  /* @GetMapping
    public Page<Student> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }*/

    @GetMapping("/list")
    public List<Student> list(@CookieValue String username, @CookieValue String password) {
        System.out.println("ok");
        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.SELECT)) {
            return dao.list();
        }
        return null;
    }


    @GetMapping("number/{number}")
    public Student findByNumber(@PathVariable String number, @CookieValue String username, @CookieValue String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.SELECT)) {
            return dao.findByNumber(number);
        }
        return null;
    }

    @PostMapping
    public String add(@CookieValue("username") String username, @CookieValue("password") String password, @Validated @RequestBody Student student) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.INSERT)) {
            dao.save(generateNumber(student));
            return "0";
        }
        return "Error-Saving : You have no Permission";

    }

    @PutMapping
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Student student) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.UPDATE)) {
            Student stu = dao.findByNumber(student.getNumber());
            if (stu == null || stu.getId() == student.getId()) {
                try {
                    student.setNumber(dao.getOne(student.getId()).getNumber());
                    dao.save(student);
                    return "0";
                } catch (Exception e) {
                    return "Error-Updating : " + e.getMessage();
                }
            } else {
                return "Error-Updating : NIC Exists";
            }
        }
        return "Error-Updating : You have no Permission";
    }

    @DeleteMapping
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Student student) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.EMPLOYEE, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(student.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @GetMapping
    public Page<Student> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size,
                                 @RequestParam("number") String number, @RequestParam("name") String name, @RequestParam("guardianname") String guardianname) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.SELECT)) {

            List<Student> studentList = dao.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                    .filter(student -> name == null || student.getFullname().contains(name))
                    .filter(student -> number == null || student.getNumber().startsWith(number))
                    .filter(student -> guardianname == null || student.getGuardianname().contains(guardianname))
                    .collect(Collectors.toList());

            int start = page * size;
            int end = start + size < studentList.size() ? start + size : studentList.size();

            return new PageImpl<>(studentList.subList(start, end), PageRequest.of(page, size), studentList.size());
        }
        return null;
    }

    private Student generateNumber(Student student) {
        Integer now = LocalDate.now().getYear();
        String year = now.toString().substring(2);
        if (dao.findTopByNumberStartsWithOrderByNumberDesc(year).isPresent()) {
            Student student1 = dao.findTopByNumberStartsWithOrderByNumberDesc(year).get();
            String newNumber = String.valueOf(Integer.parseInt(student1.getNumber()) + 1);
            student.setNumber(newNumber);
        } else {
            student.setNumber(year + "0001");
        }
        return student;
    }


    @GetMapping("listByClas")
    public List<Student> listByClas(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, Integer clasid) {
        if (AuthProvider.isUser(username, password))
            return dao.listByClas(clasid);
        else
            return null;
    }

  /*  @GetMapping("fullname/{fullname}")
    public Student listByFullname(@PathVariable String fullname, @CookieValue String username, @CookieValue String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.SELECT)) {
            return dao.listByFullname(fullname);
        }
        return null;
    }*/
    @RequestMapping(value = "/fullname/{fullname}", params = {"fullname"}, method = RequestMethod.GET, produces = "application/json")
    public List<Student> listByFullname(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password,  @RequestParam("fullname") String fullname) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CLAS, AuthProvider.SELECT)) {
            return dao.listByFullname(fullname);
        }
        return null;
    }

    /*@GetMapping("fullname/{fullname}")
    public Student findByFullnfame(@PathVariable String fullname, @CookieValue String username, @CookieValue String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENT, AuthProvider.SELECT)) {
            return dao.findByFullname(fullname);
        }
        return null;
    }*/
/*
    @GetMapping("/{fullname}")
    public List<Student> getByFullname() {
        return dao.findAll().stream()
                .filter(student -> student.getFullname().contains(name));
                .collect(Collectors.toList());
    }*/




}
