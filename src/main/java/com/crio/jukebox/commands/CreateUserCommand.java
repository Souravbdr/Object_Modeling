package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand{
    private IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        User user = userService.create(tokens.get(1));
        System.out.println(user);
    }

    
}
