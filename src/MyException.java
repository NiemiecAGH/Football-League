public class MyException extends Exception 
{
    String komunikat;
    
    public MyException() {}
    public MyException(String wiad) {komunikat = "" + wiad;}
    
    public String kom() {return komunikat;}
}