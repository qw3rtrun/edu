package com.github.qw3rtrun.edu;

import org.eclipse.persistence.platform.server.ServerPlatformBase;
import org.eclipse.persistence.sessions.DatabaseSession;

public class BitronixServerPlatform extends ServerPlatformBase {

    private static final String SERVER_NAME_AND_VERSION =  "Bitronix v7.0";

    public BitronixServerPlatform(DatabaseSession newDatabaseSession) {
        super(newDatabaseSession);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.persistence.platform.server.ServerPlatformBase#getExternalTransactionControllerClass()
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Class getExternalTransactionControllerClass() {
        if (externalTransactionControllerClass == null) {
            externalTransactionControllerClass = BitronixTransactionController.class;
        }

        return externalTransactionControllerClass;
    }

    /* (non-Javadoc)
     * @see org.eclipse.persistence.platform.server.ServerPlatformBase#getServerNameAndVersion()
     */
    @Override
    protected void initializeServerNameAndVersion() {        
        this.serverNameAndVersion = SERVER_NAME_AND_VERSION;
    }
}
