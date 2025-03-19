#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define max(X,Y) ((X) > (Y) ? (X) : (Y))



int main() {

	int T;
	scanf("%d", &T);

	for (int i = 0; i < T; i++) {
		int a; int b; int c; int d;
		int e; int f; int g; int h;

		scanf("%d %d %d %d %d %d %d %d", &a, &b, &c, &d, &e, &f, &g, &h);

		int sum_a = a + e;
		int sum_b = b + f;
		int sum_c = c + g;
		int sum_d = d + h;

		if (sum_a < 1) {
			sum_a = 1;
		}

		if (sum_b < 1) {
			sum_b = 1;
		}

		if (sum_c < 0) {
			sum_c = 0;
		}

		printf("%d\n", sum_a + 5 * sum_b + 2 * sum_c + 2 * sum_d);
	}
	

	return 0;
}


