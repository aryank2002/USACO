import java.io.*;
import java.util.*;

public class cowpatibility {
	
	public static void main(String[] args) throws IOException {
    // THIS IS AN ALMOST COMPLETE SOLUTION
    // For full solution, do not use hash set to store flavor strings, to high of a constant
    // Use a more efficient set of create a class to do this
  
		BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		
		int N = Integer.parseInt(st.nextToken());
		int[][] data = new int[N][5];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < 5; k++){
				data[i][k] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(data[i]);
		}
		
		long total = (long) N * (N - 1) / 2;
		HashMap<String, Integer>[] nums = new HashMap[6];
		for (int i = 1; i < 6; i++){
			nums[i] = new HashMap<String, Integer>();
		}
		
		for (int i = 0; i < N; i++){
			
			for (int a = 0; a < 2; a++){
				String s1 = "";
				if (a == 1) s1 += Integer.toString(data[i][0]) + " ";
				for (int b = 0; b < 2; b++){ 
					String s2 = s1;
					if (b == 1) s2 += Integer.toString(data[i][1]) + " ";
					for (int c = 0; c < 2; c++){
						String s3 = s2;
						if (c == 1) s3 += Integer.toString(data[i][2]) + " ";
						for (int d = 0; d < 2; d++){
							String s4 = s3;
							if (d == 1) s4 += Integer.toString(data[i][3]) + " ";
							for (int e = 0; e < 2; e++){
								String s = s4;
								if (e == 1) s += Integer.toString(data[i][4]) + " ";
								
								int count = a + b + c + d + e;
								if (count == 0) continue;
								
								if (nums[count].containsKey(s)){
									// nums[count].replace(s, nums[count].get(s) + 1);
									int t = nums[count].get(s);
									nums[count].put(s, t + 1);
									total = total - (2 * (count % 2) - 1) * t;
								}
								else {
									nums[count].put(s, 1);
								}
							}
						}
					}
				}
			}
		}
		
		pw.println(total);
		br.close();
		pw.close();
	}
}
