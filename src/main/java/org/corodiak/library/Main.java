package org.corodiak.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	List<Item> itemList = new ArrayList<Item>();
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.run(args);
	}
	
	public void run(String[] args)
	{
		read();
		write();
	}
	
	public void read()
	{
		try (Scanner scanner = new Scanner(System.in);)
		{
			while(true)
			{
				String line = scanner.nextLine();
				if(line.equals("0"))
				{
					break;
				}
				
				String [] data = line.split(" ");
				
				Item item = new Item();
				item.setCode(data[0]);
				item.setName(data[1]);
				item.setPrice(Integer.parseInt(data[2]));
				
				itemList.add(item);
			}
		}
	}
	
	public void write()
	{
		int idx = 0;
		for(Item item:itemList)
		{
			System.out.println(String.format("%2d) %s", ++idx, item.toString()));
		}
	}
	
}
