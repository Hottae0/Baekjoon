#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int BNP(int chart[], int seed, int sum){

    for (int i = 0; i < 14; i++) {
        if (seed >= chart[i]) {
            sum += seed / chart[i];
            seed -= (seed / chart[i]) * chart[i];
        }

    }

    return seed + sum * chart[13];
}

int timing(int chart[], int seed, int sum) {

    for (int i = 3; i < 14; i++) {
        if (chart[i - 3] < chart[i - 2] && chart[i - 2] < chart[i - 1] && chart[i - 1] < chart[i]) { // 3일 내내 증가
            seed += chart[i] * sum;
            sum = 0;
        }
        else if (chart[i - 3] > chart[i - 2] && chart[i - 2] > chart[i - 1] && chart[i - 1] > chart[i]) { // 3일 내내 하락
            sum += seed / chart[i];
            seed -= (seed / chart[i]) * chart[i];
        }
    }

    return seed + sum * chart[13];
}

int main() {    

    int seed;
    scanf("%d", &seed);

    int chart[14];

    for (int i = 0; i < 14; i++) {
        int tmp;

        if (i == 13) {
            scanf("%d", &tmp);
        }
        else {
            scanf("%d ", &tmp);
        }

        chart[i] = tmp;
    }

    int BNP_sum = BNP(chart, seed, 0);
    int Timing_sum = timing(chart, seed, 0);

    if (BNP_sum > Timing_sum) {
        printf("BNP");
    }
    else if (BNP_sum == Timing_sum) {
        printf("SAMESAME");
    }
    else {
        printf("TIMING");
    }
    


    return 0;
}


