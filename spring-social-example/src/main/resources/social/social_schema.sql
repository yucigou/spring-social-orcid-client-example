CREATE TABLE IF NOT EXISTS UserConnection (
  userId varchar(255) NOT NULL,
  providerId varchar(255) NOT NULL,
  providerUserId varchar(255) DEFAULT '' NOT NULL,
  rank INTEGER NOT NULL,
  displayName varchar(255) DEFAULT NULL,
  profileUrl varchar(512) DEFAULT NULL,
  imageUrl varchar(512) DEFAULT NULL,
  accessToken varchar(255) NOT NULL,
  secret varchar(255) DEFAULT NULL,
  refreshToken varchar(255) DEFAULT NULL,
  expireTime BIGINT DEFAULT NULL,
  PRIMARY KEY (userId,providerId,providerUserId)
);