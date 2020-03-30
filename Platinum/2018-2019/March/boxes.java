import java.io.*;
import java.util.*;

public class boxes extends Grader {

	static int N;
	static int Q;
	static int num = 100000;
	static boolean rand = false;
	
	static ArrayList<Integer>[] adj = new ArrayList[num];
	static int[] depth = new int[num];
	static int[][] jumps = new int[num][17];
	static int[] child = new int[num];
	static int[][] loc = new int[num][2];

	public static void main(String args[]) throws IOException { 
		new boxes().run(); 
	}

	public void addRoad(int a, int b) {
		if (rand == false) {
			for (int i = 0; i < num; i++) adj[i] = new ArrayList<Integer>();
			rand = true;
		}
		adj[a].add(b);
		adj[b].add(a);
	}

	public void buildFarms(){
		N = getN();
		Q = getQ();
		DFS(1, 1);
		build(1, 1, N - 1, N - 1);
		for (int i = 0; i < N; i++) {
			setFarmLocation(i, loc[i][0] + 1, loc[i][1] + 1);
		}
		for (int d = 1; d < 17; d++){
			for (int i = 0; i < N; i++){
				jumps[i][d] = jumps[jumps[i][d - 1]][d - 1];
			}
		}
	}

	public void notifyFJ(int a, int b){
		int[] t = LCA(a, b);
		addBox(t[0] + 1, t[1] + 1, t[2] + 1, t[3] + 1);
		if (t[4] != -1) {
			addBox(t[4] + 1, t[5] + 1, t[6] + 1, t[7] + 1);
		}
	}
	
	static void build(int node, int par, int rightx, int topy){
		loc[node][0] = rightx;
		loc[node][1] = topy;
		
		int x = rightx - child[node] + 1;
		int y = topy;
		
		for (int val: adj[node]){
			if (val == par) continue;
			
			x += child[val];
			build(val, node, x - 1, y - 1);
			y -= child[val];
		}
	}
	
	static int DFS(int node, int par){
		jumps[node][0] = par;
		depth[node] = depth[par] + 1;
		child[node] = 1;

		for (int val: adj[node]){
			if (val == par) continue;
			child[node] += DFS(val, node);
		}
		return child[node];
	}

	static int[] LCA (int a, int b) {
		int i = a;
		int k = b;
		boolean swap = false;
		
		if (depth[a] > depth[b]) {
			swap = true;
			int c = a;
			a = b;
			b = c;
		}
		int d = depth[b] - depth[a];
		for (int h = 16; h >= 0; h--){
			if ((1 << h) <= d) {
				b = jumps[b][h];
				d -= 1 << h;
			}
		}
		for (d = 16; d >= 0; d--) {
			if (jumps[a][d] != jumps[b][d]) {
				a = jumps[a][d];
				b = jumps[b][d];
			}
		}
		int[] t = new int[8];
		if (a != b) {
			if (swap){
				t[0] = loc[k][0]; t[1] = loc[k][1]; t[2] = loc[a][0]; t[3] = loc[a][1];
				a = jumps[a][0];
				t[4] = loc[i][0]; t[5] = loc[i][1]; t[6] = loc[a][0]; t[7] = loc[a][1];
			}
			else{
				t[0] = loc[i][0]; t[1] = loc[i][1]; t[2] = loc[a][0]; t[3] = loc[a][1];
				a = jumps[a][0];
				t[4] = loc[k][0]; t[5] = loc[k][1]; t[6] = loc[a][0]; t[7] = loc[a][1];
			}
		}
		else{
			if (loc[i][0] < loc[k][0]) {
				t[0] = loc[i][0]; t[1] = loc[i][1]; t[2] = loc[k][0]; t[3] = loc[k][1];
			}
			else {
				t[0] = loc[k][0]; t[1] = loc[k][1]; t[2] = loc[i][0]; t[3] = loc[i][1];
			}
			t[4] = -1;
		}
		return t;
	}
}
