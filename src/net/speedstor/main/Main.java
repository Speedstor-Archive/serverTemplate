package net.speedstor.main;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import net.speedstor.server.Server;

public class Main {
	public static Main main;
	private Cmd cmd;
	private Log log;
	private Server server;
	private Thread serverThread;
	private Thread cmdThread;
	Clock clock;
	
	Instant serverStartTime;
	
	public static void main(String args[]) {
		main = new Main();
		main.start();
	}
	
	public void start() {
		clock = Clock.systemDefaultZone();
		double timeStart = clock.millis();
		serverStartTime = clock.instant();
		
		
		//log
		log = new Log(clock);	

		//server
		int port = 40;
		server = new Server(log, clock, port, timeStart);
		serverThread = new Thread(server);
		serverThread.start();

		cmd = new Cmd(main, log, server);
		cmdThread = new Thread(cmd);
		cmdThread.start();

		log.log("Started @" + (clock.millis() - timeStart) + "ms");
	}
	
	public void stop() {
		((Server) server).setRunning(false);
		log.logStop("Stopping Server...");
		while(((Server) server).ifRunning()) {
			
		}
		long runTimeSecond = Duration.between(serverStartTime, clock.instant()).getSeconds();
		int runTimeMinute = (int) runTimeSecond / 60;
		int runTimeMinSec = (int) runTimeSecond % 60;
		log.logStop("Server stopped; run time: @"+runTimeMinute+"m"+runTimeMinSec+"s");
		
		log.programStop();
		System.exit(0);
	}
}
