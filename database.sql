DROP DATABASE IF EXISTS rentalproperties;
CREATE DATABASE rentalproperties; 
USE rentalproperties;

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
    username		varchar(150) not null,
    password        varchar(150) not null,
    logintype       varchar(150) not null,
	primary key (Username)
);

DROP TABLE IF EXISTS listedproperties;
CREATE TABLE PROPERTY (
	id        		int unsigned NOT NULL auto_increment,
	type			varchar(150) NOT NULL,
    numberOfBaths   int unsigned NOT NULL,
	numberOfBedrooms	int unsigned NOT NULL,
    furnished       BOOLEAN NOT NULL,
    address			varchar(150) NOT NULL,
    quadrant		varchar(5) NOT NULL,
    stateOfListing	varchar(20) NOT NULL,
    listingStart	varchar(40) NOT NULL,
    listingEnd 		varchar(40) NOT NULL,
    lanlordEmail    varchar(40) NOT NULL,
	primary key (id)
);

DROP TABLE IF EXISTS emails;
CREATE TABLE emails (
	sender varchar(40) NOT NULL,
    receiver varchar(40) NOT NULL,
    message varchar(300) NOT NULL
);

INSERT INTO PROPERTY (Landlord)
VALUES
('landlord');

INSERT INTO INBOX (Username, PropertyID)
VALUES
('user', 1);
*/
    