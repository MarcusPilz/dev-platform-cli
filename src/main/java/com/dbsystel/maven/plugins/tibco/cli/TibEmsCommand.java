package com.dbsystel.maven.plugins.tibco.cli;

import java.io.File;

/**
 * see: tibemsadmin 
 * 
 * @author MarcusPilz
 *
 */

public class TibEmsCommand extends AbstractEMSBuilderCommand {
    /**
     * option server <server-url> - connect to specified server
     */
    private String serverUrl = null;

    /**
     * option script <script-file> - execute specified script file and quit
     */
    private File script = null;

    /**
     * option user <user-name> - use this user name to connect to server
     */
    private String userName = null;

    /**
     * option password <password> - use this password to connect to server
     */
    private String passwd = null;

    /**
     * option pwdfile <passwd file> - use the password in the specified file
     */
    private File pwdFile = null;

    private boolean ignore = false;

    public TibEmsCommand(String command) {
        super(command);
    }

    /**
     * 
     * @param command
     * @param serverUrl  the url of the ems server
     * @param userName  the username used to connect to the server
     * @param password  the password used to connect to the server
     * @param script  the file contains username and password.
     */
    public TibEmsCommand(String command, String serverUrl, String userName, String password, File script) {
        super(command);
        super.addArgument(serverUrl);
        super.addArgument(userName);
        super.addArgument(password);
    }

    /**
     * 
     * @return serverUrl the url of the ems server
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * 
     * @param serverUrl the url of the ems server
     */
    public void setServerURL(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * 
     * @return script the file contains username and password.
     */
    public File getScript() {
        return script;
    }

    /**
     * 
     * @param script
     *            the file contains username and password.
     */
    public void setScript(File script) {
        this.script = script;
    }

    /**
     * 
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName the username used to connect to the server
     */
    public void setUser(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return password the password used to connect to the server
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * 
     * @param passwd the password used to connect to the server
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * 
     * @return the passwordFile the file contains username and password.
     */
    public File getPwdFile() {
        return pwdFile;
    }

    /**
     * 
     * @param pwdFile the file contains username and password.
     */
    public void setPwdFile(File pwdFile) {
        this.pwdFile = pwdFile;
    }

    /**
     * 
     * @return true to ignore
     */
    public boolean isIgnore() {
        return ignore;
    }

    /**
     * 
     * @param ignore true to ignore error messages
     */
    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    @Override
    public String arguments() {
        StringBuffer buf = this.defaultArguments();
        buf.append(" -server ");
        buf.append(this.getServerUrl());
        if (this.getPwdFile() == null) {
            buf.append(" -user ");
            buf.append(this.getUserName());
            if (this.getPasswd() != null & this.getPasswd().length() > 0) {
                buf.append(" -password ");
                buf.append(this.getPasswd());
            }
        } else {
            buf.append(" -pwdfile ");
            buf.append(this.getPwdFile().getAbsolutePath());
        }
        if (script != null) {
            buf.append(" -script ");
            buf.append(script.getAbsolutePath());
        }
        if (ignore) {
            buf.append(" -ignore");
        }
        return buf.toString();
    }
}
