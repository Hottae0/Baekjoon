N = int(input())

graph = [[0 for _ in range(101)] for _ in range(101)]

for i in range(N) :
    x, y = map(int, input().split())

    for a in range(x, x + 10):
        for b in range(y, y + 10):
            graph[a][b] = 1

count = 0

for i in range(len(graph)):
    for j in range(len(graph)):
        count += graph[i][j]

print(count)
