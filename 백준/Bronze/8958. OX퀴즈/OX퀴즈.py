t=int (input ())
word=[]


for k in range(t):
    s = input()
    word = list(s)

    ans = 0
    sum_o = 0

    for i in range(len (word)) :
        if word[i]=="O":
            sum_o += 1

            ans += sum_o
        else:
        #O 아니면 X 이기 때문에 else 써도 무관
            sum_o = 0

    print(ans)