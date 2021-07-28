import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] primes = getPrimeNumbers(N);
		int answer = 0;

		if(N == 1) answer = 0;
		else answer = count(N, primes);

		System.out.println(answer);
	}

	private static int count(int N, int[] primes) {
		int cnt = 0;
		int left = 0;
		int right = 1;

		if(isPrime(N)) cnt++;
		if(primes.length == 1) return cnt;

		int sum = primes[left] + primes[right];
		while(left < right && right < primes.length) {
			if(sum == N && right+1 < primes.length) {
				cnt++;
				sum -= primes[left++];
				sum += primes[++right];
			}
			else if(sum < N && right+1 < primes.length) {
				sum += primes[++right];
			}
			else if(sum > N) {
				sum -= primes[left++];
			}
		}
		return cnt;
	}

	private static int[] getPrimeNumbers(int N) {
		List<Integer> primeList = new ArrayList<>();

		int num = 2;
		while(num <= N) {
			if(isPrime(num)) {
				primeList.add(num);
			}
			num++;
		}

		int[] primes = new int[primeList.size()];
		for(int i=0; i<primes.length; i++) {
			primes[i] = primeList.get(i);
		}
		return primes;
	}

	private static boolean isPrime(int n) {
		for(int i=2; i<=Math.sqrt(n); i++) {
			if(n%i == 0) return false;
		}
		return true;
	}
}