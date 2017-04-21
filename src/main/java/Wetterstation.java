
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juena
 */
public class Wetterstation {
    private MyRESTClient myRESTClient;
    City city;
    String nowRequest;
    String url;
    boolean fehlerJSON = false;
    ArrayList<WetterdatenEinheit> wetterdatenEinheiten;
    
    private final String methode = "GET";
    private final String contentType = "application/json";
    
    public Wetterstation(String url, City city){
         myRESTClient = new MyRESTClient();
         this.wetterdatenEinheiten = new ArrayList<WetterdatenEinheit>();
         this.city = city;
         setUrl(url);
    }

    public City getCity() {
        return city;
    }

    public ArrayList<WetterdatenEinheit> getWetterdatenEinheiten() {
        return wetterdatenEinheiten;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public boolean update()
    {
        try {
            nowRequest = myRESTClient.getRequest("https://andfun-weather.udacity.com/weather", "GET", "application/json");
        } catch (MalformedURLException e) {
            fehlerJSON = true;
            e.printStackTrace();
        } catch (IOException e) {
            fehlerJSON = true;
            e.printStackTrace();
        } catch (RuntimeException e) {
            fehlerJSON = true;
            e.printStackTrace();
        }
        
        return fehlerJSON;
    }
    
    public boolean parseUpToDate()
    {
        nowRequest = "";
        if(!update() && nowRequest != "")
        {
            wetterdatenEinheiten.clear();
            JSONObject obj = new JSONObject(nowRequest);
            JSONObject objCity = obj.getJSONObject("city");
            
            if(!city.isEqual(objCity.getInt("id"), objCity.getString("name"), objCity.getString("country")))
            {
                return false;
            }
            
            JSONArray list = obj.getJSONArray("list");
            
            Iterator itList = list.iterator();
            while(itList.hasNext())
            {
                Object objList = itList.next();
                if(objList instanceof JSONObject)
                {
                    JSONObject objListZW = (JSONObject)objList;
                    int date = objListZW.getInt("dt");
                    double pressure = objListZW.getDouble("pressure");
                    double humidity = objListZW.getDouble("humidity");
                    
                    JSONObject objTemp = objListZW.getJSONObject("temp");
                    Temp tempZW = new Temp(objTemp.getDouble("day"), objTemp.getDouble("min"), objTemp.getDouble("max"), objTemp.getDouble("night"), objTemp.getDouble("eve"), objTemp.getDouble("morn"));
                    
                    wetterdatenEinheiten.add(new WetterdatenEinheit(date, pressure, humidity, tempZW));  
                }
                else
                    return false;
            }
        }
        else
            return false;
        
        return true;
    }

    @Override
    public String toString() {
        String output = "";
        for(WetterdatenEinheit wetterdatenEinheitObj : wetterdatenEinheiten)
        {
            output += "[";
            output += wetterdatenEinheitObj.toString();
            output += "]";   
        }

        return String.format("city: [%s], Daten: [%s]", city, output); 
    }   
 }
