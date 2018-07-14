# Oblikovanje programske potpore
## Grupa Vrata, voditelj: Josip Torić
## Projektni zadatak: Sustav za upravljanje rada pizzerie

### Opis problema

Jedna kvartovska pizzeria odlučila je povećati svoj posao. Unutarnji prostor
pizzerie je relativno malen te uvijek pun jer imaju odličan ambijent i još bolju pizzu.
Međutim, kapaciteta za izbacivanje pizza imaju otprilike tri puta više nego što ih
proizvedu svakog dana. Imaju dostavu koja je samo telefonska i nije baš puno
popularna jer gotovo svi ljudi danas naručivanje pizze rade preko interneta. Odlučili
su se dakle okrenuti novi tehnologijama i uvesti dostavu preko interneta. Kontaktirali
su trenutno najpopularniji online servis za dostavu hrane, no oni su im željeli uzimati
30 % ukupne zarade putem dostave. Vlasniku pizzerie se to nikako nije svidjelo te je
odlučio da želi imati vlastitu web aplikaciju. Dakle, dogovorena je izrada web stranice
za potrebe pizzerie.

### Funkcionalni zahtjevi sustava

Web stranicama pizzerie mogu pristupati neprijavljeni korisnici, prijavljeni
korisnici (kupci), zaposlenici pizzerie i administrator.
Neprijavljeni korisnici mogu pogledati kompletnu ponudu pizzerie, vidjeti
galeriju slika te sve opće informacije o pizzeriji.
	Ulaskom na glavnu stranicu pizzerie korisnici bi dobili prikaz glavnih usluga koje ona
nudi s ponudama različitih pizza. Na stranici bi se također nalazili i linkovi na galeriju
slika, on-line naručivanje, sekciju za kontakt i informacije o pizzeriji.
Ponuda pizzerie se sastoji od prikaza svih pizza s njihovom ocjenom. Klikom na
pojedini pizzu, otvara se stranica s detaljima pizze. Detalji pizze se sastoje od opisa
pizze, njenih sastojaka, nutritivne vrijednosti i cijene. Također svaka pizza ima svoju
mini galeriju gdje se vidi kako izgleda gotova pizza.
	Klikom na galeriju slika s naslovne stranice može se vidjeti ambijent same pizzerie,
proces izrade pizze i nasmijano osoblje.
Opće informacije pizzerie se sastoji od adrese pizzerie, lokacije na google karti,
kontakt brojeva te opisa pizzerie.
	Na sučelju neregistriranih korisnika se u svakom momentu treba nuditi da se
registriraju. Nakon uspješne registracije korisnik se može prijaviti u sustav.
Neprijavljenim korisnicima je mogućnost online naručivanja nije dostupna,
samo prijavljeni korisnici mogu naručiti pizzu.
	Prijavljeni korisnici mogu i naručiti pizzu i vidjeti sve svoje prethodne narudžbe.
	Nakon svake dostavljene pizze, kupce se potiče da ocjenu pizzu. Kupci vide
program vjernosti pizzerie. Klikom na gumb za naručivanje, zaposleniku u pizzeriji bi
se poslala poruka da je korisnik naručio pizzu.
	
Zaposlenici pizzerie imaju vlastito sučelje na kojem administriraju primljene
narudžbe.
 	Zaposlenik sustava također može privremeno onemogućiti nove narudžbe za
slučaj kada gužva u pizzeriji postane prevelika. Korisniku bi se prikazala poruka da su
narudžbe trenutno onemogućene te da pričeka neko vrijeme.
	Zaposlenik ima sučelje na kojem vidi sve prethodne današnje narudžbe na
njegovo ime i sve aktualne narudžbe.

Administrator sustava može uređivati ponudu pizza, administrirati korisnike te
vidjeti izvješća za pizzeriu.
	Izvješća se generiraju tjedno, mjesečno, kvartarno, polugodišnje i godišnje.
	Administrator može dodavati nove pizze, uređivati postojeće pizze i brisati
pizze iz ponude. Svaka pizza (govoreći kao entitet) se sastoji od imena pizze, opisa
pizze, sastojaka u pizzi, nutritivne vrijednosti (kao deskripciju) i cijene, te naravno
slika same pizze.
	Administrator ima uvid u sve korisnike. On može dodavati nove korisnike u
