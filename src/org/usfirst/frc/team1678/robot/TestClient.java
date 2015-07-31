package org.usfirst.frc.team1678.robot;

import java.io.*;
import java.net.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestClient implements Runnable{
	public double []data;
	Thread t;
	public double[] getData(){return data;}
	@Override
	public void run() {
		while(true)
		{
			try {
				Socket MyClient = new Socket("10.16.78.5", 80);
				BufferedReader from_server = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
				PrintWriter to_server = new PrintWriter(new OutputStreamWriter(MyClient.getOutputStream()));
				to_server.println("GET APPLES");
				to_server.println("");
				to_server.flush();

				String str = from_server.readLine();
				if (str.equals("Bad apples."))
				{
					MyClient.close();
					throw new Exception("client messed up.");
				}
				if (!str.equals("APPLE SERVER"))
				{
					MyClient.close();
					throw new Exception("server messed up.");
				}
				str = from_server.readLine();
				if (str.equals("Apple not found."))
				{
					double[] zero={0, 0, 0};
					while (from_server.readLine() != null)
						;
					MyClient.close();
					data=zero;
					SmartDashboard.putString("apple error","not found");
					continue;
				}
				if (!str.equals("X error:"))
				{
					MyClient.close();
					throw new Exception("server messed up.");
				}
				double x=Double.parseDouble(from_server.readLine());
				if (!from_server.readLine().equals("Y error:"))
				{
					MyClient.close();
					throw new Exception("server messed up.");
				}
				double y=Double.parseDouble(from_server.readLine());
				double[] retval={x, y, 1};
				while (from_server.readLine() != null)
					;
				MyClient.close();
				data=retval;
				SmartDashboard.putString("apple error","apple found");
				continue;
				
			} catch (IOException e) {
				SmartDashboard.putString("apple error",e.toString());
			} catch (Exception e) {
				SmartDashboard.putString("apple error",e.toString());
			}
			double[] retval={0, 0, -1};
			data=retval;
			continue;
		}
	}
	
	public TestClient()
	{
		data=new double[3];
		t=new Thread(this);
		t.start();
	}
}
