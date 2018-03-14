package Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wine {

    private String name, year, alcohol, volume;
    private String grapes;
    private String brand;
    private String country;
    private String taste;
    private String colour;

    private Wine(WineBuilder builder)
    {
        this.name = builder.name;
        this.year = builder.year;
        this.alcohol = builder.alcohol;
        this.volume = builder.volume;
        this.grapes = builder.grapes;
        this.brand = builder.brand;
        this.country = builder.country;
        this.taste = builder.taste;
        this.colour = builder.colour;

    }

    public static class WineBuilder
    {
        private final String name;
        private final String brand;
        private String year, alcohol, volume;
        private String grapes;
        private String country;
        private String taste;
        private String colour;

        public WineBuilder(String name, String brand)
        {
            this.name = name;
            this.brand = brand;
        }

        public Wine build()
        {
            return new Wine(this);
        }

        public WineBuilder year(String year)
        {
            this.year = year;
            return this;
        }

        public WineBuilder alcohol(String alcohol)
        {
            this.alcohol = alcohol;
            return this;
        }

        public WineBuilder volume(String volume)
        {
            this.volume = volume;
            return this;
        }

        public WineBuilder grapes(String grapes)
        {
            this.grapes = grapes;
            return this;
        }

        public WineBuilder country(String country)
        {
            this.country = country;
            return this;
        }

        public WineBuilder taste(String taste)
        {
            this.taste = taste;
            return this;
        }

        public WineBuilder colour(String colour)
        {
            this.colour = colour;
            return this;
        }


    }

    @Override
    public String toString() {

        StringBuilder description = new StringBuilder();
        description.append(this.brand + " : ");
        description.append(this.name + " - ");
        description.append(this.colour + ", ");
        description.append(this.taste + " from " + this.country);

        return description.toString();
    }
}
