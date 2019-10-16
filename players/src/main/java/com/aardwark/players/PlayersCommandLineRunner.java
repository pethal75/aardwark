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

	public static final String DEFAULT_START_DATE = "2000-01-01";
	public static final int DEFAULT_START_YEAR = 2000;
	
	public static final int DEFAULT_DATE_STEP = 185;
	
	public static final int DEFAULT_COUNT = 10000;
	
	@Override
	public void run(String... args) throws Exception {
		
		log.info("--- Starting players command line application -----------------------------------------");
		
		PlayerApi client = new PlayerApi();

		client.getApiClient().setUsername("tsapi_bwf_test3");
		client.getApiClient().setPassword("JFt8Fq71rZqTB0WzLosm3K99CKWbkjsl");
		
		client.getApiClient().setConnectTimeout(20000);
		client.getApiClient().setReadTimeout(20000);
		client.getApiClient().setWriteTimeout(20000);

			String response = client.activePlayerListGet("","");
			
			System.out.println("Success " + response);

			XStream xstream = new XStream();

			xstream.addPermission(NullPermission.NULL);
			xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
			xstream.allowTypeHierarchy(Collection.class);
			// allow any type from the same package
			xstream.allowTypesByWildcard(new String[] {
			    ResultActivePlayerListXml.class.getPackage().getName()+".*"
			});
			
			xstream.alias("Result", ResultActivePlayerListXml.class);
			xstream.addImplicitCollection(ResultActivePlayerListXml.class, "players");
			xstream.alias("Player", PlayerXml.class);
			
			ResultActivePlayerListXml result = (ResultActivePlayerListXml)xstream.fromXML(response);
			
			int index = 0;
			
			for(PlayerXml p : result.getPlayers() )
			{
				System.out.println( index + ".player code " + p.getCode() + " " + p.getFirstname() + " " + p.getLastname());
				
				index++;
			}
			

		log.info("--- Ending players command line application -----------------------------------------");
	}
}
