import java.util.Scanner;							//Import Scanner utility
import java.util.Random;							//Import Random utility
import java.io.*;									//Import to work with files.

public class KoT1{								
	public static void main(String[] args) throws IOException{
		Scanner s = new Scanner(System.in);				//Declare Scanner 's'.
		
		/*Declare Variables*/
		int x = 3;										//'x' will control when the program exits or continues to loop.
		int[] buildings = new int[10];					//'buildings[]' will keep track of how many of each type of building has been built.
		int[] resources = new int[10];					//'resources[]' will keep track of how much of each type of resource the kingdom has.
		int[] workers = new int[10];					//'workers[]' will keep track of how many of each type of worker is in the kingdom.
		int[] status = new int[10];						//'status[]' will keep track of miscellaneous kingdom variables.
		int option;										//'option' will be used to determine what decision is made when the user is faced with a choice.
				
		
														//Display user options and prompt for selection.
		System.out.print("What do you want to do?\n1) Start New Game\n2) Load Save Game\n3) Exit\nEnter your selection: ");
		option = Integer.parseInt(s.nextLine());		//Read in user selection to 'option'.
		
		while(x == 3)
		{
			switch(option)
			{
				case 1:
					startNewGame(buildings, workers, resources, status);	//Call 'startNewGame()'.
					x = 1;													//Set 'x' equal to 1, so that the Advisor Menu loop will open.
				break;
				case 2:
					loadSaveGame(buildings, workers, resources, status);	//Call 'loadSaveGame()'.
					x = 1;													//Set 'x' equal to 1, so that the Advisor Menu loop will open.
				break;
				case 3:
					x = 2;													//Set 'x' equal to 2, so that the program will exit.
				break;
				default:
					System.out.print("Error, invalid input.\n");			//Respond to invalid user input.
				break;
			}
		}
		
		while(x == 1)									//Run loop until the user chooses to exit from within.
		{
			/*Display Advisor Menu*/
			System.out.print("\nAdvisor menu:\nWhich advisor would you like to speak to?\n1) Chief Advisor\n2) Economic Advisor\n");
			if(buildings[4] != 0)						//Display Trade Advisor option after a trading post has been built.
			{
				System.out.print("3) Trade Advisor\n");
			}
			System.out.print("4) End turn\n5) Exit Game\n6) Save game\nEnter Selection: ");	//Finish displaying options and prompt for user selection.
			option = Integer.parseInt(s.nextLine());						//Read in user selection to 'option'.
		
			switch(option)
			{
				case 1:
					System.out.print("Chief Advisor coming soon.\n");
				break;
				case 2:
					economicAdvisor(buildings, workers, resources);			//Call 'economicAdvisor()' and give it access to 'buildings[]', 'workers[]', and 'resources[]'.
				break;
				case 3:
					if (buildings[4] == 0)									//If a trading post has not been built, then display error message if 'option' = 3.
					{
						System.out.print("Error, " + option + " is not a valid option right now.");
					}
					else													//If a trading post has been built, then call 'tradeAdvisor()' and give it access to 'resources[]' when 'option' = 3.
					{
						tradeAdvisor(resources);							//Call 'tradeAdvisor()'.
					}
				break;
				case 4:
					endTurn(buildings, workers, resources, status);			//Call 'endTurn()'.
				break;
				case 5:
					System.out.print("\nGood bye!");
					x = 2;													//Set 'x' = 2, so that the Advisor Menu loop will exit.
				break;
				case 6:
					saveGame(buildings, workers, resources, status);		//Call 'saveGame()'.
				break;
				default:													//Respond to invalid user input.
					System.out.print("Error, " + option + " is not a valid option.");
				break;
			}
		}	
	}

	public static void loadSaveGame(int[] buildings, int[] workers, int[] resources, int[] status) throws IOException{

		Scanner fs = new Scanner(new File("saveGame.txt"));	//Declare file Scanner 'fs'.
		int counter;										//'counter' is a counter variable.
		
		System.out.print("\nLoading Save Game\n\n");
		
		for(counter = 0; counter < 4; counter++)	//Read in 'resources[]'.
		{
			resources[counter] = Integer.parseInt(fs.nextLine());
		}
		
		for(counter = 0; counter < 6; counter++)	//Read in 'buildings[]'.
		{
			buildings[counter] = Integer.parseInt(fs.nextLine());
		}
		
		for(counter = 0; counter < 6; counter++)	//Read in 'workers[]'.
		{
			workers[counter] = Integer.parseInt(fs.nextLine());
		}
		
		for(counter = 0; counter < 1; counter++)	//Read in 'status[]'.
		{
			status[counter] = Integer.parseInt(fs.nextLine());
		}
		
		fs.close();									//Close 'fs'.
	}
	
	public static void saveGame(int[] buildings, int[] workers, int[] resources, int[] status) throws IOException{

		PrintWriter pw = new PrintWriter(new FileWriter("saveGame.txt"));	//Declare Print Writer 'pw'.
		
		int counter;								//'counter' is a counter variable.
		
		System.out.print("\nSaving Game\n\n");
		
		for(counter = 0; counter < 4; counter++)	//Record all elements of 'resources[]'.
		{
			pw.println(resources[counter]);
		}
		
		for(counter = 0; counter < 6; counter++)	//Record all elements of 'buildings[]'.
		{
			pw.println(buildings[counter]);
		}
		
		for(counter = 0; counter < 6; counter++)	//Record all elements of 'workers[]'.
		{
			pw.println(workers[counter]);
		}
		
		for(counter = 0; counter < 1; counter++)	//Record all elements of 'status[]'.
		{
			pw.println(status[counter]);
		}
		
		pw.close();									//Close 'pw'.
	}

