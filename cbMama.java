import java.util.Random;

public class cbMama {
	
	baby baby;
	int babyC;
	int retal; // 0 to 10, retaliation
	int time;
	Random rand = new Random();
	
	public cbMama(int r, int t) {
		this.babyC = 10;
		this.baby = null;
		this.retal = r;
		this.time = t;
	}
	
	public void addTime() {
		this.time += 1;
	}
	
	public int react() {
		if (retal >= 6) {
			return 1;
		}
		else
			return 0;
	}
	
	public baby newBabe() {
		int babyRet = this.retal + (rand.nextInt(3) - 1); //mutation of 0,1 in either direction
		baby cbBabe = new baby(babyRet, this);
		return cbBabe;
	}
}
