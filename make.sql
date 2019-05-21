CREATE TABLE sedzia
(
    id_sedzia SERIAL NOT NULL,
    imie VARCHAR NOT NULL,
    nazwisko VARCHAR NOT NULL,
    miasto VARCHAR NOT NULL,
    uprawnienia INTEGER NOT NULL,
    ilosc_meczow INTEGER DEFAULT 0,
    PRIMARY KEY (id_sedzia)
);

CREATE TABLE stadion
(
    id_stadion SERIAL NOT NULL,
    nazwa VARCHAR NOT NULL,
    miasto VARCHAR NOT NULL,
    adres VARCHAR NOT NULL,
    pojemnosc INTEGER NOT NULL,
    PRIMARY KEY (id_stadion)
);

CREATE TABLE druzyna
(
    id_druzyna SERIAL NOT NULL,
    nazwa VARCHAR NOT NULL,
    liga INTEGER NOT NULL,
    miasto VARCHAR NOT NULL,
    id_stadion INTEGER NOT NULL,
    punkty INTEGER DEFAULT 0,
    bramki_zdobyte INTEGER DEFAULT 0,
    bramki_stracone INTEGER DEFAULT 0,
    budzet INTEGER DEFAULT 0,
    różnica_bramek INTEGER DEFAULT 0,
    PRIMARY KEY (id_druzyna),
    FOREIGN KEY (id_stadion) REFERENCES stadion(id_stadion)
);

CREATE TABLE sztabowiec_funkcja
(
    id_funkcja SERIAL NOT NULL,
    funkcja VARCHAR,
    PRIMARY KEY (id_funkcja)
);

CREATE TABLE sztabowiec
(
    id_sztabowiec SERIAL NOT NULL,
    imie VARCHAR NOT NULL,
    nazwisko VARCHAR NOT NULL,
    funkcja INTEGER,
    id_druzyna INTEGER NOT NULL,
    PRIMARY KEY (id_sztabowiec),
    FOREIGN KEY (id_druzyna) REFERENCES druzyna(id_druzyna),
    FOREIGN KEY (funkcja) REFERENCES sztabowiec_funkcja(id_funkcja)
);

CREATE TABLE pozycja
(
    id_pozycja SERIAL NOT NULL,
    pozycja VARCHAR,
    PRIMARY KEY (id_pozycja)
);

CREATE TABLE pilkarz
(
    id_pilkarz SERIAL NOT NULL,
    imie VARCHAR NOT NULL,
    nazwisko VARCHAR NOT NULL,
    id_druzyna INTEGER NOT NULL,
    pozycja INTEGER,
    bramki INTEGER DEFAULT 0,
    czyste_konta INTEGER DEFAULT 0,
    wartosc INTEGER DEFAULT 0,
    PRIMARY KEY (id_pilkarz),
    FOREIGN KEY (id_druzyna) REFERENCES druzyna(id_druzyna),
    FOREIGN KEY (pozycja) REFERENCES pozycja(id_pozycja)
);

CREATE TABLE sponsor_branza
(
    id_branza SERIAL NOT NULL,
    branza VARCHAR,
    PRIMARY KEY (id_branza)
);

CREATE TABLE sponsor
(
    id_sponsor SERIAL NOT NULL,
    nazwa VARCHAR NOT NULL,
    branza INTEGER,
    PRIMARY KEY (id_sponsor),
    FOREIGN KEY (branza) REFERENCES sponsor_branza(id_branza)
);

CREATE TABLE sponsoring
(
    id_sponsor INTEGER NOT NULL,
    id_druzyna INTEGER NOT NULL,
    kwota INTEGER DEFAULT 0,
    PRIMARY KEY (id_sponsor, id_druzyna),
    FOREIGN KEY (id_sponsor) REFERENCES sponsor(id_sponsor),
    FOREIGN KEY (id_druzyna) REFERENCES druzyna(id_druzyna)
);

