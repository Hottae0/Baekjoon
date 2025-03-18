#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define max(X,Y) ((X) > (Y) ? (X) : (Y))

int N;
int M;

int** arr;
int** meorial;

int dp(int r, int c) {

	if (r == 0 && c == 0) {
		return arr[r][c];
	}

	if (r < 0 || c < 0) {
		return 0;
	}

	if (meorial[r][c] == -1) { // 방문 x
		meorial[r][c] = max(dp(r - 1, c), max(dp(r, c - 1), dp(r - 1, c - 1))) + arr[r][c];
	}

	return meorial[r][c];

}

int main() {

	scanf("%d %d", &N, &M);
	
	arr = (int**)malloc(N * sizeof(int*));
	meorial = (int**)malloc(N * sizeof(int*));

	for(int i = 0; i < N; i++) {
		arr[i] = (int*)malloc(M * sizeof(int));
		meorial[i] = (int*)malloc(M * sizeof(int));
	}

	for (int i = 0; i < N; i++) {

		for (int j = 0; j < M; j++) {
			int tmp;

			if (j != M - 1) {
				scanf("%d ", &tmp);
			}else {
				scanf("%d", &tmp);
			}

			arr[i][j] = tmp;
			meorial[i][j] = -1;
		}

	}

	int ans = dp(N - 1, M - 1);


	printf("%d", ans);


	for (int i = 0; i < N; i++) {
		free(arr[i]);
	}
	free(arr);


	return 0;
}


