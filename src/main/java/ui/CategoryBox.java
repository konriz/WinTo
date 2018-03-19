package ui;

import Entities.Categories;
import javafx.scene.control.ComboBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryBox extends ComboBox<String>{

    private Categories boxType;

    public CategoryBox()
    {
        super();
    }


}
