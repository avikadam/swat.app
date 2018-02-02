package org.sits.server;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.sits.client.Client;

public class Server extends Thread {

	/* Server port, clients list <ArrayList> */

	private final int port = 9999;

	private ServerSocket ss;

	private List<Client> clients = new CopyOnWriteArrayList<>();

	private static AtomicInteger ID = new AtomicInteger(0);

	public Server() {
		try {

			/* Starting server on host and port */

			System.out.println("\nStarting server.. on port: " + port);
			ss = new ServerSocket(port, 1000, Inet4Address.getLocalHost());
			System.out.println("\nServer started..");

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				/* Here is waiting for the clients */
				System.out.println("\nWaiting for a client...");
				addClient(); /* Accepting the clients which wants to connect. */
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public synchronized void handle(int ID, String input) throws IOException {

		/* Reading the client messages */

		System.out.println("\nMessage from client: " + input);

		/*
		 * If the client message is equals with HELLO SERVER, the server will
		 * responds with HELLO CLIENT.
		 */

		if (input.equals("HELLO SERVER")) {
			clients.get(findClient(ID)).send("HELLO CLIENT!");
		}
	}

	public void addClient() throws IOException {
		Client client = new Client(ID.incrementAndGet(), "100.101.101.1", port);
		client.open(); /* opening client streams */
		client.start();
		clients.add(client); /* Addig client to the clients list <ArrayList> */
		System.out.println("\nClient accepted.");
		client.send(">> WELCOME CLIENT! ");

	}

	/* Removing the client */
	public void removeClient(int ID) {
		clients.remove(findClient(ID));
		System.out.println("\nA Client was removed");
	}

	/* Finding client position in the clients list */
	public int findClient(int ID) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).ID == ID) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		new Server().start();
		new Client(ID.incrementAndGet(), "100.101.101.1", 9999).start();
	}
}
