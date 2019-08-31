package model;

import model.exceptions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestProject {

    Project testProject;
    Task testTask1 = new Task("TODO");
    Task testTask2 = new Task("DONE");
    Task testTask3 = new Task("inProgress");
    Task testTask4 = new Task("upNEXT");
    Project p1 = new Project("p1");
    Project p2 = new Project("p2");

    private List<Todo> tasks;

    @Test
    void testConstructor() {
        testProject = new Project("to test");
        assertEquals("to test", testProject.getDescription());
    }

    @Test
    void testEmptyStringInTask() {
        try {
            testProject = new Project("");
            fail("Failed to throw exception");
        } catch (EmptyStringException e) {
            System.out.println("Throw successfully");
        }
    }

    @Test
    void testEmptyStringInTask1() {
        try {
            testProject = new Project(null);
            fail("Failed to throw exception");
        } catch (EmptyStringException e) {
            //expected
        }
    }

    @Test
    void testAddTaskIsNull() {
        try {
            testProject = new Project("");
            testProject.getDescription();
            testProject.add(null);
            fail("Failed to throw exception");
        } catch (EmptyStringException e) {
            System.out.println("Throw successfully");
        } catch (NullArgumentException e) {
            System.out.println("Throw successfully");
        }
    }

    @Test
    void testRemoveTaskIsNull() {
        try {
            testProject = new Project("");
            testProject.getDescription();
            fail("Failed to throw exception");
            testProject.remove(null);
            fail("Failed to throw exception");
        } catch (EmptyStringException e) {
            System.out.println("Throw successfully");
        } catch (NullArgumentException e) {
            System.out.println("Throw successfully");
        }
    }


    @Test
    void testAddTask() {
        testProject = new Project("to test");
        testProject.add(testTask1);
        assertEquals(1, testProject.getNumberOfTasks());
        testProject.add(testTask1);
        assertTrue(testProject.getNumberOfTasks() == 1);
        testProject.add(testTask3);
        assertTrue(testProject.getNumberOfTasks() == 2);

    }

    @Test
    void testContainTask() {
        testProject = new Project("todo");
        testProject.add(testTask1);
        assertTrue(testProject.contains(testTask1));
        assertFalse(testProject.contains(testTask3));
    }

    @Test
    void testContainNullTask() {
        try {
            testProject = new Project("todo");
            testProject.contains(null);
            fail("Failed to throw null argument exception");
        } catch (NullArgumentException e) {
            System.out.println("Throw successfully");
        }
    }

    @Test
    void testRemoveTask() {
        testProject = new Project("todo");
        testProject.add(testTask1);
        assertEquals(1, testProject.getNumberOfTasks());
        testProject.remove(testTask1);
        assertEquals(0, testProject.getNumberOfTasks());
        assertFalse(testProject.contains(testTask1));
    }

    @Test
    void testGetTaskProcess() {
        testTask1.setStatus(Status.TODO);
        testTask2.setStatus(Status.DONE);
        testTask3.setStatus(Status.IN_PROGRESS);
        testTask4.setStatus(Status.UP_NEXT);

        testProject = new Project("todo");
        testProject.add(testTask2);
        assertEquals(0, testProject.getProgress());
        testProject.add(testTask1);
        assertEquals(0, testProject.getProgress());
        testProject.add(testTask3);
        testProject.add(testTask4);
        assertEquals(0, testProject.getProgress());
    }

    @Test
    void testTaskIsCompleted() {
        testTask1.setStatus(Status.TODO);
        testTask2.setStatus(Status.DONE);
        testProject = new Project("todo");
        assertFalse(testProject.isCompleted());
        testProject.add(testTask2);
        assertFalse(testProject.isCompleted());
        testProject.add(testTask1);
        assertFalse(testProject.isCompleted());
    }

    @Test
    void testGetEstimatedTimeToComplete() {
        Project testProject1 = new Project("todo ## read ## eat");
        Project testProject2 = new Project("math ## english");
        assertEquals(0,testProject1.getNumberOfTasks());
        assertEquals(0,testProject2.getNumberOfTasks());
        testTask1.setEstimatedTimeToComplete(8);
        testTask2.setEstimatedTimeToComplete(0);
        testTask3.setEstimatedTimeToComplete(12);
        testTask4.setEstimatedTimeToComplete(6);
        testProject1.add(testTask4);
        testProject1.add(testTask3);
        testProject2.add(testTask1);
        testProject2.add(testTask2);
        testProject1.add(testProject2);
        assertEquals(8, testProject2.getEstimatedTimeToComplete());
        assertEquals(26, testProject1.getEstimatedTimeToComplete());
    }

    @Test
    void testSetInvalidProgress1() {

        try {
            Project testProject1 = new Project("todo ## read ## eat");
            testProject1.add(testTask1);
            testTask1.setProgress(-50);
            testProject1.getProgress();
            fail("throw");
        } catch (InvalidProgressException e) {
            //expected
        }
    }

    @Test
    void testSetInvalidProgress2() {
        Project testProject1 = new Project("todo ## read ## eat");
        testProject1.add(testTask1);
        testTask1.setProgress(50);
        testProject1.getProgress();
    }

    @Test
    void testSetInvalidProgress3() {
        Project testProject1 = new Project("todo ## read ## eat");
        testProject1.add(testTask1);
        testTask1.setProgress(0);
        assertEquals(0,testProject1.getProgress());
    }

    @Test
    void testSetInvalidProgress4() {
        Project testProject1 = new Project("todo ## read ## eat");
        testProject1.add(testTask1);
        testTask1.setProgress(100);
        assertEquals(100, testProject1.getProgress());
    }

    @Test
    void testSetInvalidProgress5() {

        try {
            Project testProject1 = new Project("todo ## read ## eat");
            testProject1.add(testTask1);
            testTask1.setProgress(1000);
            testProject1.getProgress();
            fail("throw");
        } catch (InvalidProgressException e) {
            //expected
        }
    }

    @Test
    void testGetInvalidProgress() {

        try {
            Project testProject1 = new Project("todo ## read ## eat");
            testTask1.setProgress(-50);
            testTask2.setProgress(150);
            testProject1.add(testTask2);
            testProject1.add(testTask1);
            testProject1.getProgress();
            fail("throw");
        } catch (InvalidProgressException e) {
            //expected
        }
    }

    @Test
    void testGetInvalidTime1() {

        try {
            Project testProject1 = new Project("todo ## read ## eat");
            testProject1.add(testTask1);
            testTask1.setEstimatedTimeToComplete(-50);
            fail("throw");
        } catch (NegativeInputException e) {
            //expected
        }
    }

    @Test
    void testGetInvalidTime2() {
        Project testProject1 = new Project("todo ## read ## eat");
        testProject1.add(testTask1);
        testTask1.setEstimatedTimeToComplete(0);
        assertEquals(0,testProject1.getEstimatedTimeToComplete());
    }

    @Test
    void testGetProgress() {
        Project testProject1 = new Project("todo ## read ## eat");
        Project testProject2 = new Project("math ## english");
        assertEquals(0,testProject1.getNumberOfTasks());
        assertEquals(0,testProject2.getNumberOfTasks());
        assertEquals(0,testProject1.getProgress());
        assertEquals(0,testProject2.getProgress());
        testProject1.add(testTask1);
        testProject1.add(testTask2);
        testProject1.add(testTask3);
        assertEquals(0,testProject1.getProgress());
        testTask1.setProgress(100);
        assertEquals(33,testProject1.getProgress());
        testTask2.setProgress(50);
        testTask3.setProgress(25);
        assertEquals(58,testProject1.getProgress());
        testProject2.add(testTask4);
        testProject2.add(testProject1);
        assertEquals(29,testProject2.getProgress());
    }

    @Test
    void testGetTime() {
        Project testProject1 = new Project("todo ## read ## eat");
        Project testProject2 = new Project("math ## english");
        assertEquals(0,testProject1.getNumberOfTasks());
        assertEquals(0,testProject2.getNumberOfTasks());
        testProject1.add(testTask1);
        testProject1.add(testTask2);
        testProject1.add(testTask3);
        assertEquals(0, testProject2.getEstimatedTimeToComplete());
        testTask1.setEstimatedTimeToComplete(8);
        testTask4.setEstimatedTimeToComplete(4);
        assertEquals(8,testProject1.getEstimatedTimeToComplete());
        testTask2.setEstimatedTimeToComplete(2);
        testTask3.setEstimatedTimeToComplete(10);
        assertEquals(20,testProject1.getEstimatedTimeToComplete());
        testProject2.add(testTask4);
        assertEquals(4, testProject2.getEstimatedTimeToComplete());
        testProject2.add(testProject1);
        assertEquals(24, testProject2.getEstimatedTimeToComplete());
    }

    @Test
    void testGetTasks() {
        try {
            Project testProject1 = new Project("todo ## read ## eat");
            testProject1.getTasks();
            fail("throw");
        } catch (UnsupportedOperationException e) {
            //expected
        }
    }

    @Test
    void testEquals() {

        Project p1 = new Project("p1");
        Project p2 = new Project("p1");
        Task t1 = new Task("t1");
        assertTrue(p1.equals(p1));

        assertTrue(!p1.equals(t1));
        assertTrue(p1.equals(p2));

        assertEquals(p1.hashCode(),p2.hashCode());

    }

    @Test
    void testAddProjectToProject() {
        Project p1 = new Project("p1");
        Project p2 = new Project("p2");
        assertEquals(0,p1.getNumberOfTasks());
        p1.add(testTask4);
        p1.add(testTask3);
        p2.add(testTask4);
        p2.add(testTask3);
        p2.add(p1);
        assertEquals(2,p1.getNumberOfTasks());
        p1.add(p1);
        assertEquals(2,p1.getNumberOfTasks());
        assertEquals(3,p2.getNumberOfTasks());

    }

    @Test
    void testIterator1() {
        testTask1.setPriority(new Priority(1));
        p1.add(testTask1);
        List<Todo> todo = new ArrayList<>();

        for (Todo t : p1) {
            todo.add(t);
        }

        assertTrue(todo.iterator().hasNext());
        assertEquals(testTask1 ,todo.iterator().next());

    }


    @Test
    void testIterator2() {
        testTask2.setPriority(new Priority(2));
        p1.add(testTask2);
        List<Todo> todo = new ArrayList<>();

        for (Todo t : p1) {
            todo.add(t);
        }

        assertTrue(todo.iterator().hasNext());
        assertEquals(testTask2 ,todo.iterator().next());

    }

    @Test
    void testIterator3() {
        testTask3.setPriority(new Priority(3));
        p1.add(testTask3);
        List<Todo> todo = new ArrayList<>();

        for (Todo t : p1) {
            todo.add(t);
        }

        assertTrue(todo.iterator().hasNext());
        assertEquals(testTask3 ,todo.iterator().next());

    }

    @Test
    void testIterator4() {
        testTask4.setPriority(new Priority(4));
        p1.add(testTask4);
        List<Todo> todo = new ArrayList<>();

        for (Todo t : p1) {
            todo.add(t);
        }

        assertTrue(todo.iterator().hasNext());
        assertEquals(testTask4,todo.iterator().next());

    }

    @Test
    void testIterator5() {
        testTask1.setPriority(new Priority(4));
        testTask2.setPriority(new Priority(3));
        testTask2.setPriority(new Priority(2));
        testTask2.setPriority(new Priority(1));
        p1.add(testTask1);
        p1.add(testTask2);
        p1.add(testTask3);
        p1.add(testTask4);
        p1.add(p2);
        List<Todo> todo = new ArrayList<>();

        for (Todo t : p2) {
            todo.add(t);
        }

        assertFalse(todo.iterator().hasNext());

    }

    @Test
    void testIteratorNoSuchElement() {
        try {
            Project p = new Project("1");
            List<Todo> todo = new ArrayList<>();

            for (Todo t : p1) {
                todo.add(t);
            }

            Iterator<Todo> todoIterator = todo.iterator();
            todoIterator.next();
            fail("throw");
        } catch (NoSuchElementException e) {
            //Expected
        }
    }

}
