/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juena
 */
public class City {
    int id;
    String name;
    String country;
   
    public City()
    {
        
    }
     
    public City(int id, String name, String country)
    {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public boolean isEqual(int id, String name, String country)
    {
        if(this.id == id && this.name.equals(name) && this.country.equals(country))
            return true;
        
        return false;
    }

    @Override
    public String toString() {
        return String.format("id: %d, name: %s, country: %s", id, name, country);
    }
    
    
    
    
}
