DROP SCHEMA players;

CREATE SCHEMA players;


DROP TABLE players.player;

CREATE TABLE players.player
(
			id_player BIGINT,
			modified_at TIMESTAMP WITH TIME ZONE,
			created_at TIMESTAMP WITH TIME ZONE,
			code TEXT,
			member_id TEXT,
			name_type_id BIGINT,
			firstname TEXT,
			middlename TEXT,
			lastname TEXT,
			lastname2 TEXT,
			gender_id BIGINT,
			date_of_birth TIMESTAMP WITH TIME ZONE,
			nationality TEXT,
			active BOOLEAN,
	
	CONSTRAINT player_pk PRIMARY KEY (id_player)
);

