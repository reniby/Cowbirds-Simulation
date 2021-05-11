import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class cowbirds {
	
	public static void main(String[] args) {
		Random rand = new Random();
		sbMama[] songbirds = new sbMama[100];
		cbMama[] cowbirds = new cbMama[100];
		
		int gen = 0;
		int time = 0;
		
		for(int i = 0; i < songbirds.length; i++) {
			songbirds[i] = new sbMama(time, rand.nextInt(10)+1, rand.nextInt(6));
		}
		for (int h = 0; h < cowbirds.length; h++) {
			cowbirds[h] = new cbMama(rand.nextInt(11), time);
		}
		
		
		while (gen < 10) {
			
			//make sb nests, works
			nest[] nests = new nest[songbirds.length];
			int avgEA = 0, avgRec = 0;
			int lowEA = 5, highEA = 0, lowRec = 10, highRec = 0;
			int avgRet = 0, lowRet = 10, highRet = 0;
			
			for (int j = 0; j < songbirds.length; j++) {
				nests[j] = new nest(songbirds[j]); //auto makes 3 babies
				
				avgEA += songbirds[j].eaRatio;
				if (songbirds[j].eaRatio < lowEA)
					lowEA = songbirds[j].eaRatio;
				if (songbirds[j].eaRatio > highEA)
					highEA = songbirds[j].eaRatio;
				
				avgRec += songbirds[j].recog;
				if (songbirds[j].recog < lowRec)
					lowRec = songbirds[j].recog;
				if (songbirds[j].recog > highRec)
					highRec = songbirds[j].recog;
				
			}
			for (int k = 0; k < cowbirds.length; k++) {
				cowbirds[k].baby = cowbirds[k].newBabe();
				
				avgRet += cowbirds[k].retal;
				if (cowbirds[k].retal < lowRet)
					lowRet = cowbirds[k].retal;
				else if (cowbirds[k].retal > highRet)
					highRet = cowbirds[k].retal;
			}
			
			if (songbirds.length > 0 ) {
				avgEA = avgEA / songbirds.length;
				avgRec = avgRec / songbirds.length;
				System.out.println("SB: average EA = " + avgEA + ", high EA = " + highEA + ", low EA = " + lowEA);
				System.out.println("SB: average Rec = " + avgRec + ", high Rec = " + highRec + ", low Rec = " + lowRec);
			}
			if (cowbirds.length > 0) {
				avgRet = avgRet / cowbirds.length;
				System.out.println("CB: average Ret = " + avgRet + ", high Ret = " + highRet + ", low Ret = " + lowRet);
			}
					
			time = 0;
			
			while (time <= 10) {
				
				//add cb eggs to mamas 
				for (int k = 0; k < cowbirds.length; k++) {
					if (cowbirds[k].baby != null && cowbirds[k].baby.inNest == false) {
						for (int l = 0; l < songbirds.length; l++) {
							if (nests[l].cbi == null || nests[l].cbii == null) {
								nests[l].addCB(cowbirds[k].baby, cowbirds[k]);
								l = songbirds.length;
							}
						}
					}
				}
				
				//whenFound -> retal
				for (int j = 0; j < songbirds.length; j++) {
					if (nests[j].cbi != null || nests[j].cbii != null) {
						int decide = nests[j].mom.whenFound();
						if (decide == 1) { //abandon
							nests[j].setDestroy(time);
						}
						else if (decide == 2) { //kill
							nests[j].cbi = null;
							nests[j].cbii = null;
							
							if (nests[j].cbmom.babyC >= 2) {
								nests[j].cbmom.baby = nests[j].cbmom.newBabe();
								nests[j].cbmom.babyC -= 1;
							}
							else {
								nests[j].cbmom.babyC = 0;
								nests[j].cbmom.baby = null;
							}					
							
							if (nests[j].cbmom.react() == 1) {
								nests[j].setDestroy(time);
							}
						}
					}
				}
				
				
				
				//feed
				for (int j = 0; j < songbirds.length; j++) {
					nests[j].feed(rand.nextInt(3)+3);
				}
				
				//addTime (nest and cbMom)
				for (int j = 0; j < songbirds.length; j++) {
					nests[j].destroyCheck(time);
					nests[j].addTime();			
				}
				for (int j = 0; j < cowbirds.length; j++) {
					cowbirds[j].addTime();			
				}
							
				
				time++;
				
			}
			
			ArrayList<sbMama> sbnew = new ArrayList<sbMama>();
			ArrayList<cbMama> cbnew = new ArrayList<cbMama>();
			
			for (int j = 0; j < songbirds.length; j++) {
				
				if (nests[j].sbi.size > 40)	{
					sbMama nextGenSbi = new sbMama(0, nests[j].sbi.recog, nests[j].sbi.eaRatio);
					sbnew.add(nextGenSbi);
				}
				if (nests[j].sbii.size > 40)	{
					sbMama nextGenSbii = new sbMama(0, nests[j].sbii.recog, nests[j].sbii.eaRatio);
					sbnew.add(nextGenSbii);
				}
				if (nests[j].sbiii.size > 40)	{
					sbMama nextGenSbiii = new sbMama(0, nests[j].sbiii.recog, nests[j].sbiii.eaRatio);
					sbnew.add(nextGenSbiii);
				}
				if (nests[j].cbi != null)	{
					cbMama nextGenCb = new cbMama(nests[j].cbi.retal, 0);
					cbnew.add(nextGenCb);
				}
				if (nests[j].cbii != null)	{
					cbMama nextGenCb = new cbMama(nests[j].cbii.retal, 0);
					cbnew.add(nextGenCb);
				}
				if (nests[j].cbiii != null)	{
					cbMama nextGenCb = new cbMama(nests[j].cbiii.retal, 0);
					cbnew.add(nextGenCb);
				}
			}
			
			//add in so # of cb and sb moms is equal, account for cowbirds migrating in
			for (int h = 0; h < ((sbnew.size()-cbnew.size())/2); h++) {
				cbMama nextGenCb = new cbMama(rand.nextInt(11), 0);
				cbnew.add(nextGenCb);
			}
			
			songbirds = sbnew.toArray(new sbMama[sbnew.size()]);
			cowbirds = cbnew.toArray(new cbMama[cbnew.size()]);
			
			gen++;
			
			System.out.println("\n" + gen + ": sb= " + songbirds.length + " cb= " + cowbirds.length + "\n");
			
//			boolean run = true;
//			while (run == true) {
//				Scanner scan = new Scanner(System.in);
//				System.out.println("\nWould you like to see the next generation?");
//				String response = scan.nextLine();
//				if (response.equals("yes"))
//					run = false;
//				else
//					System.exit(0);
//			}
			
		}
	}	
}
	
