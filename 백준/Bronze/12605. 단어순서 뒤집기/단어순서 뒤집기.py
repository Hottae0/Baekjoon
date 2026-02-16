N = int(input())

for i in range(N):
    sen = input().split()
    
    print(f"Case #{i + 1}: ", end="")
    
    for j in range(len(sen) - 1, -1, -1):
        print(sen[j], end = " ")
    print()
