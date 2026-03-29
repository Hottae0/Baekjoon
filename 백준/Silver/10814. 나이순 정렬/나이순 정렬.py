N = int(input())

list = []

for i in range(N):
    age, name = input().split()
    age = int(age)

    list.append( (age, i, name))

list.sort()

for i in list:
    print(i[0], i[2])
