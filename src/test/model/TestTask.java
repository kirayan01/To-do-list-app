package model;

import model.exceptions.EmptyStringException;
import model.exceptions.InvalidProgressException;
import model.exceptions.NegativeInputException;
import model.exceptions.NullArgumentException;
import org.junit.jupiter.api.Test;
import parsers.Parser;
import parsers.TagParser;
import parsers.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {
    Task testTask = new Task("My homework");

    @Test
    void testEmptyDescription() {
        try {
            Task testTask1 = new Task("");
            //testTask1.getDescription();
            //fail("Failed to throw empty exception");
        } catch (EmptyStringException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testEmptyDescription2() {
        try {
            String nullString = null;
            Task testTask1 = new Task(nullString);
            testTask1.getDescription();
            fail("Failed to throw null exception");
        } catch (EmptyStringException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testContain() {
        Task testTask1 = new Task("My math homework");
        assertFalse(testTask1.containsTag("math"));
        testTask1.addTag("math");
        assertTrue(testTask1.containsTag("math"));
        assertFalse(testTask1.containsTag("homework"));
    }

    @Test
    void testPriority() {
        Task testTask = new Task("My math homework");
        Priority testPriority = new Priority(4);
        testTask.setPriority(testPriority);
        assertEquals(testPriority, testTask.getPriority());

    }

    @Test
    void testDescription() {
        Task testTask = new Task("My math homework");
        testTask.setDescription("My stat homework");
        assertNotEquals("My math homework", testTask.getDescription());

    }


    @Test
    void testAddTagNameIsEmpty() {
        try {
            Task testTask1 = new Task("Math");
            testTask1.addTag("");
            fail("Failed to throw Empty string exception");
        } catch (EmptyStringException e) {
            System.out.println("Successfully throw");
        }

    }

    @Test
    void testAddTagNameIsEmpty2() {
        try {
            Task testTask1 = new Task("");
            fail("Failed to throw Empty string exception");
            testTask1.addTag("");
            fail("Failed to throw Empty string exception");
        } catch (EmptyStringException e) {
            System.out.println("Successfully throw");
        }

    }

    @Test
    void testAddTagNameIsEmpty3() {
        try {
            Task testTask1 = new Task("1");
            Tag nullTag = null;
            testTask1.addTag(nullTag);
            fail("Failed to throw Null string exception");
        } catch (NullArgumentException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testContainTagNameIsEmpty3() {
        try {
            Task testTask1 = new Task("1");
            Tag nullTag = null;
            testTask1.containsTag("");
        } catch (EmptyStringException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testAddTag() {
        testTask.addTag("to do");
        assertEquals(1, testTask.getTags().size());
        assertTrue(testTask.containsTag("to do"));
        testTask.addTag("to do");
        assertEquals(1, testTask.getTags().size());
        testTask.addTag("math");
        assertEquals(2, testTask.getTags().size());

    }

    @Test
    void testRemoveTagNameIsEmpty() {
        try {
            Task testTask1 = new Task("1");
            testTask1.removeTag("");
            fail("Failed to throw Null string exception");
        } catch (EmptyStringException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testRemoveTagNameIsEmpty1() {
        try {
            Task testTask1 = new Task("1");
            Tag nullTag = null;
            testTask1.removeTag(nullTag);
            fail("Failed to throw empty string exception");
        } catch (NullArgumentException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testReMoveTag() {
        testTask.addTag("math");
        testTask.addTag("english");
        testTask.removeTag("english");
        testTask.containsTag("math");
    }

    @Test
    void testNullPriority() {
        try {
            Task testTask1 = new Task("1");
            testTask1.setPriority(null);
            fail("Failed to throw a null pointer exception");
        } catch (NullArgumentException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testNullStatus() {
        try {
            Task testTask1 = new Task("1");
            testTask1.setStatus(null);
            fail("Failed to throw a null pointer exception");
        } catch (NullArgumentException e) {
            System.out.println("Successfully throw");
        }
    }

    @Test
    void testNullDescription() {
        try {
            Task testTask1 = new Task(null);
            testTask1.getDescription();
            fail("Failed to throw a null pointer exception");
        } catch (EmptyStringException e) {
            System.out.println("Successfully throw");
        }
    }


    @Test
    void testToString() {
        Priority testPriority = new Priority(4);
        Status testStatus = Status.TODO;
        Task testTask = new Task("Read collaboration policy of the term project");
        Calendar current = GregorianCalendar.getInstance();
        current.set(Calendar.HOUR_OF_DAY, 11);
        current.set(Calendar.MINUTE, 59);
        current.set(Calendar.SECOND, 59);
        current.set(Calendar.MONTH, 1);
        current.set(Calendar.DAY_OF_MONTH, 2);
        DueDate testDueDate = new DueDate(current.getTime());
        testTask.setDueDate(testDueDate);
        testTask.setPriority(testPriority);
        testTask.setStatus(testStatus);
        testTask.addTag("cpsc210");
        testTask.addTag("project");
        String testString = "\n{\n" +
                "\tDescription: Read collaboration policy of the term project\n" +
                "\tDue date: Sat Feb 02 2019 11:59 AM\n" +
                "\tStatus: TODO\n" +
                "\tPriority: DEFAULT\n" +
                "\tTags: #project, #cpsc210\n" +
                "}";

        assertEquals(testString, testTask.toString());
        String testString1 = "\n{\n" +
                "\tDescription: math\n" +
                "\tDue date: \n" +
                "\tStatus: TODO\n" +
                "\tPriority: DEFAULT\n" +
                "\tTags:  \n" +
                "}";
        Task testTask1 = new Task("math");
        assertEquals(testString1,testTask1.toString());

    }

    @Test
    void testSetInvalidProgress1() {
        try {
            Task task1 = new Task("1");
            task1.setProgress(-1000);
        } catch (InvalidProgressException e) {
            //expected
        }
    }

    @Test
    void testSetInvalidProgress2() {
        try {
            Task task1 = new Task("1");
            task1.setProgress(1000);
        } catch (InvalidProgressException e) {
            //expected
        }
    }

    @Test
    void testSetInvalidProgress3() {
        Task task1 = new Task("1");
        task1.setProgress(0);
        assertEquals(0,task1.getProgress());
    }

    @Test
    void testSetInvalidProgress4() {
        Task task1 = new Task("1");
        task1.setProgress(100);
        assertEquals(100, task1.getProgress());
    }

    @Test
    void testSetProgress() {
        Task task3 = new Task("3");
        Task task4 = new Task("4");
        task3.setProgress(20);
        task4.setProgress(50);
        assertEquals(20,task3.getProgress());
        assertEquals(50,task4.getProgress());
    }


    @Test
    void testInvalidEstimatedTimeToComplete1() {
        try {
            Task task1 = new Task("1");
            task1.setEstimatedTimeToComplete(-90);
        } catch (NegativeInputException e) {
            //expected
        }
    }

    @Test
    void testInvalidEstimatedTimeToComplete2() {
            Task task1 = new Task("1");
            task1.setEstimatedTimeToComplete(0);
            assertEquals(0,task1.getEstimatedTimeToComplete());
    }

    @Test
    void testSetEstimatedTimeToComplete() {
        Task task3 = new Task("3");
        Task task4 = new Task("4");
        task3.setEstimatedTimeToComplete(20);
        task4.setEstimatedTimeToComplete(50);
        assertEquals(20,task3.getEstimatedTimeToComplete());
        assertEquals(50,task4.getEstimatedTimeToComplete());
    }

    @Test
    void testEquals() {

        Task p1 = new Task("p1");
        Task p2 = new Task("p1");
        Project t1 = new Project("t1");
        assertTrue(p1.equals(p1));

        assertTrue(!p1.equals(t1));
        assertTrue(p1.equals(p2));

        assertEquals(p1.hashCode(),p2.hashCode());

    }

//    @Test
//    void testParseDescription() {
//        Task testTaks1 = new Task("to do## to");
//
//    }


}

