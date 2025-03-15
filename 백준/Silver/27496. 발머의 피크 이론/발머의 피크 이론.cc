#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main() {
	int N; int L;
	scanf("%d %d", &N, &L);

	long long* ac_sum = (long long*)malloc(sizeof(long long) * (N + 1));

	ac_sum[0] = 0;

	for (int i = 1; i < N + 1; i++) {
		int tmp;
		if (i == N) {
			scanf("%d", &tmp);
		}
		else {
			scanf("%d ", &tmp);
		}

		ac_sum[i] = ac_sum[i - 1] + tmp;
	}

	int cnt = 0;
	
	for (int i = 1; i < L; i++) {
		if (129 <= ac_sum[i] && ac_sum[i] <= 138) {
			cnt += 1;
		}
	}


	for (int i = L; i < N + 1; i++) {
		int tmp = ac_sum[i] - ac_sum[i - L];

		if (129 <= tmp && tmp <= 138) {
			cnt += 1;
		}

	}

	printf("%d", cnt);

	free(ac_sum);

	return 0;
}


