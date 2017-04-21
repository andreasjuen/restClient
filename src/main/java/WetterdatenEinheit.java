/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juena
 */
public class WetterdatenEinheit {
    private int date;
    private double pressure;
    private double humidity;
    private Temp temp;

    public WetterdatenEinheit(int date, double pressure, double humidity, Temp temp) {
        this.date = date;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp = temp;
    }

    public int getDate() {
        return date;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public Temp getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return String.format("Date: %d, Pressure: %f, Humidity: %f, Temp: [%s]", date, pressure, humidity, temp);
    }  
}
