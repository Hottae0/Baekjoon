N = int(input())

lists = []

for i in range(N):
    s = input()
    lists.append((len(s), s))

lists = list(set(lists))
lists.sort()

for i in lists:
    print(i[1])
