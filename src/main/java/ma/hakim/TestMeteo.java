package ma.hakim;

import ma.hakim.outil.meteo.MeteoTool;

public class TestMeteo {

    public static void main(String[] args) {
        MeteoTool meteo = new MeteoTool();
        double latitude = 48.85;
        double longitude = 2.35;

        System.out.println("Paris, aujourd'hui (1 jour) : " + meteo.isPluiePrevue(latitude, longitude, 0, 1));
        System.out.println("Paris, demain (1 jour) : " + meteo.isPluiePrevue(latitude, longitude, 1, 1));
        System.out.println("Paris, après-demain (1 jour) : " + meteo.isPluiePrevue(latitude, longitude, 2, 1));
        System.out.println("Paris, 3 jours à partir d'aujourd'hui : " + meteo.isPluiePrevue(latitude, longitude, 0, 3));
    }
}