	public static void startNewGame(int[] buildings, int[] workers, int[] resources, int[] status){

		/*Initialize variables*/
		int counter;
		
		buildings[0] = 5;								//Initialize 'buildings[0]' (houses) to 5.
		for(counter = 1; counter < 10; counter++)		//Initialize all other elements of 'buildings[]' to 0.
		{
			buildings[counter] = 0;
		}
		
		resources[0] = 100;								//Initialize 'resources[0]' (gold) to 100.
		resources[1] = 50;								//Initialize 'resources[1]' (food) to 50.
		resources[2] = 50;								//Initialize 'resources[2]' (wood) to 50.
		resources[3] = 10;								//Initialize 'resources[3]' (stone) to 10.
		
		workers[0] = 20;								//Initialize 'workers[0]' (unskilled workers) to 20.
		workers[1] = 20;								//Initialize 'workers[1]' (unemployed unskilled workers) to 20.
		for(counter = 2; counter < 10; counter++)		//Initialize all other elements of 'workers[]' to 0.
		{
			workers[counter] = 0;
		}
		
		status[0] = 1;									//Initialize 'status[0]' (turn) to 1.
	
		System.out.print("\nStarting New Game\n\n");
	}	

	public static void economicAdvisor(int[] buildings, int[] workers, int[] resources){
		Scanner s = new Scanner(System.in);						//Declare Scanner 's'.
		
		int option;												//'option' will be used to determine what decision is made when the user is faced with a choice.
		int x = 1;												//'x' will be used to exit the Economic Advisor loop when the user selects the "return to advisor menu" option.
		while(x == 1)											//Run the Economic Advisor loop until the user exits it from within.
		{
			/*Display user options and prompt for selection.*/
			System.out.print("\nEconomic Advisor\nWhat do you want to do?\n1) View buildings report\n2) View labor report\n3) View resources report\n4) Construct buildings\n5) Assign unskilled labor\n6) Return to advisor menu\nEnter your selection: ");
			option = Integer.parseInt(s.nextLine());			//Read in the user's selection to 'option'.
			switch(option)
			{
				case 1:											//Display how many of each type of building is in the kingdom.
					System.out.print("\nBuildings Report:\nYou have:\n" + buildings[0] + " houses\n" + buildings[1] + " barracks\n" + buildings[2] + " wheat farms\n" + buildings[3] + " hunting lodges\n" + buildings[4] + " trading posts\n" + buildings[5] + " stone quarries\n");
				break;
				case 2:											//Display how many of each type of worker is in the kingdom.
					System.out.print("\nLabor Report:\nYou have:\n" + workers[0] + " unskilled workers\n" + workers[1] + " unemployed unskilled workers\n" + workers[2] + " wheat farmers\n" + workers[3] + " hunters\n" + workers[4] + " lumberjacks\n" + workers[5] + " stone miners\n");
				break;
				case 3:											//Display how much of each type of resource the kingdom has.
					System.out.print("\nResources Report:\n You have:\n" + resources[0] + " ounces of gold\n" + resources[1] + " units of food\n" + resources[2] + " cords of wood\n" + resources[3] + " units of stone\n");
				break;
				case 4:											
					constructBuildings(buildings, resources);	//Call constructBuildings().
				break;
				case 5:
					assignUnskilledWorkers(workers, buildings);	//Call assignUnskilledWorkers().
				break;
				case 6:
					x = 2;										//Set 'x' = 2 so that the Economic Advisor loop will exit.
				break;
				default:
					System.out.print("\nError, " + option + " is not a valid option");	//Respond to invalid user input for 'option'.
				break;
			}
		}
	}

