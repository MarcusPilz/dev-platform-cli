package com.dbsystel.maven.plugins.tibco.cli;

import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

/**
 * Create the deployment configuration file. The deployment configuration file is created by the AppManage utility based
 * on information in the EAR file. The -out option provides the name and location of the deployment configuration file.
 * The file is created with XML tags for all required schema and substitution variables for each machine binding tag.
 * <p>
 * AppManage -export -ear c:\ears\deployment\filenotify.ear -out c:\ears\deployments\filenotify.xml
 * </p>
 * 
 * @author MarcusPilz
 *
 */
public class GenerateDeploymentConfigCommand {
    private String command;

    private File earFile;

    private File xmlConfigFile;

    public GenerateDeploymentConfigCommand(String cmd, File earFile, File xmlConfigFile) {
        this.command = cmd;
        this.earFile = earFile;
        this.xmlConfigFile = xmlConfigFile;
    }

    public void execute() throws Exception {
        String command = getCommand();
        System.out.println("Executing: " + command);

        CommandLine cmdLine = CommandLine.parse(getCommand());
        DefaultExecutor executor = new DefaultExecutor();
        int exitValue = executor.execute(cmdLine);
        System.out.println("RETCODE :: " + exitValue);
    }

    public String getCommand() {
        StringBuffer buf = new StringBuffer();
        buf.append(command);
        buf.append("-export");
        buf.append("-ear");
        buf.append(earFile.getAbsolutePath());
        buf.append("-out");
        buf.append(xmlConfigFile.getAbsolutePath());

        return buf.toString();
    }

}
