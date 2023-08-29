package com.crio.jukebox.repositories.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;

public class UserRepository implements IUserRepository{

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<>();
    }

    @Override
    public User save(User entity) {
        if(userMap.get(entity.getId())==null){
            ++autoIncrement;
            entity = new User(autoIncrement.toString(),entity.getName());
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    @Override
    public void delete(User entity) {
        userMap.remove(entity.getId());
    }

    @Override
    public long count() {
        return userMap.size();
    }    
}
