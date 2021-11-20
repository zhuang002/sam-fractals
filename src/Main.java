import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int level = sc.nextInt();
		int width = sc.nextInt();
		int xCoord = sc.nextInt();
		
		HashSet<Integer> crosses = new HashSet<>();
		crosses.add(1);
		

		Square square =new Square(1,0,1,width);
		HashSet<Square> nextSquares=new HashSet<>();
		nextSquares.add(square);
		
		for (int i=0;i<level;i++) {
			nextSquares = doNextLevel(nextSquares, crosses, xCoord);
		}
		
		for (int crs:crosses) {
			System.out.print(crs+" ");
		}
	}
	
	private static HashSet<Square> doNextLevel(ArrayList<Square> nextSquares, HashSet<Integer> crosses, int xCoord) {
		// TODO Auto-generated method stub
		HashSet<Square> ret = new HashSet<>();
		for (Square sq:nextSquares) {
			HashSet<Square> squares=doCut(sq, crosses, xCoord);
			ret.addAll(squares);
		}
		return ret;
	}

	private static HashSet<Square> doCut(Square sq, HashSet<Integer> crosses, int xCoord) {
		// TODO Auto-generated method stub
		HashSet<Square> ret = new HashSet<>();
		int relativeX = xCoord-sq.x;
		int newLen = sq.len/3;
		switch (sq.type) {
		case 0:
			if (relativeX< newLen) {
				Square square = new Square(0, sq.x,sq.y,newLen);
				ret.add(square);
			} else if (relativeX == newLen) {
				crosses.add(sq.y+newLen);
			} else if (relativeX<newLen*2) {
				crosses.remove(sq.y);
				crosses.add(sq.y+newLen);
				Square square = new Square(0, sq.x+newLen, sq.y+newLen, newLen);
				ret.add(square);
			} else if (relativeX == newLen*2) {
				crosses.add(sq.y+newLen);
				crosses.add(sq.y+newLen*2);
				Square square = new Square(3,sq.x+newLen*2, sq.y, newLen);
				ret.add(square);
			} else {
				crosses.add(sq.y+newLen);
				crosses.add(sq.y+newLen*2);
				Square square = new Square(3,sq.x+newLen*2, sq.y, newLen);
				ret.add(square);
				square = new Square(0, sq.x+newLen*2, sq.y+newLen*2, newLen);
				ret.add(square);
			}
			break;
		case 1:
			if (relativeX< newLen) {
				Square square = new Square(0, sq.x,sq.y,newLen);
				ret.add(square);
			} else if (relativeX == newLen) {
				crosses.add(sq.y+newLen);
			} else if (relativeX<newLen*2) {
				crosses.remove(sq.y);
				crosses.add(sq.y+newLen);
				Square square = new Square(1, sq.x+newLen, sq.y+newLen, newLen);
				ret.add(square);
			} else if (relativeX == newLen*2) {
				crosses.add(sq.y+newLen);
				Square square = new Square(2,sq.x+newLen*2, sq.y, newLen);
				ret.add(square);
			} else {
				Square square = new Square(2,sq.x+newLen*2, sq.y, newLen);
				ret.add(square);
			}
			break;
		case 2:
			if (relativeX< newLen) {
				crosses.add(sq.y+newLen);
				crosses.add(sq.y+newLen*2);
				Square square = new Square(2, sq.x,sq.y+2*newLen,newLen);
				ret.add(square);
				square = new Square(3, sq.x,sq.y,newLen);
				ret.add(square);
			} else if (relativeX == newLen) {
				crosses.add(sq.y+newLen);
				crosses.add(sq.y+newLen*2);
				Square square = new Square(3, sq.x,sq.y,newLen);
				ret.add(square);
			} else if (relativeX<newLen*2) {
				crosses.remove(sq.y);
				crosses.add(sq.y+newLen);
				Square square = new Square(1, sq.x+newLen, sq.y+newLen, newLen);
				ret.add(square);
			} else if (relativeX == newLen*2) {
				crosses.add(sq.y+newLen);
				Square square = new Square(2,sq.x+2*newLen, sq.y, newLen);
				ret.add(square);
			} else {
				Square square = new Square(2,sq.x+newLen*2, sq.y, newLen);
				ret.add(square);
			}
			break;
		case 3:
			break;
		}
		return ret;
	}

}

class Square {
	int type;
	int x,y;
	int len;
	
	public Square(int type, int x, int y, int len) {
		this.type=type;
		this.x=x;
		this.y=y;
		this.len=len;
	}
}
