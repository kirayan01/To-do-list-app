package model;


import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;

import java.util.*;
import java.util.function.Consumer;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order of tasks is preserved
public class Project extends Todo implements Iterable<Todo>, Observer {

    private String description;
    private List<Todo> tasks;

    // MODIFIES: this
    // EFFECTS: constructs a project with the given description
    //     the constructed project shall have no tasks.
    //  throws EmptyStringException if description is null or empty
    public Project(String description) {
        super(description);
//        if (description == null || description.isEmpty()) {
//            throw new EmptyStringException("Cannot construct a project with no description");
//        }
        this.description = description;
        tasks = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: task is added to this project (if it was not already part of it)
    //   throws NullArgumentException when task is null
    public void add(Todo task) {
        if (this.equals(task)) {
            return;
        } else if (!contains(task)) {
            tasks.add(task);
            task.addObserver(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes task from this project
    //   throws NullArgumentException when task is null
    public void remove(Todo task) {
        if (task == null) {
            throw new NullArgumentException("task is null");
        }
        if (contains(task)) {
            tasks.remove(task);
        }
    }

    // EFFECTS: returns the description of this project
    public String getDescription() {
        return description;
    }

    @Override
    public int getEstimatedTimeToComplete() {
//        int n = 0;
//        for (Todo t : tasks) {
//            n = n + t.getEstimatedTimeToComplete();
//        }
//        return n;
        return etcHours;
    }

    // EFFECTS: returns an unmodifiable list of tasks in this project.
    @Deprecated
    public List<Task> getTasks() {
        throw new UnsupportedOperationException();
    }

    // EFFECTS: returns an integer between 0 and 100 which represents
    //     the percentage of completion (rounded down to the nearest integer).
    //     the value returned is the average of the percentage of completion of
    //     all the tasks and sub-projects in this project.

    @Override
    public int getProgress() {
        int p = 0;

        if (getNumberOfTasks() == 0) {
            return 0;
        } else {
            for (Todo t : tasks) {
                p = t.getProgress() + p;
            }
            return p / getNumberOfTasks();
        }
    }

    // EFFECTS: returns the number of completed tasks in this project
//    private int getNumberOfCompletedTasks() {
//        int done = 0;
//        for (Todo t : tasks) {
//            if (t.getStatus() == Status.DONE) {
//                done++;
//            }
//        }
//        return done;
//    }

    // EFFECTS: returns the number of tasks (and sub-projects) in this project
    public int getNumberOfTasks() {
        return tasks.size();
    }

    // EFFECTS: returns true if every task (and sub-project) in this project is completed, and false otherwise
//     If this project has no tasks (or sub-projects), return false.
    public boolean isCompleted() {
        return getNumberOfTasks() != 0 && getProgress() == 100;
    }

    // EFFECTS: returns true if this project contains the task
    //   throws NullArgumentException when task is null
    public boolean contains(Todo task) {
        if (task == null) {
            throw new NullArgumentException("Illegal argument: task is null");
        }
        return tasks.contains(task);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        Project project = (Project) o;
        return Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }


    @Override
    public Iterator<Todo> iterator() {
        return new ToDoIterator();
    }

    @Override
    public void update(Observable o, Object arg) {
        Task task = (Task) o;
        task.getEstimatedTimeToComplete();
    }

    private class ToDoIterator implements Iterator<Todo> {
        private Todo todo;
        private int priority = 1;
        private int index = 0;
        private int size = 0;
        private Iterator<Todo> taskIterator = tasks.iterator();



        @Override
        public boolean hasNext() {
            return (tasks.size() > size);
        }

        @Override
        public Todo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            while (hasNext()) {
                index = 0;
                for (index = 0; tasks.size() > index; index++) {
                    todo = tasks.get(index);
                    if (todo.getPriority().equals(new Priority(priority))) {
                        size++;
                        return todo;
                    }
                }
                priority++;
            }
            return null;
        }

    }
}




