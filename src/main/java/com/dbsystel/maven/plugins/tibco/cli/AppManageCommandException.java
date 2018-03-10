/**
 * 
 */
package com.dbsystel.maven.plugins.tibco.cli;

/**
 * @author MarcusPilz
 *
 */
public class AppManageCommandException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * Constructs a new exception with the specified detail key. 
     * @param key the detailed key
     * 
     */
    public AppManageCommandException(String key) {
        super(key);
    }

    /**
     * 
     * Constructs a new exception with the specified detail key and cause. 
     * @param key the detailed key
     * @param cause the detailed cause
     */
    public AppManageCommandException(String key, Throwable cause) {
        super(key, cause);
    }

    /**
     * 
     * Constructs a new exception with the specified detail cause. 
     * @param cause the detailed cause
     */
    public AppManageCommandException(Throwable cause) {
        super(cause);
    }

}
