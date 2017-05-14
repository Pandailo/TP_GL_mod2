DROP TABLE PGL_Musique;
CREATE TABLE PGL_Musique(id INTEGER,titre VARCHAR2(25),duree INTEGER,artiste VARCHAR2(50));
CREATE OR REPLACE TRIGGER trigger_PGL_id BEFORE INSERT ON PGL_Musique FOR EACH ROW
DECLARE
	idm INTEGER;
BEGIN
	SELECT max(id) INTO idm FROM PGL_Musique;
	IF idm IS NOT NULL
	THEN 
		idm:=idm+1; 
		:new.id := idm;
	ELSE
		:new.id :=0;
	END IF;
END;
/

INSERT INTO PGL_Musique VALUES(0,'Down with the sickness',216,'Disturbed');
INSERT INTO PGL_Musique VALUES(0,'Prayer of the refugee',217,'Rise Against');
INSERT INTO PGL_Musique VALUES(0,'Superstition',267,'Stevie Wonder');
INSERT INTO PGL_Musique VALUES(0,'Sweet Child O"Mine',299,'Guns N" Roses');
INSERT INTO PGL_Musique VALUES(0,'Short Change Hero',237,'The Heavy');