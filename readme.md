# Greyson Trains game

## Pravidla soutěže:

Pravidla soutěže:

1. Naprogramuj algoritmus v Javě. Cílem hry je naplánovat jízdní řády vlakům tak, aby byla zadaná ekonomika co nejvýkonnější. Prodejem surovin je spočítáno skóre. Vyhrává hráč s nejvyšším skóre.
    
2. Pro implementaci použijte prostředí předpřipravené hry, která je k dispozici na<a
                href="https://github.com/GreysonDevel/gc-trains">https://github.com/GreysonDevel/gc-trains</a>.
3. Odevzdaný algoritmus bude na konci soutěže spouštěn na referenčních mapách, které nejsou řešiteli k dispozici. (Různe velikosti map, různe počty vláčků dolů, továren a obchodů, různe rozmýstnění všech objektů na mapě.) Hra hra má omezený herní čas(1000 tiků = 1000 tick). Za jeden tik se:
   * vytěží nebo vyrobí jedna jednotka suroviny
   * posune vlak o jednou pozici(vlak se posouvá do 8 směrů)
   * vykládka celého vlaku
   * naložení určité suroviny do vlaku

Suroviny vznikají v dolech (Mine), zpracovávají se v továrnách (Factory) a jsou prodávány v obchodech (Shop). Co lze vytěžit v dolech, co a z čeho vyrábí továrny je popsáno ve veřejných metodách programu. Každá surovina má svoji cenu za kterou může být prodána v obchodech. Obchodu skupují všechno, ale za různé ceny. Každá budova má omezenou velikost skladu pro každou surovinu. Pokud je sklad plný, budova již surovinu nevyrábí a ani nepřijímá od vlaků. Pokud vlak doveze surovinu do plné stanice, nemůže vyložit surovinu a pokračuje dál. Pro jednotlivé vlaky sestav jízdní řád. Každý vlak má metodu addStation() která do jízdního řádu přidá zastávku. Vlak navštěvuje stanice v cyklu, neboli na konci jízdního řádu pokračuje opět od začátku.  

Stanice jsou dvou typů:
   * Nakládací - vlak naloží zvolenou surovinu až do své maximální kapacity
   * Vykládací - vlak kompletně vyloží svůj náklad
   
   GAME INFO:
   
   * game.getMaxX() a game.getMaxY() vrací maximální rozměry hracího pole
   * game.getTotalTicks() hra má omezený herní čas, vrací počet jednotek času
   * game.getMines() Vrátí všechny doly na mapě
   * game.getFactories() Vrátí všechny továrny na mapě
   * game.getShops() Vrátí všechny obchody na mapě
   * game.getTrains() Vrátí všechny vlaky na mapě
   
   Každý herní objekt má vlastní informace (poloha, produkce, atd.), vyzkoušej jeho viditelné metody.

4. Ve hře se setkáš s tímto ekonomickým modelem (surovina a její cena): 
    * Iron - 1
    * Wood - 1
    * Coal - 2
    * Gold - 5    
    * Steel - 10 (vznikne sloučením Iron & Coal)
    * Gold Ingot - 20 (vznikne sloučením Coal & Gold)
    * Tool - 100 (vznikne sloučením Wood & Tool)
    * Jewellery - 500 (vznikne sloučením Gold Ingot & Tool)
    
5. Počet odevzdaných algoritmů není nijak limitován. Soutěž končí<strong>26.5.2019 ve 23:59.</strong>

## Instrukce ke hře:
1. Stáhni si hru z GitHubu: <a href="https://github.com/GreysonDevel/gc-trains">https://github.com/GreysonDevel/gc-trains</a>.
        git clone https://github.com/GreysonDevel/gc-trains.git
2. Otevři ve svém oblíbeném IDE jako Java SE projekt.
   
