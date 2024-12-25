package main.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private final static String SPACE = " ";
    private String commandName;
    private List<String> params;

    public Command(String inputString) {
        this.commandName = "";
        this.params = new ArrayList<>();
        this.separateCommandNameAndParams(inputString);
    }

    private void separateCommandNameAndParams(String inputString) {
        List<String> tokens = Arrays.stream(inputString.trim().split(SPACE)).map(String::trim).filter(token -> !token.isEmpty()).collect(Collectors.toList());

        this.commandName = tokens.getFirst();
        tokens.removeFirst();
        this.params = tokens;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
