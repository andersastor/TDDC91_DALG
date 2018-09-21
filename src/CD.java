import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CD {

	public static void main(String[] args) {

		int N;
		int M;
		int matches = 0;

		Kattio katt = new Kattio(System.in, System.out);
		while ((N = katt.getInt()) != 0 && (M = katt.getInt()) != 0) {
			matches = 0;
			HashSet <Integer> jack = new HashSet<Integer>();
			for (int i = 0; i < N; i++) {
				jack.add(katt.getInt());
			}
			for (int j = 0; j < M; j++) {
				if (jack.contains(katt.getInt())) {
					matches++;
				}
			}
			System.out.println(matches);
		}
		katt.close();
	}
}
