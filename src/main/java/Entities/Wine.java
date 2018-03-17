package Entities;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

public class Wine {

    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty brand = new SimpleStringProperty();
    private final SimpleStringProperty grapes = new SimpleStringProperty();
    private final SimpleStringProperty country = new SimpleStringProperty();
    private final SimpleStringProperty taste = new SimpleStringProperty();
    private final SimpleStringProperty colour = new SimpleStringProperty();
    private final SimpleStringProperty year = new SimpleStringProperty();
    private final SimpleStringProperty alcohol = new SimpleStringProperty();
    private final SimpleStringProperty volume = new SimpleStringProperty();


    public Wine(String name, String brand)
    {
        setName(name);
        setBrand(brand);
    }

    public Wine(String name, String brand, String colour, String taste)
    {
        setName(name);
        setBrand(brand);
        setColour(colour);
        setTaste(taste);
    }


    public void setName(String name)
    {
        this.name.set(name);
    }

    public void setBrand(String brand)
    {
        this.brand.set(brand);
    }

    public void setGrapes(String grapes)
    {
        this.grapes.set(grapes);
    }

    public void setCountry(String country)
    {
        this.country.set(country);
    }

    public void setTaste(String taste)
    {
        this.taste.set(taste);
    }

    public void setColour(String colour)
    {
        this.colour.set(colour);
    }

    public void setYear(String year)
    {
        this.year.set(year);
    }

    public void setAlcohol(String alcohol)
    {
        this.alcohol.set(alcohol);
    }

    public void setVolume(String volume)
    {
        this.volume.set(volume);
    }

    public String getName()
    {
        return this.name.get();
    }

    public String getBrand()
    {
        return this.brand.get();
    }

    public String getGrapes()
    {
        return this.grapes.get();
    }

    public String getCountry()
    {
        return this.country.get();
    }

    public String getTaste()
    {
        return this.taste.get();
    }

    public String getColour()
    {
        return this.colour.get();
    }

    public String getYear()
    {
        return this.year.get();
    }

    public String getVolume()
    {
        return this.volume.get();
    }

    public String getAlcohol()
    {
        return this.alcohol.get();
    }



    @Override
    public String toString() {

        StringBuilder description = new StringBuilder();
        description.append(getBrand() + " : ");
        description.append(getName() + " - ");
        description.append(getColour() + ", ");
        description.append(getTaste() + " from " + getCountry());

        return description.toString();
    }
}
