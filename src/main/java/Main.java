

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author juena
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean settingCity = false;
        Settings setting = new Settings();
        City myCity = new City();
        settingCity = setting.setToOf_XML(myCity);

        if (settingCity) {
            Wetterstation wetterstation01 = new Wetterstation("https://andfun-weather.udacity.com/weather", myCity);
            if (wetterstation01.parseUpToDate()) {
                System.out.println(wetterstation01);
            } else {
                System.out.println("Fehler beim Parsen");
            }
        }

        String[][] kunden = CSVParser.parse(3, ",", "http://www.globalbee.at/Andreas/POS1_STOLZ/kunden.csv");
        
        if(kunden != null)
        {
            for (int i = 0; i < kunden.length; i++) {
            for (int e = 0; e < kunden[i].length; e++) {
                   System.out.print(kunden[i][e] + " ");
            }
            
            System.out.println();
        }
        }

    }
}
