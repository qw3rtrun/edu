package com.github.qw3rtrun.edu;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.server.Server;


public class MyServerCustomizer implements SessionCustomizer {

	@Override
	public void customize(Session session) throws Exception {
		Server server = (Server) session;
		System.out.println(server.getServerPlatform());
	}
}