	public static void tradeAdvisor(int[] resources){
		Scanner s = new Scanner(System.in);				//Declare Scanner 's'.
		
		int x =1;										//'x' will be used to exit the Trade Advisor loop when directed to by the user.
		int option;										//'option' will be used to determine what decision is made when the user is faced with a choice.
		int amount;										//'amount' will be used to determine how much of a resource should be sold or bought.
		char confirm;									//'confirm' will be used to confirm with the user whether or not the selected action should be carried out.
		
		while(x==1)										//Run the Trade Advisor loop until the user exits it from within.
		{												//Display user options and prompt for selection.
			System.out.print("\nTrade Advisor:\nWhat do you want to do?\n1) View resources report\n2) Buy resources\n3) Sell resources\n4) Return to advisor menu\nEnter your selection: ");
			option = Integer.parseInt(s.nextLine());	//Read in user selection to 'option'.
			switch(option)
			{
			case 1:										//Display how much of each type of resource the kingdom has. 
				System.out.print("\nResources Report:\n You have:\n" + resources[0] + " ounces of gold\n" + resources[1] + " units of food\n" + resources[2] + " cords of wood\n" + resources[3] + " units of stone\n");
			break;
			case 2:										//Prompt user to choose which resource to buy.
				System.out.print("\n Which resource do you want to buy?\n1) Food\n2) Wood\n3) Stone\n4) None\nEnter your selection: ");
				option = Integer.parseInt(s.nextLine());//Read in user selection to 'option'.
				switch(option)
				{
				case 1:											//Display exchange rate for buying food and prompt for amount to buy.
					System.out.print("Food costs 3 ounces of gold per unit. How much do you want to buy? ");
					amount = Integer.parseInt(s.nextLine());	//Read in user input to 'amount'.
					System.out.println(amount + " units of food costs " + (3*amount) + " ounces of gold. Okay to go ahead and buy (y/n)? ");	//Display total cost and prompt for confirmation.
					confirm = (s.nextLine()).charAt(0);			//Read in user confirmation to 'confirm'.
					switch(confirm)
					{
					case 'Y':									//If 'confirm' is an upper or lower case y, attempt to execute transaction.
					case 'y':
						if((amount * 3) <= resources[0])		//Check to see if the kingdom has enough gold for this transaction.
						{
							resources[1] += amount;				//Increase 'resources[1]' (food) by 'amount'.
							resources[0] -= (amount * 3);		//Decrease 'resources[0]' (gold) by 'amount' times the exchange rate.
						}	
						else									//Respond to insufficient gold.
						{
							System.out.print("You do not have enough gold. Transaction cancelled.\n");
						}
					break;
					case 'N':									//If 'confirm' is an upper or lower case n, cancel transaction.
					case 'n':
						System.out.print("Okay, transaction cancelled.\n");
					break;
					default:									//Respond to invalid input for 'confirm', and cancel transaction.
						System.out.print("Error, " + confirm + " is not a valid option.\n");
					break;
					}
				break;
				case 2:											//Display exchange rate for buying wood and prompt for amount to buy.
					System.out.print("Wood costs 2 ounces of gold per cord. How much do you want to buy? ");
					amount = Integer.parseInt(s.nextLine());	//Read in user input to 'amount'.
					System.out.println(amount + " cords of wood costs " + (2*amount) + " ounces of gold. Okay to go ahead and buy (y/n)? ");	//Display total cost and prompt for confirmation.
					confirm = (s.nextLine()).charAt(0);			//Read in user confirmation to 'confirm'.
					switch(confirm)
					{
					case 'Y':									//If 'confirm' is an upper or lower case y, attempt to execute transaction.
					case 'y':
						if((amount * 2) <= resources[0])		//Check to see if the kingdom has enough gold for this transaction.
						{
							resources[2] += amount;				//Increase 'resources[2]' (wood) by 'amount'.
							resources[0] -= (amount * 2);		//Decrease 'resources[0]' (gold) by 'amount' times the exchange rate.
						}
						else									//Respond to insufficient gold.
						{
							System.out.print("You do not have enough gold. Transaction cancelled.\n");
						}
					break;
					case 'N':									//If 'confirm' is an upper or lower case n, cancel transaction.
					case 'n':
						System.out.print("Okay, transaction cancelled.\n");
					break;
					default:									//Respond to invalid input for 'confirm', and cancel transaction.
						System.out.print("Error, " + confirm + " is not a valid option.\n");
					break;
					}
				break;
				case 3:											//Display exchange rate for buying stone and prompt for amount to buy.
					System.out.print("Stone costs 6 ounces of gold per unit. How much do you want to buy? ");
					amount = Integer.parseInt(s.nextLine());	//Read in user input to 'amount'.
					System.out.println(amount + " units of stone costs " + (6*amount) + " ounces of gold. Okay to go ahead and buy (y/n)? ");	//Display total cost and prompt for confirmation.
					confirm = (s.nextLine()).charAt(0);			//Read in user confirmation to 'confirm'.
					switch(confirm)
					{
					case 'Y':									//If 'confirm' is an upper or lower case y, attempt to execute transaction.
					case 'y':
						if((amount * 6) <= resources[0])		//Check to see if the kingdom has enough gold for this transaction.
						{
							resources[3] += amount;				//Increase 'resources[3]' (stone) by 'amount'.
							resources[0] -= (amount * 6);		//Decrease 'resources[0]' (gold) by 'amount' times the exchange rate.
						}
						else									//Respond to insufficient gold.
						{
							System.out.print("You do not have enough gold. Transaction cancelled.\n");
						}
					break;
					case 'N':									//If 'confirm' is an upper or lower case n, cancel transaction.
					case 'n':
						System.out.print("Okay, transaction cancelled.\n");
					break;
					default:									//Respond to invalid input for 'confirm', and cancel transaction.
						System.out.print("Error, " + confirm + " is not a valid option.\n");
					break;
					}
				break;
				case 4:											//Cancel transaction.
					System.out.print("Okay, transaction cancelled.\n");
				break;
				default:										//Respond to invalid input for 'option'.
					System.out.print("Error, " + option + " is not a valid option.\n");
				break;
				}	
				option = 2;										//Reset 'option' to 2, so that it can continue to be used for the Trade Advisor loop.
			break;
			case 3:												//Prompt user to choose which resource to sell.
				System.out.print("\nWhich resource do you want to sell?\n1) Food\n2) Wood\n3) Stone\n4) None\nEnter your selection: ");
				option = Integer.parseInt(s.nextLine());		//Read in user selection to 'option'.
				switch(option)
				{
				case 1:											//Display exchange rate for selling food and prompt for amount to sell.
					System.out.print("Food is worth 2 ounces of gold per unit. How much do you want to sell? ");
					amount = Integer.parseInt(s.nextLine());	//Read in user input to 'amount'.
					System.out.println(amount + " units of food are worth " + (2*amount) + " ounces of gold. Okay to go ahead and sell (y/n)? ");	//Display total sale and prompt for confirmation.
					confirm = (s.nextLine()).charAt(0);			//Read in user confirmation to 'confirm'.
					switch(confirm)
					{
					case 'Y':									//If 'confirm' is an upper or lower case y, attempt to execute transaction.
					case 'y':
						if(amount <= resources[1])				//Check to see if the kingdom has enough food to execute the transaction.
						{
							resources[1] -= amount;				//Decrease 'resources[1]' (food) by 'amount'.
							resources[0] += (amount * 2);		//Increase 'resources[0]' (gold) by 'amount' times the exchange rate.
						}	
						else									//Respond to insufficient amount of food.
						{
							System.out.print("You do not have that much food. Transaction cancelled.\n");
						}
					break;
					case 'N':									//If 'confirm' is an upper or lower case n, cancel transaction.
					case 'n':
						System.out.print("Okay, transaction cancelled.\n");
					break;
					default:									//Respond to invalid input for 'confirm', and cancel transaction.
						System.out.print("Error, " + confirm + " is not a valid option.\n");
					break;
					}
				break;
				case 2:											//Display exchange rate for selling wood and prompt for amount to sell.
					System.out.print("Wood is worth 1 ounce of gold per cord. How much do you want to sell? ");
					amount = Integer.parseInt(s.nextLine());	//Read in user input to 'amount'.
					System.out.println(amount + " cords of wood are worth " + (amount) + " ounces of gold. Okay to go ahead and sell (y/n)? ");	//Display total sale and prompt for confirmation.
					confirm = (s.nextLine()).charAt(0);			//Read in user confirmation to 'confirm'.
					switch(confirm)
					{
					case 'Y':									//If 'confirm' is an upper or lower case y, attempt to execute transaction.
					case 'y':
						if(amount <= resources[2])				//Check to see if the kingdom has enough wood to execute the transaction.
						{
							resources[2] -= amount;				//Decrease 'resources[2]' (wood) by 'amount'.
							resources[0] += amount;				//Increase 'resources[0]' (gold) by 'amount' times the exchange rate.
						}
						else									//Respond to insufficient amount of wood.
						{
							System.out.print("You do not have that much wood. Transaction cancelled.\n");
						}
					break;
					case 'N':									//If 'confirm' is an upper or lower case n, cancel transaction.
					case 'n':
						System.out.print("Okay, transaction cancelled.\n");
					break;
					default:									//Respond to invalid input for 'confirm', and cancel transaction.
						System.out.print("Error, " + confirm + " is not a valid option.\n");
					break;
					}
				break;	
				case 3:											//Display exchange rate for selling stone and prompt for amount to sell.
					System.out.print("Stone is worth 2 ounces of gold per unit. How much do you want to sell? ");
					amount = Integer.parseInt(s.nextLine());	//Read in user input to 'amount'.
					System.out.println(amount + " units of stone are worth " + (2*amount) + " ounces of gold. Okay to go ahead and sell (y/n)? ");	//Display total sale and prompt for confirmation.
					confirm = (s.nextLine()).charAt(0);			//Read in user confirmation to 'confirm'.
					switch(confirm)
					{
					case 'Y':									//If 'confirm' is an upper or lower case y, attempt to execute transaction.
					case 'y':
						if(amount <= resources[3])				//Check to see if the kingdom has enough stone to execute the transaction.
						{
							resources[3] -= amount;				//Decrease 'resources[3]' (stone) by 'amount'.
							resources[0] += (amount * 2);		//Increase 'resources[0]' (gold) by 'amount' times the exchange rate.
						}
						else									//Respond to insufficient amount of wood.
						{
							System.out.print("You do not have that much stone. Transaction cancelled.\n");
						}
					break;
					case 'N':									//If 'confirm' is an upper or lower case n, cancel transaction.
					case 'n':
						System.out.print("Okay, transaction cancelled.\n");
					break;
					default:									//Respond to invalid input for 'confirm', and cancel transaction.
						System.out.print("Error, " + confirm + " is not a valid option.\n");
					break;
					}
				break;
				case 4:											//Cancel transaction.
					System.out.print("Okay, transaction cancelled.\n");
				break;
				default:										//Respond to invalid input for 'option', and cancel transaction.
					System.out.print("Error, " + option + " is not a valid option.\n");
				break;
				}
				option = 3;										//Reset 'option' to 3, so that it can continue to be used for the Trade Advisor loop.
			break;
			case 4:
				x = 2;											//Set x = 2 so that the Trade Advisor loop will exit.
			break;
			default:											//Respond to invalid user input for 'option'.
				System.out.print("Error, " + option + " is not a valid option.\n");
			break;
			}
		}
	}

