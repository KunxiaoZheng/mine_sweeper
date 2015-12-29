package mine_sweeper;

import java.util.Random;

public class controller {
	private static int[][] map;

	public void new_game() {
		map = new int[10][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 0;
			}
		}
		addBomb();
	}

	public void new_game(int x, int y) {
		map = new int[x][y];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 0;
			}
		}
		addBomb();
	}

	public void new_game(int x, int y, int bombNum) {
		map = new int[x][y];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 0;
			}
		}
		insertBomb(bombNum);
	}

	private void addBomb() {
		int temp = map.length;
		temp = temp * map[0].length;
		insertBomb((int) (temp * 0.15));
	}

	// adding a
	private void insertBomb(int num) {
		int x = map.length;
		int y = map[0].length;
		Random rand = new Random();
		int xValue = 0;
		int yValue = 0;
		for (int bombCount = 0; bombCount < num; bombCount++) {
			do {
				xValue = rand.nextInt(x);
				yValue = rand.nextInt(y);
			} while (map[xValue][yValue] != 0);
			map[xValue][yValue] = 1;
		}
	}

	public boolean checkForBomb(int x, int y) {
		if (map[x][y] == 1) {
			return true;
		}
		return false;
	}

	public int checkSourrending(int x, int y) {
		int result = 0;
		if (x == 0 && y == 0) {
			if (checkForBomb(x, y + 1))
				result++;
			if (checkForBomb(x + 1, y + 1))
				result++;
			if (checkForBomb(x + 1, y))
				result++;
		} else if (x == map.length - 1 && y == map[0].length - 1) {
			if (checkForBomb(x, y - 1))
				result++;
			if (checkForBomb(x - 1, y))
				result++;
			if (checkForBomb(x - 1, y - 1))
				result++;
		} else if (x == map.length - 1 && y == 0) {
			if (checkForBomb(x - 1, y + 1))
				result++;
			if (checkForBomb(x, y + 1))
				result++;
			if (checkForBomb(x - 1, y))
				result++;
		} else if (x == 0 && y == map[0].length - 1) {
			if (checkForBomb(x, y - 1))
				result++;
			if (checkForBomb(x + 1, y - 1))
				result++;
			if (checkForBomb(x + 1, y))
				result++;
		} else if (x == 0) {
			if (checkForBomb(x, y - 1))
				result++;
			if (checkForBomb(x + 1, y - 1))
				result++;
			if (checkForBomb(x + 1, y))
				result++;
			if (checkForBomb(x + 1, y + 1))
				result++;
			if (checkForBomb(x, y + 1))
				result++;
		} else if (x == map.length - 1) {
			if (checkForBomb(x - 1, y + 1))
				result++;
			if (checkForBomb(x, y + 1))
				result++;
			if (checkForBomb(x - 1, y))
				result++;
			if (checkForBomb(x - 1, y - 1))
				result++;
			if (checkForBomb(x, y - 1))
				result++;
		} else if (y == 0) {
			if (checkForBomb(x, y + 1))
				result++;
			if (checkForBomb(x + 1, y + 1))
				result++;
			if (checkForBomb(x + 1, y))
				result++;
			if (checkForBomb(x - 1, y))
				result++;
			if (checkForBomb(x - 1, y + 1))
				result++;
		} else if (y == map[0].length - 1) {
			if (checkForBomb(x, y - 1))
				result++;
			if (checkForBomb(x + 1, y - 1))
				result++;
			if (checkForBomb(x + 1, y))
				result++;
			if (checkForBomb(x - 1, y))
				result++;
			if (checkForBomb(x - 1, y - 1))
				result++;
		} else {
			if (checkForBomb(x, y - 1))
				result++;
			if (checkForBomb(x, y + 1))
				result++;
			if (checkForBomb(x - 1, y + 1))
				result++;
			if (checkForBomb(x - 1, y))
				result++;
			if (checkForBomb(x - 1, y - 1))
				result++;
			if (checkForBomb(x + 1, y + 1))
				result++;
			if (checkForBomb(x + 1, y))
				result++;
			if (checkForBomb(x + 1, y - 1))
				result++;
		}
		return result;
	}

	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------------");
	}
}