CREATE TABLE mecz
(
    id_gospodarz INTEGER NOT NULL,
    id_gosc INTEGER NOT NULL,
    id_sedzia INTEGER NOT NULL,
    bramki_gospodarz INTEGER DEFAULT 0,
    bramki_gosc INTEGER DEFAULT 0,
    frekwencja INTEGER DEFAULT 0,
    PRIMARY KEY (id_gospodarz, id_gosc),
    FOREIGN KEY (id_gospodarz) REFERENCES druzyna(id_druzyna),
    FOREIGN KEY (id_gosc) REFERENCES druzyna(id_druzyna),
    FOREIGN KEY (id_sedzia) REFERENCES sedzia(id_sedzia)
);

INSERT INTO stadion (nazwa, miasto, adres, pojemnosc) VALUES
('Stadion miejski im. Henryka Reymana w Krakowie', 'Kraków', 'Reymonta 22', 33000),
('Stadion miejski w Warszawie', 'Warszawa', 'Łazienkowska 3', 31000),
('Stadion miejski w Poznaniu', 'Poznań', 'Bułgarska 17', 45000),
('Stadion miejski w Białymstoku', 'Białystok', 'Słoneczna 7', 25000),
('Stadion miejski w Szczecinie', 'Szczecin', 'Krakowska 19', 15000),
('Stadion miejski w Gdańsku', 'Gdańsk', 'Podchorążych 5', 40000),
('Stadion miejski w Gdyni', 'Gdynia', 'Duża 10', 10000),
('Stadion miejski we Wrocławiu', 'Wrocław', 'Mała 2', 40000),
('Stadion miejski w Rzeszowie', 'Rzeszów', 'Biłgorajska 8', 8000),
('Stadion miejski w Lublinie', 'Lublin', 'Biłgorajska 13', 12000),
('Stadion miejski w Katowicach', 'Katowice', 'Zabrzańska 25', 20000),
('Stadion miejski w Zabrzu', 'Zabrze', 'Katowicka 20', 25000);

INSERT INTO druzyna (nazwa, liga, miasto, id_stadion) VALUES
('Motor Lublin', 2, 'Lublin', 10),
('Sopocianka Sopot', 3, 'Sopot', 7),
('Arka Gdynia', 1, 'Gdynia', 7),
('Lech Poznań', 1, 'Poznań', 3),
('Warta Poznań', 2, 'Poznań', 3),
('Śląsk Wrocław', 1, 'Wrocław', 8),
('Górnik Zabrze', 1, 'Zabrze', 12),
('GKS Katowice', 2, 'Katowice', 11),
('Stal Rzeszów', 3, 'Rzeszów', 9),
('MKS Szczecin', 2, 'Szczecin', 5);

INSERT INTO pozycja (pozycja) VALUES
('bramkarz'),
('obrońca'),
('pomocnik'),
('napastnik');

