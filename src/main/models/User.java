package main.models;

import java.util.UUID;

public class User {
    private UUID userId;
    private String name;
    private Location location;

    public User(String name, Location location) {
        this.userId = UUID.randomUUID();
        this.name = name;
        this.location = location;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
