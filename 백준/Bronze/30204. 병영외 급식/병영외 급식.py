N, X = map(int, input().split())

arr = list(map(int, input().split()))

sum = 0
for i in range(len(arr)):
    sum += arr[i];

print(1 if sum % X == 0 else 0)