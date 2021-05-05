package lvc.cds;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Scanner;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/app", new MyHandler());
        server.setExecutor(null); 
        server.start();
    }

    static class MyHandler implements HttpHandler {
        
        @Override
        public void handle(HttpExchange t) throws IOException {
            
            URI uri = t.getRequestURI();
            String s = uri.toString();
            String fileName = s.substring(s.indexOf('/', 2) + 1, s.length());
            String content = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
            t.sendResponseHeaders(200, content.length());
            OutputStream os = t.getResponseBody();
            os.write(content.getBytes());
            os.close();
            
        }


    }
}