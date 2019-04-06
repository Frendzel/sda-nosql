package com.sda;

import org.junit.Test;

import java.lang.reflect.Field;

public class GradeTest {

    @Test
    public void test() throws IllegalAccessException {
        Grade grade = new Grade();
        for (Field declaredField : grade.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            System.out.println(declaredField.get(grade));
        }
    }

}