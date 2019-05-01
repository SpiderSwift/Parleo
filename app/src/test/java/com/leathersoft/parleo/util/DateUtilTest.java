package com.leathersoft.parleo.util;

import org.junit.Assert;
import org.junit.Test;

import java.time.Month;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void calculateAge_Success() {

        // setup
        Date birthDate = new GregorianCalendar(1999, 4, 21).getTime();
        // exercise
        Date actualDate = new GregorianCalendar(2019, 5, 1).getTime();

        int actual = DateUtil.calculateAge(birthDate, actualDate);
        // assert
        Assert.assertEquals(20, actual);
    }

    @Test
    public void calculateAge_Null() {

        // setup
        Date birthDate = null;
        // exercise
        Date actualDate = new GregorianCalendar(2019, 5, 1).getTime();

        int actual = DateUtil.calculateAge(birthDate, actualDate);
        // assert
        Assert.assertEquals(0, actual);
    }
}