import java.util.Random;

public class sbMama {

	int time; //1 to 5
	int recog; //0 to 10
	int eaRatio; // 0 to 5
	int babyC;
	Random rand = new Random();

	public sbMama (int t, int rec, int ea) {
		this.babyC = 2;
		this.time = 10 - t;
		this.recog = rec;
		this.eaRatio = ea;
	}
	
	public int whenFound() { //0 = do nothing, 1 = abandon, 2 = eject
		if (4 >= this.recog) {
			if (2 >= this.eaRatio)
				return 1;
			else
				return 2;
		}
		else
			return 0;
	}
	
	public void addTime() {
		this.time += 1;
	}
	
	public baby newBabe() {
		int babyRecog = this.recog + rand.nextInt(3)-1;
		if (babyRecog < 0)
			babyRecog = 0;
		else if (babyRecog > 10)
			babyRecog = 10;
		int babyEA = this.eaRatio + rand.nextInt(3)-1;
		if (babyEA < 0)
			babyEA = 0;
		else if (babyEA > 5)
			babyEA = 5;
		baby sbBabe = new baby(babyEA, babyRecog);
		return sbBabe;
	}
}
