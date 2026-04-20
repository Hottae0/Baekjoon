import math

T = int(input())

for t in range(T):
    arr = list(map(int, input().split()))

    ans = 1
    for i in range(len(arr) - 1):
        for j in range(i + 1, len(arr)):
            ans = max(ans, math.gcd(arr[i], arr[j]))

    print(ans)
