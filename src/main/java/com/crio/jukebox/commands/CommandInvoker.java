package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.NoSuchCommandException;

public class CommandInvoker {
    private static final Map<String,ICommand> commandMap = new HashMap<>();

    public void registerCommand(String commandName, ICommand command){
        commandMap.put(commandName,command);
    }

    private ICommand getCommand(String commandName){
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException{
        ICommand command = getCommand(commandName);
        if(command==null){
            throw new NoSuchCommandException();
        }
        command.execute(tokens);
    }
}
