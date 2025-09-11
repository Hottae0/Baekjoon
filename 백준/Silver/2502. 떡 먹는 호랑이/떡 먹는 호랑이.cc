#include <iostream>

using namespace std;

int main() {

    int D, K;
    cin >> D >> K;

    int fibonacci[32];
    fibonacci[1] = fibonacci[2] = 1;

    for(int i = 3; i < D + 1; i++){
        fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];   
    }

    int a = fibonacci[D - 2];
    int b = fibonacci[D - 1];

    int A = 0;

    for(int i = 2; i < K; i++){
        if((K - (a * i)) % b == 0){
            A = i;
            break;
        }
    }

    int B = (K - (a * A)) / b;

    cout << A << endl << B;

    return 0;
}
