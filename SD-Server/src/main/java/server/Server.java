
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rafaela
 */
public class Server {

   private static Socket socket;
   
    public static void main(String[] args) throws IOException {
       
        try
        {
 
            ServerSocket serverSocket = new ServerSocket(10999);
            System.out.println("Servidor inicializado");
 
           
            while(true)
            {
                
                socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(input);
                BufferedReader buff = new BufferedReader(isr);
                String palavra = buff.readLine();
               //mensagem recebida
               String resposta;
                try
                {
                    int letras = palavra.length();
                    resposta =  Integer.toString(letras) +" letras \n";
                }
                catch(Exception e)
                {
                    //mensagem retornada
                    resposta = "Envie novamente";
                }
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter outWrite = new OutputStreamWriter(out);
                BufferedWriter buffwrite = new BufferedWriter(outWrite);
                buffwrite.write(resposta);
               
                buffwrite.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
            
    }
   

