package com.akura.controller;

import com.akura.entity.*;
import com.akura.util.RegexPattern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

@RestController
@RequestMapping("/regexes")
public class RegexController {

    @RequestMapping(value = "/employee", produces = "application/json")
    public HashMap<String, HashMap<String, String>> employee() {
        return getRegex(new Employee());
    }

    @RequestMapping(value = "/user", produces = "application/json")
    public HashMap<String, HashMap<String, String>> user() {
        return getRegex(new User());
    }

    @RequestMapping(value = "/designation", produces = "application/json")
    public HashMap<String, HashMap<String, String>> designation() {
        return getRegex(new Designation());
    }

    @RequestMapping(value = "/clas", produces = "application/json")
    public HashMap<String, HashMap<String, String>> clas() {
        return getRegex(new Clas());
    }

    @RequestMapping(value = "/grade", produces = "application/json")
    public HashMap<String, HashMap<String, String>> grade() {
        return getRegex(new Grade());
    }

    @RequestMapping(value = "/student", produces = "application/json")
    public HashMap<String, HashMap<String, String>> Student() {
        return getRegex(new Student());
    }

    @RequestMapping(value = "/subject", produces = "application/json")
    public HashMap<String, HashMap<String, String>> subject() {
        return getRegex(new Subject());
    }

    @RequestMapping(value = "/studentstatus", produces = "application/json")
    public HashMap<String, HashMap<String, String>> Studentstatus() {
        return getRegex(new Studentstatus());
    }

    @RequestMapping(value = "/guardiantype", produces = "application/json")
    public HashMap<String, HashMap<String, String>> Guardiantype() {
        return getRegex(new Guardiantype());
    }


    @RequestMapping(value = "/placementtest", produces = "application/json")
    public HashMap<String, HashMap<String, String>> Placementtest() {
        return getRegex(new Placementtest());
    }

    @RequestMapping(value = "/result", produces = "application/json")
    public HashMap<String, HashMap<String, String>> Result() {
        return getRegex(new Result());
    }


    public static <T> HashMap<String, HashMap<String, String>> getRegex(T t) {
        try {
            Class<? extends Object> aClass = t.getClass();
            HashMap<String, HashMap<String, String>> regex = new HashMap<>();

            for (Field field : aClass.getDeclaredFields()) {

                Annotation[] annotations = field.getDeclaredAnnotations();

                for (Annotation annotation : annotations) {

                    if (annotation instanceof Pattern) {
                        Pattern myAnnotation = (Pattern) annotation;
                        HashMap<String, String> map = new HashMap<>();
                        map.put("regex", myAnnotation.regexp());
                        map.put("message", myAnnotation.message());
                        regex.put(field.getName(), map);
                    }

                    if (annotation instanceof RegexPattern) {
                        RegexPattern myAnnotation2 = (RegexPattern) annotation;
                        HashMap<String, String> map2 = new HashMap<>();
                        map2.put("regex", myAnnotation2.regexp());
                        map2.put("message", myAnnotation2.message());
                        regex.put(field.getName(), map2);
                    }
                }
            }
            return regex;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
