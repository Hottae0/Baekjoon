#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main() {

	int N;
	int M;

	scanf("%d %d", &N, &M);


	int cnt = 0;
	int sum = 0;

	for (int i = 0; i < N; i++) {
		int tmp = 0;
		scanf("%d", &tmp);

		if (sum  + tmp > M) {
			sum = tmp;
			cnt += 1;
		}
		else if (sum + tmp == M) {
			sum = 0;
			cnt += 1;
		}
		else {
			sum += tmp;
		}
		
	}

	if (sum > 0) cnt += 1;

	printf("%d", cnt);

	return 0;
}



