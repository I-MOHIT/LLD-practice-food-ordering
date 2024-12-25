package main;

import main.commands.CommandExecutor;
import main.commands.CommandExecutorFactory;
import main.exceptions.CommandNotFoundException;
import main.models.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode {
    private CommandExecutorFactory commandExecutorFactory;
    private OutputPrinter outputPrinter;

    public InteractiveMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    public void process() throws IOException {
        outputPrinter.hello();
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String inputString = bufferedReader.readLine();
            Command command = new Command(inputString);
            CommandExecutor commandExecutor = this.commandExecutorFactory.getCommandExecutor(command);
            this.processCommand(command, commandExecutor);
            if (command.getCommandName().equals("exit")) {
                return;
            }
        }
    }

    private void processCommand(Command command, CommandExecutor commandExecutor) {
        if (!commandExecutor.validateCommand(command)) {
            throw new CommandNotFoundException();
        }
        commandExecutor.executeCommand(command);
    }
}
