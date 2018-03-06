import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Grapes implements Category{

    private Integer grapesID;
    private String grapes;

    public String getName()
    {
        return this.grapes;
    }

}
