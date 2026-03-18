import sys

input = sys.stdin.readline

N, M = map(int, input().split())

arr = list(range(1, N + 1))

for _ in range(M):
    a, b = map(int, input().split())
    a -= 1
    b -= 1

    for i in range(0, int((b - a + 1) / 2)) :
        arr[a + i], arr[b - i] = arr[b - i], arr[a + i]

print(*arr)

