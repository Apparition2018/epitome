-- H2 Schema for World Database
-- Generated from MySQL world.sql

DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS countrylanguage;
DROP TABLE IF EXISTS country;

CREATE TABLE country (
    Code CHAR(3) NOT NULL DEFAULT '',
    Name CHAR(52) NOT NULL DEFAULT '',
    Continent VARCHAR(30) NOT NULL DEFAULT 'Asia',
    Region CHAR(26) NOT NULL DEFAULT '',
    SurfaceArea DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    IndepYear SMALLINT DEFAULT NULL,
    Population INT NOT NULL DEFAULT 0,
    LifeExpectancy DECIMAL(3, 1) DEFAULT NULL,
    GNP DECIMAL(10, 2) DEFAULT NULL,
    GNPOld DECIMAL(10, 2) DEFAULT NULL,
    LocalName CHAR(45) NOT NULL DEFAULT '',
    GovernmentForm CHAR(45) NOT NULL DEFAULT '',
    HeadOfState CHAR(60) DEFAULT NULL,
    Capital INT DEFAULT NULL,
    Code2 CHAR(2) NOT NULL DEFAULT '',
    PRIMARY KEY (Code)
);

CREATE TABLE city (
    ID INT NOT NULL AUTO_INCREMENT,
    Name CHAR(35) NOT NULL DEFAULT '',
    CountryCode CHAR(3) NOT NULL DEFAULT '',
    District CHAR(20) NOT NULL DEFAULT '',
    Population INT NOT NULL DEFAULT 0,
    PRIMARY KEY (ID)
);

CREATE TABLE countrylanguage (
    CountryCode CHAR(3) NOT NULL DEFAULT '',
    Language CHAR(30) NOT NULL DEFAULT '',
    IsOfficial CHAR(1) NOT NULL DEFAULT 'F',
    Percentage DECIMAL(4, 1) NOT NULL DEFAULT 0.0,
    PRIMARY KEY (CountryCode, Language)
);

CREATE INDEX idx_city_countrycode ON city (CountryCode);
CREATE INDEX idx_countrylanguage_countrycode ON countrylanguage (CountryCode);
