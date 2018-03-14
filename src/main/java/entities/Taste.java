package Entities;

import entities.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Taste implements Category {
    private Integer tasteID;
    private String taste;

    @Override
    public String getName()
    {
        return this.taste;
    }
}
