
public class nest {
	
	sbMama mom;
	cbMama cbmom;
	baby cbi, cbii, cbiii, sbi, sbii, sbiii;
	baby[] sizes = new baby[6];
	int loop = 3, destroyTime = -1;

	public nest(sbMama m) {
		this.mom = m;
		this.cbmom = null;
		this.sbi = mom.newBabe();
		this.sbii = mom.newBabe();
		this.sbiii = mom.newBabe();
		this.cbi = null;
		this.cbii = null;
		this.cbiii = null;
		this.sizes[0] = sbi;
		this.sizes[1] = sbii;
		this.sizes[2] = sbiii;
		this.sizes[3] = this.cbi;
		this.sizes[4] = this.cbii;
		this.sizes[5] = this.cbiii;
	}
	
	public void addCB(baby cowbird, cbMama m) {
		if (cbi == null) {
			this.cbi = cowbird;
			this.cbmom = m;
			this.cbmom.baby = this.cbi;
			this.cbi.inNest = true;
			this.loop++;
		}
		else if (cbii == null) {
			this.cbii = cowbird;
			this.cbmom = m;
			this.cbmom.baby = this.cbii;
			this.cbii.inNest = true;
			this.loop++;
		}
		else {
			this.cbiii = cowbird;
			this.cbmom = m;
			this.cbmom.baby = this.cbii;
			this.cbiii.inNest = true;
			this.loop++;
		}
	}
	
	public void print() {
		for (int i = 0; i < this.loop; i++) {
			if(this.sizes[i] != null)
				System.out.println(this.sizes[i].size + " " + this.sizes[i].growthRate);
			else
				System.out.println("null");
		}
		System.out.println("");
	}
	
	public void setDestroy(int time) {
		this.destroyTime = time + 12;
	}
	
	public void destroyCheck(int time) {
		if (this.destroyTime == time)
			this.destroy();
	}
	
	public void destroy() {
		if (this.mom.babyC > 0) {
			this.sbi = mom.newBabe();
			this.sbii = mom.newBabe();
			this.sbiii = mom.newBabe();
			this.cbi = null;
			this.cbii = null;
			this.cbiii = null;
			this.sizes[0] = sbi;
			this.sizes[1] = sbii;
			this.sizes[2] = sbiii;
			this.sizes[3] = this.cbi;
			this.sizes[4] = this.cbii;
			this.sizes[5] = this.cbiii;
			this.loop = 3;
			this.mom.babyC -= 1;
		}
		else {
			this.sbi = null;
			this.sbii = null;
			this.sbiii = null;
			this.cbi = null;
			this.cbii = null;
			this.cbiii = null;
			this.loop = 0;
		}
	}
	
	public void addTime() {
		for (int i = 0; i < this.loop; i++) {
			this.sizes[i].addTime();
			this.sizes[i].updateSize();
		}
		this.mom.addTime();
	}
	
	public void feed(int food) {
		
		if (this.cbiii != null) {
			this.sizes[5] = cbiii;
			this.sizes[4] = cbii;
			this.sizes[3] = cbi;
			this.loop = 5;
		}
		else if (this.cbii != null) {
			this.sizes[4] = cbii;
			this.sizes[3] = cbi;
			this.loop = 4;
		}
		else if (this.cbi != null) {
			this.sizes[3] = cbi;
			this.loop = 3;
		}
		else
			this.loop = 3;
		
		baby[] foodOrder = this.sizes.clone();
		
		//insertion sort (works, was indv tested)
		for (int i = 1; i < this.loop; i++) {
			for (int temp = 0; temp < i; temp++) {
				if (foodOrder[i].getSize() < foodOrder[temp].getSize()) {
					baby save = foodOrder[temp];
					foodOrder[temp] = foodOrder[i];	
					
					for (int is = i; is > temp+1; is--)
						foodOrder[i] = foodOrder[i-1];
					foodOrder[temp+1] = save;
				}		
			}
		}
		
		
		
		int current = 0;
		while (food != 0) {
			if (this.sizes[current].fed == false)
				this.sizes[current].fed = true;
			else {
				if (this.sizes[current].type == 0)
					this.sizes[current].growthRate += 0.5;
				else
					this.sizes[current].growthRate += 1;
			}
			
			if (current < this.loop-1)
				current++;
			else
				current = 0;
			
			food--;
		}
		
		for (int k = 0; k < this.loop; k++) {
			if (this.sizes[k].fed == false) {
				this.sizes[k].growthRate -= 1;			
			}
		}
	
	}
}
