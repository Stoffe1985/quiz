package com.example.christofferwiregren.quiz;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by christofferwiregren on 2017-11-02.
 */

public class GameWords {

    public Map<String, Card> card() {

        Map<String, Card> word = new HashMap<String, Card>();


        word.put("1", new Card("2000-talet", "När blev det möjligt att ingå kyrklig samkönad vigsel i Sverige enligt ett beslut i Kyrkomötet?", "1 november 2009", "1 juli 2010", "1 maj 2010", "1 maj 2009", 1));
        word.put("2", new Card("2000-talet", "Det blev mycket skriverier när Stockholms stads Kulturella hederspris delades ut 2012. Vem eller vilka fick priset?", "Kent", "Swedish House Mafia", "Avicii", "Rebecca & Fiona", 2));
        word.put("3", new Card("Musik & hits", "Från vilken ort kommer Streaplers, som är ett av Sveriges äldsta aktiva dansband?", "Karlskoga", "Kungälv", "Alingsås", "Karlstad", 2));
        word.put("4", new Card("Musik & hits", "Vilkén stad kom bandet The Searchers från?", "Leeds", "Manchester", "Liverpool", "London", 3));
        word.put("5", new Card("Musik & hits", "Hur börjar Barry Manilows klassiska discolåt Copacabana?", "Mama,just killed a man", "Her name was Lola", "I will try not cry", "Happy days are here again", 2));
        word.put("6", new Card("Mat & dryck", "Vilken av följande ingredienser är INTE typisk för curryblandningar?", "Ingefära", "Kanel", "Vitpeppar", "Gurkmeja", 3));
        word.put("7", new Card("Mat & dryck", "Vad ingår ursprungligen INTE i en hollandaisesås", "Vinäger", "Citron", "Smör", "Äggula", 1));
        word.put("8", new Card("Mat & dryck", "Vilken drink görs på mörk rom, med pressad lime och ginger ale?", "Cuba Libre", "Mojito", "Che Guevara", "Fidel Castro", 4));
        word.put("9", new Card("TV-serier", "Hur många säsonger om de populärara hemmafruarna i Desperate Housewives har spelats in?", "8", "10", "6", "11", 1));
        word.put("10", new Card("TV-serier", "Vad heter Kenny i South Park i efternamn?", "Broflovski", "McCormick", "Cartman", "Marsh", 2));
        word.put("11", new Card("TV-serier", "Utanför vilken stad utspelade sig Falcon Crest?", "Chicago", "New York", "Los Angeles", "San Francisco", 4));
        word.put("12", new Card("Djur & natur", "Vad är en smörbult?", "En fisk", "En blomma", "En svamp", "En typ av vass", 1));
        word.put("13", new Card("Djur & natur", "Vad betalas till Länsstyrelsen för varje älg i landet som fälls under älgjakt?", "Decimeringsavgift", "Jaktavgift", "Kvotavgift", "Fällavgift", 4));
        word.put("14", new Card("Djur & natur", "Hur brett vingspann har kattugglan?", "Ca 90 cm", "Ca 120 cm", "Ca 40 cm", "Ca 150 cm", 1));
        word.put("15", new Card("Teknikens under", "Vilket batterimärke har en rosa kanin som maskot?", "Euroshopper", "Varta", "Duracell", "Tudor", 3));
        word.put("16", new Card("Teknikens under", "Vad är ett piktogram?", "En bildkarta", "En förenklad illustration", "En måttenhet", "Ett bildformat", 2));
        word.put("17", new Card("Teknikens under", "Vad var UNIVAC I?", "Ett flygplan", "En dator", "En dammsugare", "En rymdfarkost", 2));
        word.put("18", new Card("Böcker & ord", "Vilket påstående om den amerikanske 1800-talsförfattaren Mark Twain stämmer?", "Han skrev Oliver Twist", "Han levde hela livet i Alaska", "Han var antiimperialist", "Han var positiv till slaveriet", 3));
        word.put("19", new Card("Böcker & ord", "Vad leder till att Astrid Lindgrens Madicken får hjärnskakning i en av böckerna?", "Hon åker skridskor", "Hon flygge med ett paraply", "Hon slåss med Lus-Mia", "Hon dansar på ett halt golv", 2));
        word.put("20", new Card("Oktoberkampen", "Sverige kom tvåa i sin kvalgrupp inför VM i herrfotboll, 2018. Vilket lag vann Sveriges grupp?", "Frankrike", "Ungern", "Holland", "Italien", 1));
        word.put("21", new Card("Oktoberkampen", "Säsong 2 av serien Stranger things har premiär. Vad heter den kvinnliga huvudkaraktären?", "One", "Seven", "Five", "Eleven", 4));
        word.put("22", new Card("Oktoberkampen", "Sveriges försvar utreds för att ha brutit mot lagen när de kopt något för 12 miljoner kronor. Vad då?", "Hundar", "Landminor", "Baskrar", "Granatkastare", 1));
        word.put("23", new Card("Oktoberkampen", "I vilken svensk stad sprängdes delar av polishuset sönder av en bomb i mitten av oktober?", "Trelleborg", "Göteborg", "Malmö", "Helsingborg", 4));


        return word;
    }

}
