package com.dbsystel.maven.plugins.tibco.cli;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBuilderCommand {
    /**
     * 
     */
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 
     */
    private String command;

    /**
     * 
     */
    private List<String> arguments;

    /**
     * 
     */
    protected DefaultExecuteResultHandler resultHandler;

    /**
     * 
     * @param command - the builder cmd to execute
     */
    public AbstractBuilderCommand(String command) {
        this.command = command;
        arguments = new ArrayList<String>();
        resultHandler = new DefaultExecuteResultHandler();
    }

    /**
     * 
     * @param key - argument to add
     */
    public void addArgument(String key) {
        arguments.add(key);
    }

    /**
     * 
     * @return File the location of the project folder
     */
    public abstract File getProjectFolder();

    /**
     * 
     * @return - the uri
     */
    public abstract String getUri();

    /**
     * 
     * @return the assigned outputFile
     */
    public abstract File getOutputFile();

    /**
     * 
     * @return hide libraries
     */
    public abstract boolean hideLibraries();

    /**
     * 
     * @return path o aliases
     */
    public abstract String pathToAliases();

    /**
     * 
     * @return weather the outputfile should be overwritten
     */
    public abstract boolean getOverwriteOutputFile();

    /**
     * 
     * @return the propertiy file
     */
    public abstract String getPropertiesFile();

    /**
     * 
     * @return int exitValue - 0 if successul
     * @throws ExecuteException if an error during execution occured
     * @throws IOException if the files could not accessed
     */
    public int execute() throws ExecuteException, IOException {
        int retCode = 0;
        String command = arguments();

        CommandLine cmdLine = CommandLine.parse(command);
        Executor executor = new DefaultExecutor();
        //executor.execute(cmdLine, resultHandler);
        retCode = executor.execute(cmdLine);
        //executor.setExitValue(0);
        //retCode = resultHandler.getExitValue();

        return retCode;
    }

    /**
     * 
     * @return the arguments as String
     */
    public abstract String arguments();

    /**
     * TODO CHECK IF THIS PARAMETERS SHOULD BE CALLED EVERY APPMANAGE CMD
     * @return a list with common arguments for appmanage
     */
    public StringBuffer defaultArguments() {
        StringBuffer buf = new StringBuffer();
        buf.append(command);
        buf.append(" --propFile ");
        buf.append(getPropertiesFile());
        String pathToAliases = pathToAliases();
        if (pathToAliases != null) {
            buf.append(" -a ");
            buf.append(pathToAliases); // path to fileAliases.properties
        }
        String uri = getUri();
        if (uri != null) {
            buf.append(" -lib "); // path of the libbuilder relative to the BW project path
            buf.append(uri);
        }
        File outputFile = getOutputFile();
        if (outputFile != null) {
            buf.append(" -o "); // output file
            buf.append(getOutputFile());
        }
        buf.append(" -p ");
        buf.append(getProjectFolder()); // BW project path
        if (getOverwriteOutputFile()) {
            buf.append(" -x "); // overwrite the output
        }

        //        if (hideLibraries()) {
        //            buf.append(" -v ");
        //        }

        return buf;
    }

}
