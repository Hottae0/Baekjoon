import sys
input=sys.stdin.readline

N =int(input().rstrip())
appendent = {}

for i in range(N):
    tmp = input().rstrip().split(".")[1]

    if tmp in appendent:
        appendent[tmp] += 1
    else:
        appendent[tmp] = 1


arr = sorted(appendent.keys())

for i in arr :
    print(i, appendent[i])
