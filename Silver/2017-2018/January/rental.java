import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class rental {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("rental.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		long money = 0;
		int[] milkprod = new int[N];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			milkprod[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(milkprod);
		int[] prodsorted = new int[N];
		int k = 0;
		for (int i = N - 1; i >= 0; i--){
			prodsorted[k] = milkprod[i];
			k++;
		}
		int[][] milkprice = new int[2][M];
		TreeMap<Integer, Integer> milkpr = new TreeMap<Integer, Integer>();
		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int gallons = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			if (milkpr.containsKey(price)){
				int temp = milkpr.get(price);
				milkpr.put(price, (gallons + temp));
			}
			else {
				milkpr.put(price, gallons);
			}
		}
		int m = 0;
		while (!milkpr.isEmpty()){
			int price = milkpr.lastKey();
			int gallons = milkpr.remove(price);
			milkprice[0][m] = gallons;
			milkprice[1][m] = price;
			m++;
		}
		int[] rent = new int[R];
		for (int i = 0; i < R; i++){
			st = new StringTokenizer(br.readLine());
			rent[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(rent);
		int[] rentsorted = new int[N];
		int counter = N - 1;
		for (int i = R - 1; i >= 0; i--){
			rentsorted[counter] = rent[i];
			if (counter == 0){
				break;
			}
			counter--;
		}
		// rentsorted, milkprice (gallons, price), prodsorted
		int level = 0;
		for (int i = 0; i < N; i++){
			int rentval = rentsorted[i];
			int prod = prodsorted[i];
			long milkmoney = 0;
			boolean temp = true;
			int tlevel = level;
			if (tlevel == m){
				temp = false;
			}
			while (temp){
				if (milkprice[0][tlevel] < prod){
					milkmoney+= (long) milkprice[0][tlevel] * milkprice[1][tlevel];
					prod-=milkprice[0][tlevel];
					tlevel++;
					if (tlevel == m){
						temp = false;
					}
				}
				else {
					milkmoney+= prod * milkprice[1][tlevel];
					temp = false;
				}
			}
			if (rentval >= milkmoney){
				money+=(long)rentval;
				
			}
			else {
				money+=(long)milkmoney;
				prod = prodsorted[i];
				temp = true;
				tlevel = level;
				if (tlevel == m){
					temp = false;
				}
				while (temp){
					if (milkprice[0][tlevel] < prod){
						prod-=milkprice[0][tlevel];
						milkprice[0][tlevel] = 0;
						tlevel++;
						if (tlevel == m){
							temp = false;
						}
					}
					else {
						milkprice[0][tlevel]-=prod;
						temp = false;
					}
				}
				level = tlevel;
			}
		}
		pw.println(money);
		br.close();
		pw.close();
	}
}
