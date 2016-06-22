# Clan schema

# --- !Ups

CREATE TABLE clans (
  id SERIAL PRIMARY KEY,
  tag varchar(16) NOT NULL,
  clan_level integer,
  clan_points integer,
  war_win_streak integer,
  war_wins integer,
  war_ties integer,
  war_losses integer,
  members integer
);

# --- !Downs

DROP TABLE Clans;
