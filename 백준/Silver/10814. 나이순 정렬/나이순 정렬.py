N = int(input())

list = []

for i in range(N):
    age, name = input().split()
    age = int(age)

    list.append( (age, name))

list.sort(key = lambda x : x[0])

for i in list:
    print(i[0], i[1])
