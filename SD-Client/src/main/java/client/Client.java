/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author rafaela
 */
public class Client {

    private static Socket socket;

    public static void main(String[] args) throws UnknownHostException, IOException {

        //Caso fosse utilizado as primitivas do protocolo request-reply esse seria o comportamento do metodo doOperation
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            socket = new Socket(inetAddress, 10999);
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter outWrite = new OutputStreamWriter(out);
            BufferedWriter buffwrite = new BufferedWriter(outWrite);
            //enviando requisição
            String mensagem = "letra maiuscula";
            String mensagemEnviada = mensagem + "\n";
            buffwrite.write(mensagemEnviada);
            buffwrite.flush();
            //recebendo resposta do servidor
            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader buff = new BufferedReader(reader);
            String resposta = buff.readLine();
            System.out.println(resposta);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
