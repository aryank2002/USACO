import java.util.*;
import java.io.*;

public class taming {
	
	static int[] nums;
	
	static int Checker(int digit, int index){
		if (nums[index] == digit){
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("taming.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		
		int N = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++){
			int[][][] mins = new int[N + 1][N + 1][N + 1];
			for (int a = 0; a <= N; a++){
				for (int b = 0; b <= N; b++){
					for (int c = 0; c <= N; c++){
						mins[a][b][c] = Integer.MAX_VALUE / 2;
					}
				}
			}
			mins[1][1][0] = Checker(0, 1);
			for (int days = 2; days <= N; days++){
				for (int breakout = 1; breakout <= N; breakout++){
					for (int digits = 0; digits <= N; digits++){
						if (digits > 0){
							mins[days][breakout][digits] = mins[days - 1][breakout][digits - 1] + Checker(digits, days);
						}
						else if (digits == 0 && days == 2){
							mins[days][breakout][digits] = mins[days - 1][breakout - 1][0] + Checker(0, 2);
						}
						else if (digits == 0){
							int m = Integer.MAX_VALUE;
							for (int d = 0; d <= N ; d++){
								m = Math.min(m, mins[days - 1][breakout - 1][d] + Checker(0, days));
							}
							mins[days][breakout][digits] = m;
						}
						//end
					}
				}
			}
			
			
			int max = Integer.MAX_VALUE;
			for (int digits = 0; digits <= N; digits++){
				max = Math.min(max, mins[N][i][digits]);
			}
			
			pw.println(max);
			// end
		}
		
		pw.close();
		br.close();
	}
}
