package net.speedstor.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

import net.speedstor.server.Server;

public class Cmd implements Runnable{
	BufferedReader in;
	Main main;
	Log log;
	Server server;

	public Cmd(Main main, Log log, Server server) {
		in = new BufferedReader(new InputStreamReader(System.in));
		this.main = main;
		this.server = server;
	}
	
	public void run() {
		outerloop:
		while(true) {
			try {
				if(in.ready()) {
					String[] commandLine = in.readLine().split("\\s+");
					switch(commandLine[0]) {
					case "":
						break;
					case "stop":
					case "quit":
					case "exit":
						break outerloop;
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		main.stop();
	}

}
