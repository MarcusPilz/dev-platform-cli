/**
 * 
 */
package com.dbsystel.maven.plugins.tibco.cli;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

/**
 * @author MarcusPilz Tibco AppManage Commands
 * 
 *         Extract all deployed EAR files in a tibco administrator domain C:\Apps\tibco\tra\5.7\bin\
 *         AppManage -batchExport -user admin -pw admin -domain Testdomain -dir c:/temp/TFS
 * 
 *         Extract particular EAR from the Administrator domain C:\Apps\tibco\tra\5.7\bin
 *         AppManage -export -out C:\temp\OnyxAdmin.xml -app OnyxAdmin -user admin -pw admin -domain Testdomain
 * 
 *         Extract information from above extracted EAR in the form of xml C:\Apps\tibco\tra\5.7\bin
 *         Appmanage -export -ear "C:\temp\OnyAdmin\OnyxAdmin.ear" -out "C:\Test\EAR\OnyxAdmin.xml"
 * 
 *         Start all BW services from the Administrator domain C:\Apps\tibco\tra\5.7\bin
 *         AppManage -batchstart -user admin -pw admin -domain Testdomain -dir c:/temp/TFS
 * 
 *         Stop all BW services from the Administrator domain \ C:\Apps\tibco\tra\5.7\bin
 *         AppManage -batchstop -user admin -pw admin -domain Testdomain -dir c:/temp/TFS
 * 
 *         Kill all BW services from the Administrator domain C:\Apps\tibco\tra\5.7\bin
 *         AppManage -batchkill -user admin -pw Adm1n1strat0r -domain TFS -dir c:/temp/TFS
 * 
 *         Deploy the EAR file from the Administrator domain C:\Apps\tibco\tra\5.7\bin
 *         AppManage -deploy -ear myTest.ear -deployConfig c:\test\myApp.xml -app myApp -user admin -pw admin-domain Testdomain
 * 
 *         Build an EAR file for a particular project C:\Apps\tibco\tra\5.7\bin
 *         buildear -s -ear -o "C:\Test\myportal.ear" -p "C:\Test\samplePORTAL"
 */
public abstract class AbstractAppManageCommand implements AppManageCommand {
    private String command;

    private List<String> arguments;

    public AbstractAppManageCommand(String command) {
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

    public abstract String getPathToAliases();

    public abstract boolean getOverwriteOutputFile();

    public abstract String getPropertiesFile();

    public abstract String getCommand();

    public void execute() throws AppManageCommandException {
        String command = getCommand();
        try {
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            if (exitValue != 0)
                throw new AppManageCommandException("ERROR DURING EXECUTING CMD: " + command);
        } catch (Exception e) {
            throw new AppManageCommandException(e);
        }
    }

}
