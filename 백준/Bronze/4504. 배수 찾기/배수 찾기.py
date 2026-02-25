n = int(input())

x = -1
while( (x := int(input())) != 0) :
    if(x % n == 0):
        print(f"{x} is a multiple of {n}.")
    else :
        print(f"{x} is NOT a multiple of {n}.")
