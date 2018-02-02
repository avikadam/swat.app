package org.sits.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
	private Socket s;
	private DataInputStream in;
	private DataOutputStream out;
	public int ID;

	public Client(final int ID, final String host, final int port) {
		/* Attempting to connect */
		try {
			System.out.println("Connecting to host: " + host + " on " + port);
			s = new Socket(host, port);
			if (s.isConnected()) {
				/*
				 * If is connected, will open the streams and sending the
				 * welcome message to the server.
				 */ open();
				System.out.println("Connecting to host: " + host + " on " + port);
				send("HELLO SERVER");
			}
		} catch (IOException e) {
			/* Connect problem.. */ System.out.println("Can't connect to host. " + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				/* Reading the server messages. */ String message = in.readUTF();
				System.out.println("Message from server: " + message);
			}
		} catch (IOException e) {
			/* Reading problems, disconnect. */ close();
			System.out.println("Disconnected. " + e.getMessage());
		}
	}

	public void open() throws IOException {
		/* Opening IO streams, after socket is connect. */ in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
	}

	public void close() {
		/* Closing the client streams. */
		try {
			out.close();
			in.close();
			s.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/* Sending message to the server. */
	public void send(String msg) throws IOException {
		out.writeUTF(msg);
	}
}