INSERT INTO pilkarz (imie, nazwisko, id_druzyna, pozycja, wartosc) VALUES
('Paweł', 'Brożek', 1, 4, 1000000),
('Michał', 'Kucharczyk', 2, 3, 2000000),
('Maciej', 'Jankowski', 5, 4, 500000),
('Maciej', 'Makuszewski', 6, 3, 1500000),
('Igor', 'Angulo', 9, 4, 1500000),
('Krzysztof', 'Mączyński', 8, 3, 100000),
('Jakub', 'Błaszczykowski', 1, 3, 5000000),
('Sławomir', 'Peszko', 1, 3, 1000000),
('Kamil', 'Wojtkowski', 1, 3, 3000000),
('Vullnet', 'Basha', 1, 3, 1500000),
('Marcin', 'Wasilewski', 1, 2, 1000000),
('Maciej', 'Sadlok', 1, 2, 2000000),
('Matej', 'Palcić', 1, 2, 1500000),
('Rafał', 'Pietrzak', 1, 2, 2000000),
('Marko', 'Kolar', 1, 4, 4000000),
('Mateusz', 'Lis', 1, 1, 3000000),
('Paweł', 'Chleb', 2, 1, 1000000),
('Michał', 'Trąba', 2, 4, 1000000),
('Tomasz', 'Mączny', 2, 3, 1000000),
('Krzysztof', 'Malarz', 2, 3, 1000000),
('Grzegorz', 'Brzęczyszczykiewicz', 2, 3, 1000000),
('Maciej', 'Rybus', 2, 3, 1200000),
('Igor', 'Mączyński', 2, 2, 1100000),
('Carlos', 'Rafa', 2, 2, 2100000),
('Robert', 'Grot', 2, 2, 1200000),
('Thomas', 'Geige', 2, 2, 1500000),
('Markus', 'Eisenbichler', 5, 1, 1000000),
('Roman', 'Koudelka', 5, 3, 500000),
('Paweł', 'Małaszyński', 5, 3, 500000),
('Pavels', 'Mickievicius', 5, 3, 500000),
('Zdenek', 'Vancura', 5, 3, 5000000),
('Victor', 'Hugo', 5, 2, 10000),
('Hugo', 'Trapp', 5, 2, 1500000),
('Donald', 'Polski', 5, 2, 1500000),
('Jarosław', 'Polski', 5, 2, 1500000),
('Władysław', 'Murzyn', 5, 4, 1500000),
('Waldemar', 'Pawlak', 6, 1, 1500000),
('Józef', 'Mackiewicz', 6, 4, 1200000),
('Borys', 'Szyc', 6, 3, 1200000),
('Ariel', 'Borysiuk', 6, 3, 1500000),
('Dusan', 'Oblak', 6, 3, 1800000),
('Vytautas', 'Novikovas', 6, 3, 2100000),
('Arvydas', 'Latasovas', 6, 2, 3100000),
('Tobias', 'Lotz', 6, 2, 1200000),
('Jesus', 'Ramos', 6, 2, 1200000),
('Rafael', 'Santi', 6, 2, 1200000),
('Diego', 'Lopez', 8, 1, 1200000),
('Leo', 'Diogenez', 8, 4, 1100000),
('Luis', 'Leo', 8, 3, 1000000),
('Janusz', 'Wolny', 8, 3, 3100000),
('Antoni', 'Cedro', 8, 3, 1300000),
('Piotr', 'Kowalski', 8, 3, 1100000),
('Krzysztof', 'Piorun', 8, 2, 1100000),
('Bogusław', 'Pierun', 8, 2, 1200000),
('Mirosław', 'Poroże', 8, 2, 1300000),
('Maciej', 'Boryna', 8, 2, 1400000),
('Paweł', 'Bestia', 9, 1, 1500000),
('Christian', 'Anastasić', 9, 4, 1600000),
('Paweł', 'Dobry', 9, 3, 1700000),
('Denis', 'Romeo', 9, 3, 1100000),
('Alessio', 'Blue', 9, 3, 2100000),
('Piotr', 'Skąpy', 9, 3, 1000000),
('Janusz', 'Szybki', 9, 2, 1000000),
('Goran', 'Damjan', 9, 2, 1000000),
('Domen', 'Damjan', 9, 2, 1000000),
('Peter', 'Damjan', 9, 2, 1500000);

INSERT INTO sedzia (imie, nazwisko, miasto, uprawnienia) VALUES
('Szymon', 'Marciniak', 'Płock', 1),
('Paweł', 'Raczkowski', 'Toruń', 1),
('Tomasz', 'Musiał', 'Sandomierz', 1),
('Fabian', 'Drzyzga', 'Bydgoszcz', 1),
('Adam', 'Frokter', 'Włocławek', 1),
('Stefan', 'Kłaczek', 'Łęczyca', 1),
('Damian', 'Drab', 'Nałęczów', 1),
('Hubert', 'Śmieszek', 'Biłgoraj', 1),
('Hugo', 'Gliniany', 'Opole', 1),
('Feliks', 'Szczęśliwy', 'Zakopane', 1),
('Robert', 'Sebastian', 'Jastarnia', 1),
('Jakub', 'Jezus', 'Betlejem', 1),
('Grzegorz', 'Sadza', 'Głogów', 2),
('Paweł', 'Węgiel', 'Rybnik', 2),
('Łukasz', 'Graniczny', 'Zgorzelec', 3),
('Sebastian', 'Samochód', 'Świdnica', 3),
('Ireneusz', 'Klops', 'Tarnobrzeg', 3),
('Waldemar', 'Klocek', 'Gliwice', 3);

