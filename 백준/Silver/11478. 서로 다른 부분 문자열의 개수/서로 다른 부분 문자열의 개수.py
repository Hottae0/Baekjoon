import sys
input=sys.stdin.readline

N = input().rstrip()
S = set()

for i in range(len(N)):
    for j in range(i, len(N)):
        S.add(N[i : j + 1])

print(len(S))
