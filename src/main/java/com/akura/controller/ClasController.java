package com.akura.controller;

import com.akura.dao.*;

import com.akura.entity.Clas;
import com.akura.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

@RestController
public class ClasController {

    @Autowired
    private ClasDao dao;

    @Autowired
    private GradeDao daoGrade;

    @Autowired
    private SubjectDao daoSubject;

    @Autowired
    private EmployeeDao daoEmployee;

    @Autowired
    private MediumDao daoMedium;

    @Autowired
    private BranchDao daoBranch;

/*    @RequestMapping(value = "/clases", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Clas clas) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.EMPLOYEE,AuthProvider.INSERT)) {
            Clas clasname = dao.findByName(clas.getName());

            if (clasname != null)
                return "Error-Validation : Clas Exists";
            else
                try {
                    dao.save(clas);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }*/
    @RequestMapping(value = "/clases", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Clas clas) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.EMPLOYEE,AuthProvider.INSERT)) {
             for (Clas clas1 : dao.findAll()) {
                 Clas clasname = dao.findByName(clas.getName());
                 if (clasname != null)
                     return "Error-Validation : Clas Exists";
                if (!checkRoom(clas1, clas))
                    return "Error-Saving : Room isn't available";

                if (!checkTeacher(clas1, clas))
                    return "Error-Saving : Teacher isn't available";
            }

            dao.save(clas);
            return "0";
        }
        return "Error-Saving : You have no Permission";

    }

    private boolean checkTeacher(Clas existingClass, Clas newClass) {
        if (newClass.getDofinish().isAfter(existingClass.getDostart()))
            if (newClass.getDostart().isBefore(existingClass.getDofinish())) {
                if (newClass.getEndtime().isAfter(existingClass.getStarttime()))
                    if (newClass.getStarttime().isBefore(existingClass.getEndtime())) {
                        if (Objects.equals(newClass.getEmployeeTeacherId().getId(), existingClass.getEmployeeTeacherId().getId())) {
                            return false;
                        } else
                            return true;
                    } else
                        return true;
                else
                    return true;
            } else
                return true;
        return true;
    }

    private boolean checkRoom(Clas existingClass, Clas newClass) {
        if (newClass.getDofinish().isAfter(existingClass.getDostart()))
            if (newClass.getDostart().isBefore(existingClass.getDofinish())) {
                if (newClass.getEndtime().isAfter(existingClass.getStarttime()))
                    if (newClass.getStarttime().isBefore(existingClass.getEndtime())) {
                        if (Objects.equals(newClass.getRoomId().getId(), existingClass.getRoomId().getId())) {
                            return false;
                        } else
                            return true;
                    } else
                        return true;
                else
                    return true;
            } else
                return true;
        return true;
    }



           /* for (Clas clas1 : dao.findAll()) {
                if (!checkRoom(clas1, clas))
                    return "Error-Saving : Room isn't available";

                if (!checkTeacher(clas1, clas))
                    return "Error-Saving : Teacher isn't available";
            }

            dao.save(clas);
            return "0";
        }
        return "Error-Saving : You have no Permission";

    }

    private boolean checkTeacher(Clas existingClass, Clas newClass) {
        if (newClass.getDofinish().isAfter(existingClass.getDostart()))
            if (newClass.getDostart().isBefore(existingClass.getDofinish())) {
                if (newClass.getEndtime().isAfter(existingClass.getStarttime()))
                    if (newClass.getStarttime().isBefore(existingClass.getEndtime())) {
                        if (Objects.equals(newClass.getEmployeeTeacherId().getId(), existingClass.getEmployeeTeacherId().getId())) {
                            return false;
                        } else
                            return true;
                    } else
                        return true;
                else
                    return true;
            } else
                return true;
        return true;
    }

    private boolean checkRoom(Clas existingClass, Clas newClass) {
        if (newClass.getDofinish().isAfter(existingClass.getDostart()))
            if (newClass.getDostart().isBefore(existingClass.getDofinish())) {
                if (newClass.getEndtime().isAfter(existingClass.getStarttime()))
                    if (newClass.getStarttime().isBefore(existingClass.getEndtime())) {
                        if (Objects.equals(newClass.getRoomId().getId(), existingClass.getRoomId().getId())) {
                            return false;
                        } else
                            return true;
                    } else
                        return true;
                else
                    return true;
            } else
                return true;
        return true;
    }*/

    @RequestMapping(value = "/clases", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Clas clas) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.EMPLOYEE, AuthProvider.UPDATE)) {
            Clas cla = dao.findByName(clas.getName());
            if (cla == null || cla.getId() == clas.getId()) {
                try {
                    dao.save(clas);
                    return "0";
                } catch (Exception e) {
                    return "Error-Updating : " + e.getMessage();
                }
            } else {
                return "Error-Updating : Name Exists";
            }
        }
        return "Error-Updating : You have no Permission";
    }


    @RequestMapping(value = "/clases", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Clas clas) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.EMPLOYEE, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(clas.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/clases", params = {"page", "size", "gradeid", "subjectid", "employeeteacherid", "mediumid", "branchid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Clas> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size,
                              @RequestParam("gradeid") Integer gradeid, @RequestParam("subjectid") Integer subjectid, @RequestParam("employeeteacherid") Integer employeeteacherid, @RequestParam("mediumid") Integer mediumid, @RequestParam("branchid") Integer branchid) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.CLAS, AuthProvider.SELECT)) {

            List<Clas> clases = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Clas> clasestream = clases.stream();
            //clasestream = clasestream.filter(e -> !(e.getCallingname().equals("Admin")));

            if (gradeid != null)
                clasestream = clasestream.filter(e -> e.getGradeId().equals(daoGrade.getOne(gradeid)));
            if (subjectid != null)
                clasestream = clasestream.filter(e -> e.getSubjectId().equals(daoSubject.getOne(subjectid)));
            if (employeeteacherid != null)
                clasestream = clasestream.filter(e -> e.getEmployeeTeacherId().equals(daoEmployee.getOne(employeeteacherid)));
            if (mediumid != null)
                clasestream = clasestream.filter(e -> e.getMediumId().equals(daoMedium.getOne(mediumid)));
            if (branchid != null)
                clasestream = clasestream.filter(e -> e.getRoomId().getBranchId().getName().equals(daoBranch.getOne(branchid).getName()));


            List<Clas> clases2 = clasestream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < clases2.size() ? start + size : clases2.size();
            Page<Clas> clapage = new PageImpl<>(clases2.subList(start, end), PageRequest.of(page, size), clases2.size());

            return clapage;
        }

        return null;

    }

    @RequestMapping(value = "/clases", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Clas> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CLAS, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/clases/list", method = RequestMethod.GET, produces = "application/json")
    public List<Clas> clases() {
        return dao.list();
    }



 /*   @RequestMapping(value = "/clases/list/bysubject", params = {"subjectname"},method = RequestMethod.GET, produces = "application/json")
    public List<Clas> clas(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @RequestParam("subjectid") Integer subjectid ){
        if (AuthProvider.isUser(username, password))
            return dao.listBySubject(subjectid);

        else return null;
    }
*/


    @GetMapping("clases/getAvailable")
    public List<Clas> getAvailable() {
        return dao.findAll().stream()
                .filter(clas -> clas.getClasstatusId().getId() == 1 || clas.getClasstatusId().getId() == 2)
                .filter(clas -> clas.getRoomId().getCapacity() > clas.getStudentclasList().size())
                .collect(Collectors.toList());
    }

    @GetMapping("clases/getAvailableBySubject")
    public List<Clas> getAvailableBySubject() {
        return dao.findAll().stream()
                .filter(clas -> clas.getClasstatusId().getId() == 1 || clas.getClasstatusId().getId() == 2)
                .filter(clas -> clas.getSubjectId().getId() == 1 || clas.getSubjectId().getId() == 5)
                .collect(Collectors.toList());
    }

    @GetMapping("clases/BySubject")
    public List<Clas> BySubject() {
        return dao.findAll().stream()
                .filter(clas -> clas.getSubjectId().getId() == 1 || clas.getSubjectId().getId() == 5)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/clases/list/listByStudent", params = {"studentid"}, method = RequestMethod.GET, produces = "application/json")
    public List<Clas> listByStudent(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, Integer studentid) {
        if (AuthProvider.isUser(username, password))
            return dao.listByStudent(studentid);
        else
            return null;
    }
}