	public static void assignUnskilledWorkers(int[] workers, int[] buildings){
		Scanner s = new Scanner(System.in);				//Declare Scanner 's'.
		int option;										//'option' will be used to determine what decision is made when the user is faced with a choice. 
		int number;										//'number' will store the number of workers to hire/fire.
		int choice;										//'choice' will be used to determine what decision is made when the user is faced with a choice.
		int x = 1;										//'x' will determine when the hire/fire loop exits.
		while (x==1)									//Run loop until the user exits from within.
		{											
														//Display user options, and prompt for a decision.
			System.out.print("\nWhat do you want to do?\n1) View labor report\n2) Hire workers\n3) Fire workers\n4) Return to labor advisor menu\n Enter your selection: ");
			option = Integer.parseInt(s.nextLine());	//Read user selection into 'option'.
			switch(option)
			{
			case 1:
				System.out.print("\nLabor Report:\nYou have:\n" + workers[0] + " unskilled workers\n" + workers[1] + " unemployed unskilled workers\n(" + workers[2] + "/" + (buildings[2]*5) + ") wheat farmers\n(" + workers[3] + "/" + (buildings[3]*5) + ") hunters\n" + workers[4] + " lumberjacks\n(" + workers[5] + "/" + (buildings[5]*10) + ") stone miners\n");
			break;
			case 2:										//Display unskilled jobs and prompt for selection.
				System.out.print("\nWhat do you want to hire?\n1) Wheat farmers (" + workers[2] + "/" + (buildings[2]*5) + ")\n2) Hunters (" + workers[3] + "/" + (buildings[3]*5) + ")\n3) Lumberjacks\n4) Stone miners (" + workers[5] + "/" + (buildings[5]*10) + ")\n5) Return to assignment menu\nEnter your selection: ");
				choice = Integer.parseInt(s.nextLine());//Read in user selection to 'choice'.
				switch(choice)
				{
					case 1:
						System.out.print("\nHow many wheat famers do you want to hire? ");			//Prompt for number of wheat farmers to hire.
						number = Integer.parseInt(s.nextLine());									//Read in number of wheat farmers to hire into 'number'.
						if((number <= workers[1]) && ((number + workers[2]) <= (buildings[2]*5)))	//Make sure that there are enough wheat farms and unemployed unskilled workers.
						{
							workers[2] = workers[2] + number;	//Increase 'workers[2]' (wheat farmers) by 'number'.
							workers[1] = workers[1] - number;	//Decrease 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers hired.\n");
						}
						else if(number > workers[1])			//Respond to an insufficient number of unemployed unskilled workers.
						{
							System.out.print("You do not have enough unemployed low skill workers. Hiring abondoned.\n");
						}
						else if((number + workers[2]) > (buildings[2]*5))	//Respond to an insufficient number of wheat farms.
						{
							System.out.print("You do not have enough wheat farms. Hiring abondoned.\n");	
						}
					break;
					case 2:
						System.out.print("\nHow many hunters do you want to hire? ");					//Prompt for number of hunters to hire.
						number = Integer.parseInt(s.nextLine());									//Read in number of hunters to hire into 'number'.
						if((number <= workers[1]) && ((number + workers[3]) <= (buildings[3]*5)))	//Make sure that there are enough hunting lodges and unemployed unskilled workers.
						{
							workers[3] = workers[3] + number;	//Increase 'workers[3]' (hunters) by 'number'.
							workers[1] = workers[1] - number;	//Decrease 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers hired.\n");
						}
						else if(number > workers[1])			//Respond to an insufficient number of unemployed unskilled workers.
						{
							System.out.print("You do not have enough unemployed low skill workers. Hiring abondoned.\n");
						}
						else if((number + workers[3]) > (buildings[3]*5))	//Respond to an insufficient number of hunting lodges.
						{
							System.out.print("You do not have enough hunting lodges. Hiring abondoned.\n");
						}
					break;
					case 3:
						System.out.print("\nHow many lumberjacks do you want to hire? ");		//Prompt for number of lumberjacks to hire.
						number = Integer.parseInt(s.nextLine());							//Read in number of lumberjacks to hire into 'number'.
						if(number <= workers[1])											//Make sure that there are enough unemployed unskilled workers.
						{
							workers[4] = workers[4] + number;	//Increase 'workers[4]' (lumberjacks) by 'number'.
							workers[1] = workers[1] - number;	//Decrease 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers hired.\n");
						}
						else if(number > workers[1])			//Respond to an insufficient number of unemployed unskilled workers.
						{
							System.out.print("You do not have enough unemployed low skill workers. Hiring abondoned.\n");
						}
					break;
					case 4:
						System.out.print("\nHow many stone miners do you want to hire? ");	//Prompt for number of stone miners to hire.
						number = Integer.parseInt(s.nextLine());							//Read in number of stone miners to hire into 'number'.
						if((number <= workers[1])	&& ((number + workers[5]) <= (buildings[5]*10)))	//Make sure that there are enough stone quarries and unemployed unskilled workers.
						{
							workers[5] = workers[5] + number;	//Increase 'workers[5]' (stone miners) by 'number'.
							workers[1] = workers[1] - number;	//Decrease 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers hired.\n");
						}
						else if(number > workers[1])			//Respond to an insufficient number of unemployed unskilled workers.
						{
							System.out.print("You do not have enough unemployed low skill workers. Hiring abondoned.\n");
						}
						else if((number + workers[5]) > (buildings[5]*10))	//Respond to an insufficient number of stone quarries.
						{
							System.out.print("You do not have enough stone quarries. Hiring abondoned.\n");
						}
					break;
					
					case 5:										//User selected to return to assign workers menu.
						System.out.print("Hiring abondoned.\n");
					break;
					default:									
						System.out.print("Error, invalid user option. Hiring abondoned.\n");	//Respond to invalid user input.
					break;
					}
				break;
				case 3:														//Display unskilled jobs and prompt for selection.
					System.out.print("\nWhat do you want to fire?\n1) Wheat farmers (" + workers[2] + "/" + (buildings[2]*5) + ")\n2) Hunters (" + workers[3] + "/" + (buildings[3]*5) + ")\n3) Lumberjacks\n4) Stone miners (" + workers[5] + "/" + (buildings[5]*10) + ")\n5) Return to assignment menu\nEnter your selection: ");
					choice = Integer.parseInt(s.nextLine());				//Read in user selection to 'choice'.
					switch(choice)
					{
					case 1:
						System.out.print("\nHow many wheat famers do you want to fire? ");	//Prompt for number of wheat farmers to fire.
						number = Integer.parseInt(s.nextLine());			//Read in number of wheat farmers to fire into 'number'.
						if(number <= workers[2])							//Make sure there are enough wheat farmers.
						{
							workers[2] = workers[2] - number;				//Decrease 'workers[2]' (wheat farmers) by 'number'.
							workers[1] = workers[1] + number;				//Increase 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers fired.\n");
						}
						else												//Respond to an insufficient number of wheat farmers.
						{
							System.out.print("You do not have that many wheat farmers. Firing abondoned.\n");
						}	
					break;
					case 2:
						System.out.print("\nHow many hunters do you want to fire? ");			//Prompt for number of hunters to fire.
						number = Integer.parseInt(s.nextLine());			//Read in number of hunters to fire into 'number'.
						if(number <= workers[3])							//Make sure there are enough hunters.
						{
							workers[3] = workers[3] - number;				//Decrease 'workers[3]' (hunters) by 'number'.
							workers[1] = workers[1] + number;				//Increase 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers fired.\n");
						}
						else												//Respond to an insufficient number of hunters.
						{
							System.out.print("You do not have that many hunters. Firing abondoned.\n");
						}
					break;
					case 3:
						System.out.print("\nHow many lumberjacks do you want to fire? ");		//Prompt for number of lumberjacks to fire.
						number = Integer.parseInt(s.nextLine());			//Read in number of lumberjacks to fire into 'number'.
						if(number <= workers[4])							//Make sure there are enough lumberjacks.
						{
							workers[4] = workers[4] - number;				//Decrease 'workers[4]' (lumberjacks) by 'number'.
							workers[1] = workers[1] + number;				//Increase 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers fired.\n");
						}
						else												//Respond to an insufficient number of lumberjacks.
						{
							System.out.print("You do not have that many lumberjacks. Firing abondoned.\n");
						}
					break;
					case 4:
						System.out.print("\nHow many stone miners do you want to fire? ");		//Prompt for number of stone miners to fire.
						number = Integer.parseInt(s.nextLine());			//Read in number of stone miners to fire into 'number'.
						if(number <= workers[5])							//Make sure there are enough stone miners.
						{
							workers[5] = workers[5] - number;				//Decrease 'workers[5]' (stone miners) by 'number'.
							workers[1] = workers[1] + number;				//Increase 'workers[1]' (unemployed unskilled workers) by 'number'.
							System.out.print("\nWorkers fired.\n");
						}
						else												//Respond to an insufficient number of stone miners.
						{
							System.out.print("You do not have that many stone miners. Firing abondoned.\n");
						}
					break;
					
					case 5:								//User selection to return to assignment menu.
						System.out.print("Firing abondoned.\n");
					break;
					default:							//Respond to invalid user input.
						System.out.print("Error, invalid user option. Firing abondoned.\n");
					break;
					}
				break;
				case 4:									//User selection to return to Labor Advisor menu.
					x = 2;								//Set 'x' = 2;
					System.out.print("Returning to Labor Advisor menu.\n");
				break;
			default:									//Respond to invalid user input.
				System.out.print("Error, invalid user option.\n");
			break;
			}	
		}
	}

