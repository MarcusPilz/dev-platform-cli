package com.dbsystel.maven.plugins.tibco.cli;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public abstract class AbstractCompilerCommand {

    private String command;

    private List<String> arguments;

    public AbstractCompilerCommand(String command) {
        this.command = command;
        arguments = new ArrayList<String>();
    }

    public void addArgument(String key) {
        arguments.add(key);
    }

    public abstract File getProjectFolder();

    public abstract String getUri();

    public abstract File getOutputFile();

    public abstract boolean hideLibraries();

    public abstract String pathToAliases();

    public abstract boolean getOverwriteOutputFile();

    public abstract String getPropertiesFile();

    public int execute() throws ExecuteException, IOException {
        int retCode = 0;
        String command = arguments();

        CommandLine cmdLine = CommandLine.parse(command);

        System.out.println(cmdLine);

        DefaultExecutor executor = new DefaultExecutor();
        retCode = executor.execute(cmdLine);

        return retCode;
    }

    public abstract String arguments();

    /**
     * TODO CHECK IF THIS PARAMETERS SHOULD BE CALLED EVERY APPMANAGE CMD
     * @return a list with common arguments for appmanage
     */
    public StringBuffer defaultArguments() {
        StringBuffer buf = new StringBuffer();
        buf.append(this.command);
        buf.append(" --propFile ");
        buf.append(getPropertiesFile());
        String pathToAliases = pathToAliases();
        if (pathToAliases != null) {
            buf.append(" -a ");
            buf.append(pathToAliases);
        }

        return buf;
    }

}
