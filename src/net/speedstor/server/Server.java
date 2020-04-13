package net.speedstor.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Clock;

import net.speedstor.main.Log;

public class Server implements Runnable{
	Log log;
	public boolean running = false;
	int port;

	ServerSocket serverSocket;

	Clock clock;
	double startTime;
	
	public Server(Log log, Clock clock, int port, double startTime) {
		this.log = log;
		this.port = port;
		this.clock = clock;
		this.startTime = startTime;
		running = true;
		
	}

	@Override
	public void run() {
	    try {
			serverSocket = new ServerSocket(port);
			log.log("Open on port: ("+port+")");
		} catch (IOException e1) {
			log.error("port: "+port+" taken, try another one");
			return;
			//debug
			//e1.printStackTrace();
		}
	    log.log("Server started: "+(clock.millis() - startTime)+"ms");

	    
		while(running) {
			//Receive requests
	        try {
	        	Socket client = serverSocket.accept();
	        	
	        	//move to a new thread for dealing with request
	        	//TODO: the server, mostly a thread to deal with the request
	        	
	        }catch ( java.io.InterruptedIOException e ) {
	        	//timeout socket
	        	log.error("Server accept socket timed out");
	        } catch (IOException e) {
	        	log.error("Error with receiving requests");
	        	e.printStackTrace();
			}
		}
	}
	
	public boolean ifRunning() {
		return running;
	}
	public int setRunning(Boolean value) {
		running = value;
		return 1;
	}

}
