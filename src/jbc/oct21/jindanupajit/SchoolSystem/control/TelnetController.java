package jbc.oct21.jindanupajit.SchoolSystem.control;

// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import jbc.oct21.jindanupajit.SchoolSystem.view.Terminal;

import java.io.*;
import java.text.*;
import java.net.*;

// Server class
public class TelnetController implements Runnable
{
    public void run() {
        // server is listening on port 5056
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8823);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket socket = null;

            try
            {
                // socket object to receive incoming client requests
                socket = serverSocket.accept();

                //System.out.println("A new client is connected : " + socket);

                // obtaining input and out streams
                InputStream inputStream = new DataInputStream(socket.getInputStream());
                OutputStream outputStream = new DataOutputStream(socket.getOutputStream());

                //System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(socket, inputStream, outputStream);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}

// ClientHandler class
class ClientHandler extends Thread
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final InputStream inputStream;
    final OutputStream outputStream;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, InputStream inputStream, OutputStream outputStream)
    {
        this.s = s;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void run()
    {
        TerminalController telnetTerminal = new TerminalController(new Terminal(outputStream, inputStream ));
        telnetTerminal.run();

        try
        {
            // closing resources
            if (inputStream != System.in)
               this.inputStream.close();

            if (outputStream != System.out)
                this.outputStream.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