INSERT INTO sztabowiec_funkcja (funkcja) VALUES
('trener'),
('asystent trenera'),
('trener bramkarzy'),
('masażysta'),
('fizjoterapeuta'),
('kierwonik drużyny'),
('kucharz');

INSERT INTO sztabowiec (imie, nazwisko, funkcja, id_druzyna) VALUES
('Maciej', 'Stolarczyk', 1, 1),
('Adam', 'Mickiewicz', 2, 1),
('Juliusz', 'Słowacki', 4, 1),
('Antoni', 'Macierewicz', 1, 2),
('Alfons', 'Mądry', 2, 2),
('Albert', 'Głupi', 3, 2),
('Karol', 'Jyster', 1, 3),
('Edward', 'Nirek', 2, 3),
('Cyprian', 'Norwid', 1, 4),
('Grzegorz', 'Marcin', 1, 5),
('Paweł', 'Kulczycki', 1, 6),
('Feliks', 'Wójcicki', 1, 7),
('Jarosław', 'Królewski', 1, 8),
('Rafał', 'Carski', 1, 9),
('Juliusz', 'Cesarski', 1, 10),
('Julian', 'Niemcewicz', 1, 11),
('Czesław', 'Widzący', 1, 12),
('Hubert', 'Matynia', 4, 4),
('Franciszek', 'Flak', 6, 4),
('Olaf', 'Jowisz', 5, 5),
('Tomasz', 'Problem', 4, 5),
('Zenon', 'Kalafior', 6, 6),
('Leon', 'Zawodowiec', 7, 6),
('Kelli', 'Kutaisi', 4, 7),
('Xavier', 'Ramirez', 3, 7),
('Noriaki', 'Kasai', 2, 8),
('Paweł', 'Samarytanin', 4, 8),
('Jonasz', 'Judasz', 3, 9),
('Alfred', 'Batman', 6, 9),
('Jude', 'Law', 7, 10),
('Christian', 'Bale', 5, 10),
('Johnny', 'Depp', 7, 11),
('Bruce', 'Willis', 3, 11),
('Matthew', 'McConaughey', 2, 12),
('Tomasz', 'Karolak', 3, 12);

INSERT INTO sponsor_branza (branza) VALUES
('Odzież'),
('IT'),
('Bukmacherka'),
('Spożywcze'),
('Linie lotnicze'),
('Motoryzacja');

INSERT INTO sponsor (nazwa, branza) VALUES
('Adidas', 1),
('Nike', 1),
('Puma', 1),
('Reebok', 1),
('Comarch', 2),
('WFiIS', 2),
('Synerise', 2),
('Samsung', 2),
('LVBet', 3),
('STS', 3),
('Fortuna', 3),
('Auchan', 4),
('Żabka', 4),
('Biedronka', 4),
('Awiteks', 4),
('Fly Emirates', 5),
('LOT', 5),
('Air France', 5),
('Lufthansa', 5),
('Volvo', 6),
('Volkswagen', 6),
('BMW', 6),
('Audi', 6),
('Renault', 6),
('Mercedes', 6),
('Porsche', 6),
('Aston Martin', 6),
('Ford', 6),
('Lexus', 6);


CREATE OR REPLACE FUNCTION dodano_sponsoring (druzyna_s INTEGER, kwota INTEGER)
RETURNS INTEGER AS
$$
    UPDATE druzyna SET budzet = budzet + kwota WHERE id_druzyna = druzyna_s RETURNING 1;