sustav, uređivati postojeće korisnike i brisati korisnike. Ove operacije se prvenstveno
odnose na dodavanje zaposlenika u pizzeriu i brisanje istih, iako administrator ima
mogućnost za brisanje i dodavanje svih korisnika. Klikom na zaposlenika otvara se
stranica s detaljima zaposlenika gdje se mogu uređivati svi njegovi ostali podaci.
	Prikaz svih zaposlenika je u tablici u kojoj su stupci ime, prezime, datum rođenja, dan
zaposlenja te broj izdanih pizza. Prikaz svih kupaca je također u tablici u kojoj se vidi
ime, prezime, datum rođenja i broj naručenih pizza. Klikom na kupca, također se
otvara nova stranica gdje on može uređivati osobne podatke korisnika.
	Izvješća trebaju biti tjedna, mjesečna, kvartarna, polugodišnja i godišnja. Uz
prikaz na web stranici, izvještaji se mogu preuzeti u .xlsx formatu ili .pdf formatu.

### Nefunkcionalni zahtjevi sustava
Nuđenje registracije treba biti suptilno koliko je to moguće. Za registraciju je
potrebno unijeti ime, prezime, e-mail, lozinku i broj telefona.
Uvjete programa vjernosti pizzerie postavlja administrator, a u principu
funkcionira da nakon određenog broja dostavljenih pizza, kupci imaju pravo na
besplatne pizze. Program vjernosti ima u sebi track bar koji se polagano puni kako su
kupci bliže nekom od pragova programa vjernosti, naravno u cilju da naručuju što više
i više pizza. Prelaskom praga od 5 naručenih pizza dobila bi se mogućnost izabiranja
pizze besplatno.
	Kada narudžba stigne u sustav, ona je u statusu "NARUČENA". Tu narudžbu
vide svi zaposlenici, a kada je neki zaposlenik prihvati, ona je u statusu "U
PRIPRAVLJANJU". Narudžba se također onda sprema na njegovo ime, tj. pamti se u
sustavu da ju je taj zaposlenik prihvatio. Kada se narudžba spremi i narudžba krene,
tada bude u statusu "U DOSTAVI". Kada se vrati dostavljač s novcima, narudžba
dođe u završni status "PLAĆENA". No, ako je došlo do nekih poteškoća s kupcem i 
narudžba nije dostavljena ili nije plaćena, tada narudžba bude u statusu "NIJE
PLAĆENA".
	Kada narudžba stigne u roku od 3 minute zaposlenik je dužan obavijestiti
korisnika za koliko će mu vremena stići pizza. Također kada dostavljač krene na
korisnikovu lokaciju, zaposlenik je dužan obavijestiti korisnika da mu je pizza krenula.
	Nadalje, kada se dostavljač vrati s novcima korisnika, zaposlenik je dužan u sustav
unijeti da je pizza plaćena, no za ovu naredbu ne treba posebno obavijestiti korisnika.
	Brisanje ljudi koji su korisnici pizzerije treba biti omogućeno zbog ljudi koji
pokušaju prevariti pizzeriu.
	Administrator sustava ne može vidjeti trenutnu lozinku korisnika, iako može na
zahtjev korisnika im postaviti novi password koji će im savjetovati da ga što prije
promjene.
	Svako izvješće sastoji se od ukupnog broja naručenih pizza, broj naručenih
pizza po vrsti pizze te broj naručenih pizza po svakom satu radnog vremena. Nadalje
u izvješću treba biti prosječno vrijeme dostave pizze, prosječno vrijeme dostave po
svakom satu, prosječna ocjena pizza te prosječna ocjena po svakoj pizzi.

### Tehnički zahtjevi sustava
Iako nisu fiksni i podložni su promjena, inicijalni je plan da se backend
aplikacije napravi u Spring frameworku za programski jezik Java. Frontend je u planu
da se napravi u Angular frameworku, koristit će se PostgreSQL sustav za upravljanje
bazom podataka. Autentifikacija korisnika će biti token based jer sustav će
funkcionirati kao client-based aplikacije a ne server-side.

### Opće upute
Cilj projekta je praktično primijeniti postupke oblikovanja programske podrške
na rješavanje konkretnih problema, izraditi projektnu dokumentaciju i što stvarniju
implementaciju za traženi zadatak. Program će se ispitivati postavljanjem upita pri
čemu svaki dio implementacije mora pod određenim uvjetima biti dohvatljiv. Pri tome
je bitna preglednost i laka dostupnost funkcionalnosti i sadržaja, budući da će se sve
ispitivati iz perspektive krajnjeg korisnika sustava. 