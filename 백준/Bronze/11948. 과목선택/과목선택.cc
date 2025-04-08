#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main() {

	int sum = 0;

	int min = 101;

	for (int i = 0; i < 4; i++) {
		int tmp = 0;
		scanf("%d", &tmp);

		if (tmp < min) {
			min = tmp;
		}

		sum += tmp;
	}

	sum -= min;

	int min2 = 101;
	for (int i = 0; i < 2; i++) {
		int tmp = 0;
		scanf("%d", &tmp);

		if (tmp < min2) {
			min2 = tmp;
		}

		sum += tmp;
	}
	sum -= min2;

	printf("%d", sum);

	return 0;
}