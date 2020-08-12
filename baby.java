
public class baby {
	
	final int SBEGG = 2, CBEGG = 1;
	int eaRatio, recog, retal, type, size; //type: 0 is sb, 1 is cb
	boolean fed, inNest;
	double growthRate;
	int maturity;
	cbMama mom;
	
	
	public baby(int ea, int rec) {
		this.eaRatio = ea;
		this.recog = rec;
		this.type = 0;
		this.maturity = 0; //how long its been alive
		this.size = 0;
		this.fed = false;
		this.growthRate = 1; //how fast it grows
	}
	
	public baby(int ret, cbMama m) {
		this.retal = ret;
		this.type = 1;
		this.maturity = 0;
		this.size = 0;
		this.fed = false;
		this.growthRate = 1.5;
		this.mom = m;
		this.inNest = false;
	}
	
	public void addTime() {
		this.maturity += 1;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void updateSize() {
		if (this.type == 0) {
			if (this.maturity > SBEGG)
				this.size += this.growthRate;
			else 
				this.growthRate = 1;
		}
		else if (this.type == 1) {
			if (this.maturity > CBEGG)
				this.size += this.growthRate;
			else
				this.growthRate = 1.5;
		}		
	}
	

}
