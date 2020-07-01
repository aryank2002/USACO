import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class badmilk {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("badmilk.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("badmilk.out"));
		PrintWriter pw = new PrintWriter(bw);
		
		int numpeople = Integer.parseInt(st.nextToken());
		int nummilk = Integer.parseInt(st.nextToken());
		int lines = Integer.parseInt(st.nextToken());
		int sicklines = Integer.parseInt(st.nextToken());
		
		List<ArrayList<Integer>> milkconsum = new ArrayList<ArrayList<Integer>>();
		List<ArrayList<Integer>> milktime = new ArrayList<ArrayList<Integer>>();
		
		List<Integer> pbadmilks = new ArrayList<Integer>();
		
		List<Integer> sickpeople = new ArrayList<Integer>();
		List<Integer> sicktime = new ArrayList<Integer>();
		
		for (int i = 0; i < numpeople; i++){
			milkconsum.add(new ArrayList<Integer>());
			milktime.add(new ArrayList<Integer>());
		}
		
		int person = 0;
		int milk = 0;
		int time = 0;
		
		for (int i = 0; i < lines; i++){
			st = new StringTokenizer(br.readLine());
			person = Integer.parseInt(st.nextToken());
			milk = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			milkconsum.get(person-1).add(milk);
			milktime.get(person-1).add(time);
		}
		
		for (int i = 0; i < sicklines; i++){
			st = new StringTokenizer(br.readLine());
			person = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			sickpeople.add(person);
			sicktime.add(time);
		}
		
		for (int i = 1; i < nummilk + 1; i++){
			boolean badmilk = true;
			for (int k = 0; k < sicklines; k++){
				boolean drank = false;
				for (int m = 0; m < milkconsum.get(sickpeople.get(k)-1).size(); m++){
					if ( (i == milkconsum.get(sickpeople.get(k)-1).get(m)) && (milktime.get(sickpeople.get(k)-1).get(m)< sicktime.get(k)) ){
						drank = true;
					}
				}
				if (drank == false){
					badmilk = false;
				}
			}
			if (badmilk == true){
				pbadmilks.add(i);
			}
		}
		
		int temporary = 0;
		for (int i = 0; i < pbadmilks.size(); i++){
			int people = 0;
			for (int k = 0; k < numpeople; k++){
				
				for (int n = 0; n < milkconsum.get(k).size(); n++){
					if (pbadmilks.get(i) == milkconsum.get(k).get(n)){
						people++;
						break;
					}
				}
			}
			if (people > temporary){
				temporary = people;
			}
		}
		
		pw.println(temporary);
		br.close();
		pw.close();
	}
}
