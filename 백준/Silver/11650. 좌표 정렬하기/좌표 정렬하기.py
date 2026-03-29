N = int(input())

list = []

for i in range(N):
    x, y = map(int, input().split())
    list.append((x,y)) # 튜플의 형태로!

list.sort()

for i in list:
    print(i[0], i[1], sep = " ")