* Ve složce <strong>game</strong> nic neměň, jinak si celou hru uděláš nekonzistentní s produkční hrou.
*  Ve složce <strong>maps</strong> je testovací mapa s minami, na které můžeš testovat svůj kód. Definici mapy můžeš libovolně měnit, aby sis vyzkoušel svůj algoritmus na jiných velikostech a rozmístění min.
3. Napiš svůj algoritmus do metody <strong>dispatchTrainPaths()</strong> ve třídě <strong>ImplementMePlayer.java</strong>.
* Balíček třídy musí zůstat
                    <pre class="prettyprint">package eu.greyson.trains</pre>
* Definice třídy musí zůstat
                    <pre class="prettyprint">public class ImplementMePlayer extends Dispatcher</pre>
* Konstruktor třídy musí zůstat
                    <pre class="prettyprint">public ImplementMePlayer(Game game) { super(game); }</pre>
* Metoda musí vyhazovat vyjímku TimeoutException - nepotlačuj jí
                    <pre class="prettyprint">public void dispatchTrainPaths() throws TimeoutException</pre>
* Do metody <strong>dispatchTrainPaths()</strong> můžeš psát libovolný java code potřebný pro ideální jízdní řád.

* Některé java konstrukce, které bys k nalezení min určitě neměl potřebovat jsou však zakázané Některé java konstrukce jsou zakázané (např. čtení ze souboru, otevírání socketů, java reflections apod.).

* V rámci metody <strong>dispatchTrainPaths()</strong> máš k dispozici objekt
                    <pre class="prettyprint">eu.greyson.trains.game.objects.Game </pre> , který obsahuje celou hru:
                    
                    
                           /**
                                * IMPLEMENTUJ TUTO METODU
                                *
                                * Cílem hry je naplánovat jízdní řády vlakům tak, aby byla zadaná ekonomika co nejvýkonější.
                                * Prodejem surovin je spočítáno skóre.
                                *
                                * Suroviny vznikají v dolech (Mine), zpracovávají se v továrnách (Factory) a jsou prodávány v obchodech (Shop).
                                * Každá surovina má svoji cenu za kterou je prodána.
                                *
                                * Spusť "Launcher" na ověření svého algoritmu a získání skóre. Můžeš si vybrat mezi GUI a konzolovým výstupem.
                                *
                                * JAK HRÁT:
                                * Pro jednotlivé vlaky sestav jízdní řád.
                                * Každý vlak má metodu addStation() která do jízdního řádu přidá zastávku.
                                * Vlak navštěvuje stanice v cyklu, neboli na konci jízdního řádu pokračuje opět od začátku.
                                *
                                * Stanice jsou dvou typů:
                                *  * Nakládací - vlak naloží zvolenou surovinu až do své maximální kapacity
                                *  * Vykládací - vlak kompletně vyloží svůj náklad
                                *
                                * GAME INFO:
                                * game.getMaxX() a game.getMaxY() vrací maximální rozměry hracího pole
                                * game.getTotalTicks() hra má omezený herní čas, vrací počet jednotek času
                                * game.getMines() Vrátí všechny doly na mapě
                                * game.getFactories() Vrátí všechny továrny na mapě
                                * game.getShops() Vrátí všechny obchody na mapě
                                * game.getTrains() Vrátí všechny vlaky na mapě
                                *
                                * Každý herní objekt má vlastní informace (poloha, produkce, atd.), vyzkoušej jeho viditelné metody.
                                *
                                * Výjimku TimeoutException nijak nepotlačuj.
                                */                    


4. Vyzkoušej svůj algoritmus tím, že spustíš <strong>Launcher.java</strong>

5. Jakmile seš spokojený se svým algoritmem, odevzdej ho na stránce <a href="https://devel.greyson.eu/soutez/run">Upload</a>. Odevzdává se pouze soubor ImplementMePlayer.java . Po uploadu tvůj algoritmus okamžitě vyhodnotíme a zobrazíme dosažené skóre.

6. Pokud nejsi spokojený se svým výsledkem, můžeš se pokusit algoritmus vylepšit a dostat se na přední příčky. Počet odevzdaných algoritmů není nijak limitován. Soutěž končí <strong>26.5.2019 v 23:59.</strong>

7. Výherce bude kontaktován emailem, který uvedl při nahrávání algoritmu.

