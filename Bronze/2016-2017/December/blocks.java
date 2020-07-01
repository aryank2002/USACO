
import java.io.*;
import java.util.*;

public class blocks {

	public static void main(String[] args) throws Exception {
    // This code does receive full credit in the USACO grading system but it is not a very good example
    // of concise and clear programming. This was the first USACO question I attempted and it reflects
    // my lack of experience with competitive programming at the time.
    
		char character;

		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int e = 0;
		int f = 0;
		int g = 0;
		int h = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int m = 0;
		int n = 0;
		int o = 0;
		int p = 0;
		int q = 0;
		int r = 0;
		int s = 0;
		int t = 0;
		int u = 0;
		int v = 0;
		int w = 0;
		int x = 0;
		int y = 0;
		int z = 0;

		int a2 = 0;
		int b2 = 0;
		int c2 = 0;
		int d2 = 0;
		int e2 = 0;
		int f2 = 0;
		int g2 = 0;
		int h2 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		int m2 = 0;
		int n2 = 0;
		int o2 = 0;
		int p2 = 0;
		int q2 = 0;
		int r2 = 0;
		int s2 = 0;
		int t2 = 0;
		int u2 = 0;
		int v2 = 0;
		int w2 = 0;
		int x2 = 0;
		int y2 = 0;
		int z2 = 0;

		File file = new File("blocks.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		for (int mn = 0; mn < N; mn++) {
			StringTokenizer sd = new StringTokenizer(br.readLine());

			String string1 = sd.nextToken();
			String string2 = sd.nextToken();

			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File("stuff"))));

			PrintWriter writer1 = new PrintWriter(new BufferedWriter(new FileWriter(new File("stuff1"))));

			writer.println(string1);
			writer1.println(string2);

			writer.close();
			writer1.close();
			

			FileReader fr = new FileReader("stuff");
			FileReader fr2 = new FileReader("stuff1");
			while ((character = (char) fr.read()) != '\n') {
				char char1 = character;
				if (char1 == 'a') {
					a++;
				}
				if (char1 == 'b') {
					b++;
				}
				if (char1 == 'c') {
					c++;
				}
				if (char1 == 'd') {
					d++;
				}
				if (char1 == 'e') {
					e++;
				}
				if (char1 == 'f') {
					f++;
				}
				if (char1 == 'g') {
					g++;
				}
				if (char1 == 'h') {
					h++;
				}
				if (char1 == 'i') {
					i++;
				}
				if (char1 == 'j') {
					j++;
				}
				if (char1 == 'k') {
					k++;
				}
				if (char1 == 'l') {
					l++;
				}
				if (char1 == 'm') {
					m++;
				}
				if (char1 == 'n') {
					n++;
				}
				if (char1 == 'o') {
					o++;
				}
				if (char1 == 'p') {
					p++;
				}
				if (char1 == 'q') {
					q++;
				}
				if (char1 == 'r') {
					r++;
				}
				if (char1 == 's') {
					s++;
				}
				if (char1 == 't') {
					t++;
				}
				if (char1 == 'u') {
					u++;
				}
				if (char1 == 'v') {
					v++;
				}
				if (char1 == 'w') {
					w++;
				}
				if (char1 == 'x') {
					x++;
				}
				if (char1 == 'y') {
					y++;
				}
				if (char1 == 'z') {
					z++;
				}
			}
			int a1 = a;
			int b1 = b;
			int c1 = c;
			int d1 = d;
			int e1 = e;
			int f1 = f;
			int g1 = g;
			int h1 = h;
			int i1 = i;
			int j1 = j;
			int k1 = k;
			int l1 = l;
			int m1 = m;
			int n1 = n;
			int o1 = o;
			int p1 = p;
			int q1 = q;
			int r1 = r;
			int s1 = s;
			int t1 = t;
			int u1 = u;
			int v1 = v;
			int w1 = w;
			int x1 = x;
			int y1 = y;
			int z1 = z;
			 a = 0;
			 b = 0;
			 c = 0;
			 d = 0;
			 e = 0;
			 f = 0;
			 g = 0;
			 h = 0;
			 i = 0;
			 j = 0;
			 k = 0;
			 l = 0;
			 m = 0;
			 n = 0;
			 o = 0;
			 p = 0;
			 q = 0;
			 r = 0;
			 s = 0;
			 t = 0;
			 u = 0;
			 v = 0;
			 w = 0;
			 x = 0;
			 y = 0;
			 z = 0;

			while ((character = (char) fr2.read()) != '\n') {
				char char1 = (char) character;
				if (char1 == 'a') {
					a++;
				}
				if (char1 == 'b') {
					b++;
				}
				if (char1 == 'c') {
					c++;
				}
				if (char1 == 'd') {
					d++;
				}
				if (char1 == 'e') {
					e++;
				}
				if (char1 == 'f') {
					f++;
				}
				if (char1 == 'g') {
					g++;
				}
				if (char1 == 'h') {
					h++;
				}
				if (char1 == 'i') {
					i++;
				}
				if (char1 == 'j') {
					j++;
				}
				if (char1 == 'k') {
					k++;
				}
				if (char1 == 'l') {
					l++;
				}
				if (char1 == 'm') {
					m++;
				}
				if (char1 == 'n') {
					n++;
				}
				if (char1 == 'o') {
					o++;
				}
				if (char1 == 'p') {
					p++;
				}
				if (char1 == 'q') {
					q++;
				}
				if (char1 == 'r') {
					r++;
				}
				if (char1 == 's') {
					s++;
				}
				if (char1 == 't') {
					t++;
				}
				if (char1 == 'u') {
					u++;
				}
				if (char1 == 'v') {
					v++;
				}
				if (char1 == 'w') {
					w++;
				}
				if (char1 == 'x') {
					x++;
				}
				if (char1 == 'y') {
					y++;
				}
				if (char1 == 'z') {
					z++;
				}
			}
			if (a1 < a) {
				a1 = a;
			}
			if (b1 < b) {
				b1 = b;
			}
			if (c1 < c) {
				c1 = c;
			}
			if (d1 < d) {
				d1 = d;
			}
			if (e1 < e) {
				e1 = e;
			}
			if (f1 < f) {
				f1 = f;
			}
			if (g1 < g) {
				g1 = g;
			}
			if (h1 < h) {
				h1 = h;
			}
			if (i1 < i) {
				i1 = i;
			}
			if (j1 < j) {
				j1 = j;
			}
			if (k1 < k) {
				k1 = k;
			}
			if (l1 < l) {
				l1 = l;
			}
			if (m1 < m) {
				m1 = m;
			}
			if (n1 < n) {
				n1 = n;
			}
			if (o1 < o) {
				o1 = o;
			}
			if (p1 < p) {
				p1 = p;
			}
			if (q1 < q) {
				q1 = q;
			}
			if (r1 < r) {
				r1 = r;
			}
			if (s1 < s) {
				s1 = s;
			}
			if (t1 < t) {
				t1 = t;
			}
			if (u1 < u) {
				u1 = u;
			}
			if (v1 < v) {
				v1 = v;
			}
			if (w1 < w) {
				w1 = w;
			}
			if (x1 < x) {
				x1 = x;
			}
			if (y1 < y) {
				y1 = y;
			}
			if (z1 < z) {
				z1 = z;
			}

			a2 = a2 + a1;
			b2 = b2 + b1;
			c2 = c2 + c1;
			d2 = d2 + d1;
			e2 = e2 + e1;
			f2 = f2 + f1;
			g2 = g2 + g1;
			h2 = h2 + h1;
			i2 = i2 + i1;
			j2 = j2 + j1;
			k2 = k2 + k1;
			l2 = l2 + l1;
			m2 = m2 + m1;
			n2 = n2 + n1;
			o2 = o2 + o1;
			p2 = p2 + p1;
			q2 = q2 + q1;
			r2 = r2 + r1;
			s2 = s2 + s1;
			t2 = t2 + t1;
			u2 = u2 + u1;
			v2 = v2 + v1;
			w2 = w2 + w1;
			x2 = x2 + x1;
			y2 = y2 + y1;
			z2 = z2 + z1;
			fr.close();
			fr2.close();
			a1 = 0;
			 b1 = 0;
			 c1 = 0;
			 d1 = 0;
			 e1 = 0;
			 f1 = 0;
			 g1 = 0;
			 h1 = 0;
			 i1 = 0;
			 j1 = 0;
			 k1 = 0;
			 l1 = 0;
			 m1 = 0;
			 n1 = 0;
			 o1 = 0;
			 p1 = 0;
			 q1 = 0;
			 r1 = 0;
			 s1 = 0;
			 t1 = 0;
			 u1 = 0;
			 v1 = 0;
			 w1 = 0;
			 x1 = 0;
			 y1 = 0;
			 z1 = 0;
			 
			 a = 0;
			 b = 0;
			 c = 0;
			 d = 0;
			 e = 0;
			 f = 0;
			 g = 0;
			 h = 0;
			 i = 0;
			 j = 0;
			 k = 0;
			 l = 0;
			 m = 0;
			 n = 0;
			 o = 0;
			 p = 0;
			 q = 0;
			 r = 0;
			 s = 0;
			 t = 0;
			 u = 0;
			 v = 0;
			 w = 0;
			 x = 0;
			 y = 0;
			 z = 0;
		}
		br.close();
		
		File outputfile = new File("blocks.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputfile));
		PrintWriter pw = new PrintWriter(bw);
		pw.println(a2);
		pw.println(b2);
		pw.println(c2);
		pw.println(d2);
		pw.println(e2);
		pw.println(f2);
		pw.println(g2);
		pw.println(h2);
		pw.println(i2);
		pw.println(j2);
		pw.println(k2);
		pw.println(l2);
		pw.println(m2);
		pw.println(n2);
		pw.println(o2);
		pw.println(p2);
		pw.println(q2);
		pw.println(r2);
		pw.println(s2);
		pw.println(t2);
		pw.println(u2);
		pw.println(v2);
		pw.println(w2);
		pw.println(x2);
		pw.println(y2);
		pw.println(z2);

		pw.close();
	}
}
