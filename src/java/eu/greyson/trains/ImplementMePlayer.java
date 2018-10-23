package eu.greyson.trains;// neměň

import eu.greyson.trains.game.exceptions.TimeoutException;
import eu.greyson.trains.game.objects.Dispatcher;
import eu.greyson.trains.game.objects.Game;

public class ImplementMePlayer extends Dispatcher { // neměň

    public ImplementMePlayer(Game game) { // neměň
        super(game);
    }

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
    @Override
    public void dispatchTrainPaths() throws TimeoutException {
        //TODO implementuj
    }
}
