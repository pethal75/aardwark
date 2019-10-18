package com.aardwark.players;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aardwark.players.api.PlayerApi;
import com.aardwark.players.xml.player.PlayerXml;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

@Component
public class PlayersCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PlayersCommandLineRunner.class);

	@Override
	public void run(String... args) throws Exception {
		
		log.info("--- Starting players command line application -----------------------------------------");

		//-------------------------
		// Setup client
		//-------------------------
		PlayerApi client = new PlayerApi();
		client.getApiClient().setUsername("xxx");
		client.getApiClient().setPassword("yyy");

		//-------------------------
		// Call API
		//-------------------------
		String response = client.activePlayerListGet("","");
		
		//-------------------------
		// Prepare XStream
		//-------------------------
		XStream xstream = new XStream();
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xstream.allowTypeHierarchy(Collection.class);
		xstream.allowTypesByWildcard(new String[] {ResultActivePlayerListXml.class.getPackage().getName()+".*"});
		xstream.alias("Result", ResultActivePlayerListXml.class);
		xstream.addImplicitCollection(ResultActivePlayerListXml.class, "players");
		xstream.alias("Player", PlayerXml.class);
		
		//-------------------------
		// Parse response
		//-------------------------
		ResultActivePlayerListXml result = (ResultActivePlayerListXml)xstream.fromXML(response);
		
		//-------------------------
		// Print players to console
		//-------------------------
		int index = 1;
		
		for(PlayerXml p : result.getPlayers() )
		{
			log.info( index + ".player code " + p.getCode() + " " + p.getFirstname() + " " + p.getLastname());
			
			index++;
		}
			
		log.info("--- Ending players command line application -----------------------------------------");
	}
}
