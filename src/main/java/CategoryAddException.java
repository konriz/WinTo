import lombok.Getter;

@Getter
public class CategoryAddException extends Exception {

    private CategoryAlert alert;

    public CategoryAddException(Entities.Categories category)
    {
        this.alert = new CategoryAlert(category);
    }


}
