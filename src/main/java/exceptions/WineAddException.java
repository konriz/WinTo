package exceptions;

import lombok.Getter;

@Getter
public class WineAddException extends Exception{

    private WineAddAlert alert;

    public WineAddException()
    {
        this.alert = new WineAddAlert();
    }

}