$$
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION tabela_liga (liga_f INTEGER)
RETURNS TABLE
(
    Pozycja BIGINT,
    Drużyna VARCHAR,
    Punkty INTEGER,
    "Różnica bramkek" INTEGER,
    "Bramki zdobyte" INTEGER,
    "Bramki stracone" INTEGER
) 
AS
$$
    SELECT rank() over (ORDER BY punkty DESC, różnica_bramek DESC, bramki_zdobyte DESC), nazwa, punkty, różnica_bramek, bramki_zdobyte, bramki_stracone 
    FROM druzyna 
    WHERE liga = liga_f;
$$
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION tabela_strzelcy (liga_f INTEGER)
RETURNS TABLE
(
    Pozycja BIGINT,
    imię VARCHAR,
    nazwisko VARCHAR,
    bramki INTEGER,
    druzyna VARCHAR
) 
AS
$$
    SELECT rank() over (ORDER BY bramki DESC), imie, nazwisko, bramki, nazwa 
    FROM pilkarz JOIN druzyna ON pilkarz.id_druzyna = druzyna.id_druzyna 
    WHERE liga = liga_f AND bramki > 0;
$$
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION tabela_bramkarze (liga_f INTEGER)
RETURNS TABLE
(
    Pozycja BIGINT,
    imię VARCHAR,
    nazwisko VARCHAR,
    "Czyste konta" INTEGER,
    druzyna VARCHAR
) 
AS
$$
    SELECT rank() over (ORDER BY czyste_konta DESC), imie, nazwisko, czyste_konta, nazwa 
    FROM pilkarz JOIN druzyna ON pilkarz.id_druzyna = druzyna.id_druzyna 
    WHERE liga = liga_f AND czyste_konta > 0;
$$
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION mecze (liga_f INTEGER)
RETURNS TABLE
(
    Gospodarz VARCHAR,
    "Bramki gospodarz" INTEGER,
    "Bramki gość" INTEGER,
    Gość VARCHAR,
    Frekwencja INTEGER
) 
AS
$$
    SELECT d1.nazwa, bramki_gospodarz, bramki_gosc, d2.nazwa, frekwencja 
    FROM mecz m JOIN druzyna d1 ON m.id_gospodarz = d1.id_druzyna JOIN druzyna d2 ON m.id_gosc = d2.id_druzyna 
    WHERE d1.liga = liga_f;
$$
LANGUAGE SQL;

CREATE VIEW sedziowie
AS
SELECT imie, nazwisko, miasto, uprawnienia, ilosc_meczow 
FROM sedzia 
ORDER BY uprawnienia, ilosc_meczow DESC;

CREATE VIEW stadiony
AS
SELECT nazwa, pojemnosc, miasto, adres 
FROM stadion 
ORDER BY pojemnosc DESC;

CREATE VIEW pilkarze
AS
SELECT imie, nazwisko, pozycja.pozycja, nazwa 
FROM pilkarz JOIN druzyna ON pilkarz.id_druzyna = druzyna.id_druzyna JOIN pozycja ON pilkarz.pozycja = pozycja.id_pozycja
ORDER BY liga, druzyna.id_druzyna, pilkarz.pozycja;

CREATE VIEW sztab
AS
SELECT imie, nazwisko, sf.funkcja, nazwa AS drużyna
FROM sztabowiec s JOIN sztabowiec_funkcja sf ON s.funkcja = sf.id_funkcja JOIN druzyna d ON s.id_druzyna = d.id_druzyna 
ORDER BY d.id_druzyna, s.funkcja;

CREATE VIEW sponsorzy
AS
SELECT nazwa, sb.branza
FROM sponsor s JOIN sponsor_branza sb ON s.branza = sb.id_branza
ORDER BY s.branza;

CREATE VIEW umowy_sponsorskie
AS
SELECT s.nazwa AS sponsor, d.nazwa AS drużyna, kwota
FROM sponsoring sp JOIN sponsor s ON sp.id_sponsor = s.id_sponsor JOIN druzyna d ON sp.id_druzyna = d.id_druzyna
ORDER BY kwota DESC;