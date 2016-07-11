import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Podcast 
{
	public Subscriber[] database = new Subscriber[50];
	public int mastercount=0;
	
	public static void main(String[] args) 
	{
		Podcast obj = new Podcast();
		obj.inputFile();
  	}
	public void inputFile() 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the input file");
		String csvFile = in.next();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		System.out.println("SUCCESS");
		try 
		{
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) 
			{
				String[] inputLine = line.split(cvsSplitBy);
				inputLine[0]=inputLine[0].toLowerCase();
				//System.out.println(inputLine[0]);
				//System.out.println(inputLine[1]);
				if (inputLine[0].equals("subscribe")) 
				{
					//System.out.println("Yay");
					if(mastercount==0)
					{
						//System.out.println("yay2");
						Subscriber newSubscriber = new Subscriber(inputLine[1].toLowerCase());
						newSubscriber.addSubscription(inputLine[2].toLowerCase());
						//newSubscriber.count++;
						database[mastercount]=newSubscriber;
						mastercount++;
					}
					else 
					{
						boolean status = false;
						for (int i=0;i<mastercount;i++) 
						{
							if ( (inputLine[1].toLowerCase()).equals(database[i].name) ) 
							{
								database[i].addSubscription(inputLine[2].toLowerCase());
								//database[i].count++;
								status=true;
							}
						}
						if (!status) 
						{
							//System.out.println("yay9");
							Subscriber newSubscriber = new Subscriber(inputLine[1].toLowerCase());
							newSubscriber.addSubscription(inputLine[2].toLowerCase());
							//newSubscriber.count++;
							database[mastercount]=newSubscriber;
							mastercount++;
						}
					}
				}
				if (inputLine[0].equals("publish")) 
				{
					//System.out.println("final");
					//System.out.println(database[1].subscriptions[0]);
					//System.out.println(mastercount);
					Publisher newPublish = new Publisher(inputLine[1].toLowerCase(), inputLine[2].toLowerCase(), inputLine[3].toLowerCase());
					Notify notifyUsers = new Notify(newPublish);
					notifyUsers.AlertAll(database,mastercount);
				}
				if (inputLine[0].equals("unsubscribe")) 
				{
					Unsubscribe unsub = new Unsubscribe(inputLine[1].toLowerCase(), inputLine[2].toLowerCase());
					unsub.deleteSubscription(database,mastercount);
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (br != null) 
			{
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
class Subscriber extends Podcast
{
	int count = 0;
	String name="";
	String[] subscriptions = new String[50];
	public Subscriber(String name1)
	{
		name=name1;
	}
	public void addSubscription(String category)
	{
		subscriptions[count] = category;
		count ++;
	}
}
class Publisher extends Podcast
{
	String station ="";
	String category="";
	String episode ="";
	public Publisher(String station1,String category1, String episode1)
	{
		station=station1;
		category=category1;
		episode=episode1;
	}
}
class Notify extends Podcast
{
	Publisher newlyPublished;
	public Notify(Publisher newlyPublished1)
	{
		newlyPublished=newlyPublished1;
	}
	public void AlertAll(Subscriber database1[],int mastercount1)
	{
		//System.out.println("ZONE");
		//System.out.println(mastercount1);
		//System.out.println(database1[0].name);
		//System.out.println("ZONE");
		for (int i=0;i<mastercount1;i++) 
		{
			//System.out.println(i);
			//System.out.println(newlyPublished.station);
			//System.out.println(database1[0].subscriptions[0]);
			for (int j=0;j<database1[i].count;j++) 
			{
				if ( (newlyPublished.category).equals(database1[i].subscriptions[j]) ) 
				{
					System.out.println(database1[i].name + " notified of " +newlyPublished.episode +" from "+newlyPublished.station);
				}
			}
		}
	}
}
class Unsubscribe extends Podcast
{
	String subName = "";
	String subCategory="";
	boolean found = false;
	public Unsubscribe(String givenname, String givencategory)
	{
		subName=givenname;
		subCategory=givencategory;
	}
	public void deleteSubscription(Subscriber database1[],int mastercount1)
	{
		for (int i=0;i<mastercount1;i++) 
		{
			for (int j=0;j<database1[i].count;j++) 
			{
				if ( ((database1[i].name).equals(subName)) && ((database1[i].subscriptions[j]).equals(subCategory)) ) 
				{
					found=true;
					for(int k =j;k<49;k++)
					{
						database1[i].subscriptions[k]=database1[i].subscriptions[k+1];
					}
				}
			}
		}
	}
} 