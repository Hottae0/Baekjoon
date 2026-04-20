def gcd(a, b):
    if a % b == 0:
        return b
    return gcd(b, a % b)

T = int(input())

for t in range(T):
    N, *arr = map(int, input().split())

    sum = 0
    for i in range(len(arr) - 1):
        for j in range(i + 1, len(arr)):
            sum += gcd(arr[i], arr[j])

    print(sum)