package domain;

import java.io.Serializable;

public class ToDoItem implements Serializable {
    private String name;
    private String description;
    private boolean urgent;

    public ToDoItem(String name, String description, boolean urgent) {
        setName(name);
        setDescription(description);
        setUrgent(urgent);
    }

    public ToDoItem() {}

    public String toString() {
        return "name: " + getName() + ", description: " + getDescription() + ", urgent: " + isUrgent();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }
}
