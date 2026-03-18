N, M = map(int, input().split())

list = []
for i in range (N):
    list.append(i + 1)

for i in range(M):
    a, b = map(int, input().split())

    a -= 1
    b -= 1

    tmp = list[a]
    list[a] = list[b]
    list[b] = tmp

for i in list:
    print(i, end = " ")