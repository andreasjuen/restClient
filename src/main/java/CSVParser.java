
import java.io.IOException;
import java.net.MalformedURLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juena
 */
public class CSVParser {
     private final static String methode = "GET";
     private final static String contentType = "text.csv";
     private final static  MyRESTClient myRESTClient = new MyRESTClient();
    
     public static String[][] parse(int anzahlSpalten, String cvsSplitBy, String url)
     {
        String bufferCSV = null;
        try {
            bufferCSV = myRESTClient.getRequest(url, methode, contentType);
        } catch (MalformedURLException e) {            
            e.printStackTrace();
            return null;
        } catch (IOException e) {           
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {            
            e.printStackTrace();
            return null;
        }
        
        bufferCSV = bufferCSV.replace('\n', ',');
        String[] arrayCSV = bufferCSV.split(cvsSplitBy);
        String[][] csvCorrekt = new String[(arrayCSV.length / anzahlSpalten) - 1][anzahlSpalten];
        
        int zaehler = anzahlSpalten;
        for(int i = 0; i < (arrayCSV.length / anzahlSpalten) -1; i++)
        {
            for(int e = 0; e < anzahlSpalten; e++)
            {

                    csvCorrekt[i][e] = arrayCSV[zaehler].trim();
                    
                zaehler++;
            }  
        }
        return csvCorrekt;
     }   
}
