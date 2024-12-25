package main.services;

import main.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    private Map<UUID, User> uuidUserMap;

    public UserService() {
        this.uuidUserMap = new HashMap<>();
    }

    public Map<UUID, User> getUuidUserMap() {
        return uuidUserMap;
    }

    public void setUuidUserMap(Map<UUID, User> uuidUserMap) {
        this.uuidUserMap = uuidUserMap;
    }

    public void addUsersToHashMap(User user) {
        this.uuidUserMap.put(user.getUserId(), user);
    }
}
