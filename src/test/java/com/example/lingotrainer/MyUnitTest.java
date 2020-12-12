package com.example.lingotrainer;

import com.example.lingotrainer.round.RoundServiceInterface;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyUnitTest {

    RoundServiceInterface rs;


    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        System.out.println("Is typen mogelijk");
        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }
}