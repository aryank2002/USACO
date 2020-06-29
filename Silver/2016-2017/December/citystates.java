import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class citystates {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		HashMap<String, ArrayList<String>> cities = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> states = new HashMap<String, ArrayList<String>>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String[][] data = new String[2][N];
		
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			String city = st.nextToken();
			String scity = city.substring(0, 2);
			String state = st.nextToken();
			data[0][i] = scity;
			data[1][i] = state;
			if (cities.containsKey(scity)){
				cities.get(scity).add(state);
			}
			else {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(state);
				cities.put(scity, temp);
			}
			if (states.containsKey(state)){
				states.get(state).add(scity);
			}
			else {
				ArrayList<String> temp1 = new ArrayList<String>();
				temp1.add(scity);
				states.put(state, temp1);
			}
		}
		int counter = 0;
		for (int i = 0; i < N; i++){
			if (cities.containsKey(data[1][i])){
				String city1 = data[0][i];
				String state1 = data[1][i];
				for (String temp2: cities.get(state1)){
					if (temp2.equals(city1) && !temp2.equals(state1)){
						counter++;
					}
				}
			}
		}
		pw.println(counter/2);
		pw.close();
		br.close();
	}
}
