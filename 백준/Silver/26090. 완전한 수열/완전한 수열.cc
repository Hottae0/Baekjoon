#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>


int main() {

	int n;// 수열 개수
	scanf("%d", &n);

	int* arr = (int*)malloc((n+1) * sizeof(int)); //누적합.

	arr[0] = 0;

	for (int i = 1; i <= n; i++) {
		int tmp;

		if (i == n)scanf("%d", &tmp);
		else {
			scanf("%d ", &tmp);
		}
		
		arr[i] = arr[i - 1] + tmp;
		
	}

	int size = 1000001;

	int* a = (int*)calloc(size, sizeof(int));


	for (int i = 0; i < size; i++) {
		a[i] = 0;
	}

	for (int i = 2; i * i <= size; i++) {
		
		if (a[i] == 0) { // 소수라면
			for (int j = 2; i * j < size; j++) {
				a[i * j] = 1;
			}

		}
	}

	a[0] = 1;
	a[1] = 1;

	int cnt = 0;

	for (int i = 0; i < n; i++) {
		for (int j = i+1; j <= n; j++) {
			int sum = arr[j] - arr[i];

			if (a[sum] == 0 && a[j - i] == 0) {
				cnt += 1;
			}
		}
	}

	printf("%d", cnt);

	return 0;
}