	public static void constructBuildings(int[] buildings, int[] resources){
	
		Scanner s = new Scanner(System.in);
		char confirm;
		int option;
		int x = 1;
	
		while(x==1)
		{
			System.out.print("\nWhat do you want to build?\n1) View buildings report\n2) A house\n3) A barracks\n4) A wheat farm\n5) A hunting lodge\n6) A trading post\n7) A stone quarry\n8)Return to Labor Advisor menu\n Enter your selection: ");
			option = Integer.parseInt(s.nextLine());	//Read in user selection to 'choice'.
			switch(option)
			{
			case 1:
				System.out.print("\nBuildings Report:\nYou have:\n" + buildings[0] + " houses\n" + buildings[1] + " barracks\n" + buildings[2] + " wheat farms\n" + buildings[3] + " hunting lodges\n" + buildings[4] + " trading posts\n");
			break;
			case 2:									//Display cost of building a house and prompt user for confirmation.
				System.out.print("\n A house costs 10 cords of wood and 5 ounces of gold to build.\nOkay to go ahead and build a house (y/n)? ");
				confirm = (s.nextLine()).charAt(0);	//Read in user confirmation to 'confirm'.
				switch(confirm)
				{
				case 'y':						//If 'confirm' is an upper or lower case y, attempt to build a house.
				case 'Y':
					if((resources[2] >= 10) && (resources[0] >= 5))	//Check whether or not the kingdom has enough resources to build a house.
					{
						resources[0] -= 5;							//Decrease 'resources[0]' (gold) by 5.
						resources[2] -= 10;							//Decrease 'resources[2]' (wood) by 10.
						buildings[0]++;								//Increase 'buildings[0]' (houses) by 1.
						System.out.print("\nHouse built.\n");		//Inform the user that the house was successfully built.
					}
					else if (resources[2] < 10)						//Respond to insufficient wood.
					{
						System.out.print("You do not have enough wood to build a house. Construction abandoned.\n");
					}
					else if (resources[0] < 5)						//Respond to insufficient gold.
					{
						System.out.print("You do not have enough gold to build a house. Construction abandoned.\n");
					}
				break;
				case 'N':						//If 'confirm' is an upper or lower case n, abandon construction.
				case 'n':
					System.out.print("Okay, construction abandoned.\n");
				break;
				default:						//Respond to invalid user input for 'confirm', and abandon construction.
					System.out.print("Error, " + confirm + " is not a valid option. Construction abandonded.\n");
				break;
				}
			break;
			case 3:									//Display cost of building a barracks and prompt user for confirmation.
				System.out.print("\n A barracks costs 50 cords of wood, 20 units of stone, and 45 ounces of gold to build.\nOkay to go ahead and build a barracks (y/n)? ");
				confirm = (s.nextLine()).charAt(0);	//Read in user confirmation to 'confirm'.
				switch(confirm)
				{
				case 'y':						//If 'confirm' is an upper or lower case y, attempt to build a barracks.
				case 'Y':
					if((resources[2] >= 50) && (resources[0] >= 45) && (resources[3] >= 20))	//Check whether or not the kingdom has enough resources to build a barracks.
					{
						resources[0] -= 45;		//Decrease 'resources[0]' (gold) by 45.
						resources[2] -= 50;		//Decrease 'resources[2]' (wood) by 50.
						resources[3] -= 20;		//Decrease 'resources[3]' (stone) by 20.
						buildings[1]++;			//Increase 'buildings[1]' (barracks) by 1.
						System.out.print("\nbarracks built.\n");	//Inform the user that the barracks was successfully built.
					}
					else if (resources[2] < 50)	//Respond to insufficient wood.
					{
						System.out.print("You do not have enough wood to build a barracks. Construction abandoned.\n");
					}
					else if (resources[3] < 20)	//Respond to insufficient stone.
					{
						System.out.print("You do not have enough stone to build a barracks. Construction abandoned.\n");
					}
					else if (resources[0] < 45)	//Respond to insufficient gold.
					{
						System.out.print("You do not have enough gold to build a barracks. Construction abandoned.\n");
					}
				break;
				case 'N':						//If 'confirm' is an upper or lower case n, abandon construction.
				case 'n':
					System.out.print("Okay, construction abandoned.\n");
				break;
				default:						//Respond to invalid user input for 'confirm', and abandon construction.
					System.out.print("Error, " + confirm + " is not a valid option. Construction abandonded.\n");
				break;
				}
			break;
			case 4:									//Display cost of building a wheat farm and prompt user for confirmation.
				System.out.print("\n A wheat farm costs 70 cords of wood, 3 units of stone, 20 units of food, and 40 ounces of gold to build.\nOkay to go ahead and build a wheat farm (y/n)? ");
				confirm = (s.nextLine()).charAt(0);	//Read in user confirmation to 'confirm'.
				switch(confirm)
				{
				case 'y':						//If 'confirm' is an upper or lower case y, attempt to build a wheat farm.
				case 'Y':
					if((resources[0] >= 40) && (resources[1] >= 20) && (resources[2] >= 70) && (resources[3] >= 3))	//Check whether or not the kingdom has enough resources to build a wheat farm.
					{
						resources[0] -= 40;		//Decrease 'resources[0]' (gold) by 40.
						resources[1] -= 20;		//Decrease 'resources[1]' (food) by 20.
						resources[2] -= 70;		//Decrease 'resources[2]' (wood) by 70.
						resources[3] -= 3;		//Decrease 'resources[3]' (stone) by 3.
						buildings[2]++;			//Increase 'buildings[2]' (wheat farms) by 1.
						System.out.print("\nWheat farm built.\n");	//Inform the user that the wheat farm was successfully built.
					}
					else if (resources[1] < 20)	//Respond to insufficient food.
					{
						System.out.print("You do not have enough food to build a wheat farm. Construction abandoned.\n");
					}
					else if (resources[2] < 70)	//Respond to insufficient wood.
					{
						System.out.print("You do not have enough wood to build a wheat farm. Construction abandoned.\n");
					}
					else if (resources[3] < 3)	//Respond to insufficient stone.
					{
						System.out.print("You do not have enough stone to build a wheat farm. Construction abandoned.\n");
					}
					else if (resources[0] < 40)	//Respond to insufficient gold.
					{
						System.out.print("You do not have enough gold to build a wheat farm. Construction abandoned.\n");
					}
				break;
				case 'N':						//If 'confirm' is an upper or lower case n, abandon construction.
				case 'n':
					System.out.print("Okay, construction abandoned.\n");
				break;
				default:						//Respond to invalid user input for 'confirm', and abandon construction.
					System.out.print("Error, " + confirm + " is not a valid option. Construction abandonded.\n");
				break;
				}
			break;
			case 5:									//Display cost of building a hunting lodge and prompt user for confirmation.
				System.out.print("\n A hunting lodge costs 50 cords of wood, 10 units of stone, 5 units of food, and 30 ounces of gold to build.\nOkay to go ahead and build a hunting lodge (y/n)? ");
				confirm = (s.nextLine()).charAt(0);	//Read in user confirmation to 'confirm'.
				switch(confirm)
				{
				case 'y':						//If 'confirm' is an upper or lower case y, attempt to build a hunting lodge.
				case 'Y':
					if((resources[0] >= 30) && (resources[1] >= 5) && (resources[2] >= 50) && (resources[3] >= 10))	//Check whether or not the kingdom has enough resources to build a hunting lodge.
					{
						resources[0] -= 30;		//Decrease 'resources[0]' (gold) by 30.
						resources[1] -= 5;		//Decrease 'resources[1]' (food) by 5.
						resources[2] -= 50;		//Decrease 'resources[2]' (wood) by 50.
						resources[3] -= 10;		//Decrease 'resources[3]' (stone) by 10.
						buildings[3]++;			//Increase 'buildings[3]' (hunting lodges) by 1.
						System.out.print("\nHunting lodge built.\n");	//Inform the user that the hunting lodge was successfully built.
					}
					else if (resources[1] < 5)	//Respond to insufficient food.
					{
						System.out.print("You do not have enough food to build a hunting lodge. Construction abandoned.\n");
					}
					else if (resources[2] < 50)	//Respond to insufficient wood.
					{
						System.out.print("You do not have enough wood to build a hunting lodge. Construction abandoned.\n");
					}
					else if (resources[3] < 10)	//Respond to insufficient stone.
					{
						System.out.print("You do not have enough stone to build a hunting lodge. Construction abandoned.\n");
					}
					else if (resources[0] < 30)	//Respond to insufficient gold.
					{
						System.out.print("You do not have enough gold to build a hunting lodge. Construction abandoned.\n");
					}
				break;
				case 'N':						//If 'confirm' is an upper or lower case n, abandon construction.
				case 'n':
					System.out.print("Okay, construction abandoned.\n");
				break;
				default:						//Respond to invalid user input for 'confirm', and abandon construction.
					System.out.print("Error, " + confirm + " is not a valid option. Construction abandonded.\n");
				break;
				}	
			break;
			case 6:									//Display cost of building a trading post and prompt user for confirmation.
				System.out.print("\n A trading post costs 30 cords of wood, 5 units of stone, and 20 ounces of gold to build.\nOkay to go ahead and build a trading post (y/n)? ");
				confirm = (s.nextLine()).charAt(0);	//Read in user confirmation to 'confirm'.
				switch(confirm)
				{
				case 'y':						//If 'confirm' is an upper or lower case y, attempt to build a trading post.
				case 'Y':
					if((resources[0] >= 20) && (resources[2] >= 30) && (resources[3] >= 5))	//Check whether or not the kingdom has enough resources to build a trading post.
					{
						resources[0] -= 20;		//Decrease 'resources[0]' (gold) by 20.
						resources[2] -= 30;		//Decrease 'resources[2]' (wood) by 30.
						resources[3] -= 5;		//Decrease 'resources[3]' (stone) by 5.
						buildings[4]++;			//Increase 'buildings[4]' (trading posts) by 1.
						System.out.print("\nTrading post built.\n");	//Inform the user that the trading post was successfully built.
						if (buildings[4] == 1)	//If this is the kingdom's first trading post, then inform the user that the Trade Advisor is now available.
							{
								System.out.print("Trade Advisor unlocked!\n\n");
							}
					}
					else if (resources[2] < 30)	//Respond to insufficient wood.
					{
						System.out.print("You do not have enough wood to build a trading post. Construction abandoned.\n");
					}
					else if (resources[3] < 5)	//Respond to insufficient stone.
					{
						System.out.print("You do not have enough stone to build a trading post. Construction abandoned.\n");
					}
					else if (resources[0] < 20)	//Respond to insufficient gold.
					{
						System.out.print("You do not have enough gold to build a trading post. Construction abandoned.\n");
					}
				break;
				case 'N':						//If 'confirm' is an upper or lower case n, abandon construction.
				case 'n':
					System.out.print("Okay, construction abandoned.\n");
				break;
				default:						//Respond to invalid user input for 'confirm', and abandon construction.
					System.out.print("Error, " + confirm + " is not a valid option. Construction abandonded.\n");
				break;
				}	
			break;
			case 7:									//Display cost of building a stone quarry and prompt user for confirmation.
				System.out.print("\n A stone quarry costs 60 cords of wood, 5 units of stone, and 30 ounces of gold to build.\nOkay to go ahead and build a stone quarry (y/n)? ");
				confirm = (s.nextLine()).charAt(0);	//Read in user confirmation to 'confirm'.
				switch(confirm)
				{
				case 'y':						//If 'confirm' is an upper or lower case y, attempt to build a stone quarry.
				case 'Y':
					if((resources[0] >= 30) && (resources[2] >= 60) && (resources[3] >= 5))	//Check whether or not the kingdom has enough resources to build a stone quarry.
					{
						resources[0] -= 30;		//Decrease 'resources[0]' (gold) by 20.
						resources[2] -= 60;		//Decrease 'resources[2]' (wood) by 30.
						resources[3] -= 5;		//Decrease 'resources[3]' (stone) by 5.
						buildings[5]++;			//Increase 'buildings[4]' (trading posts) by 1.
						System.out.print("\nStone quarry built.\n");	//Inform the user that the stone quarry was successfully built.
					}
					else if (resources[2] < 60)	//Respond to insufficient wood.
					{
						System.out.print("You do not have enough wood to build a stone quarry. Construction abandoned.\n");
					}
					else if (resources[3] < 5)	//Respond to insufficient stone.
					{
						System.out.print("You do not have enough stone to build a stone quarry. Construction abandoned.\n");
					}
					else if (resources[0] < 30)	//Respond to insufficient gold.
					{
						System.out.print("You do not have enough gold to build a stone quarry. Construction abandoned.\n");
					}
				break;
				case 'N':						//If 'confirm' is an upper or lower case n, abandon construction.
				case 'n':
					System.out.print("Okay, construction abandoned.\n");
				break;
				default:						//Respond to invalid user input for 'confirm', and abandon construction.
					System.out.print("Error, " + confirm + " is not a valid option. Construction abandonded.\n");
				break;
				}	
			break;
			case 8:									//Abandon construction.
				x = 2;
				System.out.print("Okay, we won't build anything.\n");
			break;
			default:								//Respond to invalid user input for 'option'.
				System.out.print("Error, " + option + " is not a valid option.\n");
			break;
			}
		}
	}

