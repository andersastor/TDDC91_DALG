import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;

public class TourDeFrance {

	public static void main (String[] args) {
		
		class Kattio extends PrintWriter {
			public Kattio(InputStream i) {
				super(new BufferedOutputStream(System.out));
				r = new BufferedReader(new InputStreamReader(i));
			}
			public Kattio(InputStream i, OutputStream o) {
				super(new BufferedOutputStream(o));
				r = new BufferedReader(new InputStreamReader(i));
			}
			public int getInt() {
				return Integer.parseInt(nextToken());
			}
			private BufferedReader r;
			private String line;
			private StringTokenizer st;
			private String token;
			private String peekToken() {
				if (token == null)
					try {
						while (st == null || !st.hasMoreTokens()) {
							line = r.readLine();
							if (line == null) return null;
							st = new StringTokenizer(line);
						}
						token = st.nextToken();
					} catch (IOException e) { }
				return token;
			}
			private String nextToken() {
				String ans = peekToken();
				token = null;
				return ans;
			}
		}
		
		Kattio katt = new Kattio(System.in, System.out);
		int f; // front sprockets
		int r; // rear sprockets
		double[] fa; // teeth on i'th sprocket in front
		double[] ra; // teeth on i'th sprocket in rear
		double[] ratios; // ratios of rear and front teeth

		while (true) {
			f = katt.getInt();
			if (f == 0) { // if input is 0, exit program
				break;
			}
			r = katt.getInt();
			fa = new double[f];
			ra = new double[r];
			for (int i = 0; i < f; i++) {
				fa[i] = katt.getInt();
			}
			for (int i = 0; i < r; i++) {
				ra[i] = katt.getInt();
			}
			ratios = new double[f*r];
			int index = 0;
			for (int i = 0; i < fa.length; i++) {
				for (int j = 0; j < ra.length; j++, index++) {
					ratios[index] = (ra[j] / fa[i]); // ratio =  rear teeth / front teeth
				}
			}
			Arrays.sort(ratios); // sort the ratios in ascending order
			double res = 0;
			for (int i = 1; i < ratios.length; i++) {
				double x = ratios[i] / ratios[i - 1];
				if (x > res) {
					res = x;
				}
//				res = res < x ? x : res;
			}
			String ans = String.valueOf(Math.round(res*100.00)/100.00);
			if(ans.length() == 1) {
				System.out.println(ans + ".00");
			} else if(ans.length() == 3) {
				System.out.println(ans + "0");
			} else {
				System.out.println(ans);
			}
		}
		
		katt.close();
	}
}