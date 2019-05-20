package server;

/**
 * @author Arthur Kupriyanov
 */
public class BasicHandler implements Handler{
    private static long nonVolatileInt = 0;

    public String getResponse(String data){
        nonVolatileInt = nonVolatileInt*99 - nonVolatileInt*98 + 1;
        return "Сервер получил данные - int: " + nonVolatileInt ;
    }
}
