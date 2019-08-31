package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Represents a tag having a name
public class Tag {
    private String name;
    private Set<Task> tasks;

    // MODIFIES: this
    // EFFECTS: creates a Tag with the given name
    //    throws EmptyStringException if name is null or empty
    public Tag(String name) {
        if (name == null || name.length() == 0) {
            throw new EmptyStringException("Cannot construct a tag with no name");
        }
        this.name = name;
        tasks = new HashSet<>();
    }
    
    // EFFECTS: returns the name of this tag
    public String getName() {
        return name;
    }
    
    // MODIFIES: this
    // EFFECTS: adds task to the collection of tasks in this tag if it is not already exist
    //  throws NullArgumentException if task is null
    public void addTask(Task task) {
        if (!containsTask(task)) {
            tasks.add(task);
            task.addTag(this);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: removes task from the collection of tasks in this tag
    //  throws NullArgumentException if task is null
    public void removeTask(Task task) {
        if (containsTask(task)) {
            tasks.remove(task);
            task.removeTag(this);
        }
    }
    
    // EFFECTS: returns true if this tag is assigned to the given task
    //     returns false otherwise
    //  throws NullArgumentException if task is null
    public boolean containsTask(Task task) {
        if (task == null) {
            throw new NullArgumentException("Invalid argument: task cannot be null");
        }
        return tasks.contains(task);
    }
    
    // EFFECTS: returns an unmodifiable set of tasks that have this tag
    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }
    
    
    // EFFECTS: returns the tag name preceded by #
    @Override
    public String toString() {
        return "#" + name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
