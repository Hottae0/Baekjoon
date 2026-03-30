import sys
#빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline


n, m = map(int, input().split())

password_map = {}

for _ in range(n):
    site, pw = input().split()
    password_map[site] = pw

#찾을 사이트 비밀번호 출력하기
for _ in range(m):
    query = input().strip() # readline은 줄바꿈 문자(\n)가 포함되므로 strip()으로 제거
    print(password_map[query])