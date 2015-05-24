package mine_sweeper;

public class mine_sweeper {
	public static void main(String[]args){
		controller tempTest= new controller();
		tempTest.new_game(2,10,10);
		tempTest.printMap();
	}
}
