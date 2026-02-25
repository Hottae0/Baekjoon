X, Y = map(int, input().split())

if(X < Y):
    tmp = X
    X = Y
    Y = tmp

sum = 0


sum += X + (Y // 10)
sum += Y

print(sum)

