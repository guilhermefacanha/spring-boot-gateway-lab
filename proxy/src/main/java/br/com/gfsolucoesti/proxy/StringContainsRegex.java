package br.com.gfsolucoesti.proxy;
public class StringContainsRegex {
 
    public static void main(String[] args) {
        
        String str = "http://localhost:8000/app/index.html";
        System.out.println( str.matches(".*/app/.*"));
        
        str = "http://localhost:8000/app2/index.html"; 
        System.out.println( str.matches(".*/app/.*"));
    }
}
