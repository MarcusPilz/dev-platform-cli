package com.dbsystel.maven.plugins.tibco.cli;

import java.io.File;

/**
 * The validateproject utility allows you to validate all project resources on the command line. The validateproject
 * online help shows the command line syntax and describes each command option. To use the online help, change directory
 * to TIBCO_HOME/designer/version/bin and type validateproject.
 * <p>
 * The online help is as follows: usage:
 * </p>
 * <p>
 * ValidateProject [-h] [-u] [-a pathToAliases] project
 * </p>
 * -h: display this message -u: show warnings for unused global variables -a: path to a properties file mapping alias
 * names to library paths (aliasName=/path/to/library/file) project: the designer project to use.
 * 
 * @author MarcusPilz
 *
 */
public class ValidateCommand extends AbstractCompilerCommand {
    private File projectDir;

    private String traFile;

    private String pathToAliases;

    public ValidateCommand(String command, String traFile, File projectDir, String pathToAliases) {
        super(command);
        this.projectDir = projectDir;
        this.traFile = traFile;
        this.pathToAliases = pathToAliases;
    }

    @Override
    public File getProjectFolder() {
        return projectDir;
    }

    @Override
    public String getUri() {
        return null;
    }

    @Override
    public File getOutputFile() {
        return null;
    }

    @Override
    public boolean hideLibraries() {
        return false;
    }

    @Override
    public String pathToAliases() {
        return this.pathToAliases;
    }

    @Override
    public boolean getOverwriteOutputFile() {
        return false;
    }

    @Override
    public String getPropertiesFile() {
        return this.traFile;
    }

    @Override
    public String arguments() {
        StringBuffer buf = super.defaultArguments();
        /**
         * schould be set via option in pom
         */
        buf.append(" " + this.getProjectFolder());
        return buf.toString();
    }

}