	public static void endTurn(int[] buildings, int[] workers, int[] resources, int[] status){
		
		Scanner s = new Scanner(System.in);							//Declare Scanner 's'.
		char confirm;												//'confirm' will be used to confirm with the user whether or not the selected action should be carried out.
		int x = 1;													//'x' will control when the end of turn report loop ends.
		System.out.print("\n Ending Turn. View end of turn report? (y/n): ");	//Prompt for decision on whether or not to view the end of turn report.
		confirm = s.nextLine().charAt(0);							//Read in user selection to 'confirm'.
		
		while(x == 1)
		{
			if((confirm == 'y') || (confirm == 'Y'))				//If the user wishes to see the end of turn report...
			{
				System.out.print("\nEnd of turn report:\nTotal food produced: " + (workers[2]*10 + workers[3]*5) + " units.\nTotal wood produced was: " + (workers[4]*5) + " cords.\nTotal stone produced was: " + (workers[5]*3) + " units.\n");
				x = 2;												//Set 'x' equal to two, so that the loop will exit.
			}
			else if ((confirm != 'n') && (confirm != 'N'))			//Respond to invalid user input.
			{
				System.out.print("\nError, invalid input.\n");
			}
			else
			{
				x = 2;												//If the user does not want to see the end of turn report, set 'x' equal to 2.
			}
		}
		
		status[0]++;									//Increase 'status[0]' (turn) by one.
		resources[1] = resources[1] + workers[2]*10;	//Increase 'resources[1]' (food) by 'workers[2]' (wheat farmers) times 10.
		resources[1] = resources[1] + workers[3]*5;		//Increase 'resources[1]' (food) by 'workers[3]' (hunters) times 5.
		resources[2] = resources[2] + workers[4]*5;		//Increase 'resources[2]' (wood) by 'workers[4]' (lumberjacks) times 5.
		resources[3] = resources[3] + workers[5]*3;		//Increase 'resources[3]' (stone) by 'workers[5]' (stone miners) times 3.
	}
}

	