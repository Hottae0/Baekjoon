a = int(input())

tmp = 1

for i in range(0, a, 1):
    for j in range(0, tmp, 1):
        print("*", end = "")
    tmp += 1
    print()