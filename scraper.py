# scraper.py

import requests
from bs4 import BeautifulSoup
import pandas as pd
import time
import os

# ------------------- 설정 ------------------- #
BAEKJOON_ID = "hottae00311"
TARGET_FILE = "README.md"      # 업데이트할 파일명
# ------------------------------------------- #

def get_solved_problems(user_id: str):
    """
    백준 사이트에서 사용자가 맞은 문제 목록을 크롤링합니다.
    """
    print(f"'{user_id}'님의 맞은 문제 목록을 가져오는 중...")
    url = f"https://www.acmicpc.net/user/{user_id}"
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"
}    
    try:
        response = requests.get(url, headers=headers)
        if response.status_code != 200:
            print(f"❌ '{user_id}'님의 페이지를 찾을 수 없습니다. (상태 코드: {response.status_code})")
            return None

        soup = BeautifulSoup(response.text, "html.parser")
        
        # '맞은 문제' 섹션을 찾습니다.
        problem_list_div = soup.find("div", class_="problem-list")
        if not problem_list_div:
            print("❌ '맞은 문제' 섹션을 찾을 수 없습니다.")
            return None
            
        # 문제 번호들을 모두 추출합니다.
        problem_links = problem_list_div.find_all("a")
        solved_list = [link.text for link in problem_links]
        
        print(f"✅ 총 {len(solved_list)}개의 맞은 문제를 찾았습니다.")
        return solved_list
        
    except Exception as e:
        print(f"❌ 크롤링 중 오류 발생: {e}")
        return None

def update_readme(solved_list: list):
    """
    README.md 파일의 내용을 새로운 문제 목록으로 업데이트합니다.
    """
    if not solved_list:
        print("업데이트할 문제 목록이 없습니다.")
        return

    try:
        with open(TARGET_FILE, 'r', encoding='utf-8') as f:
            readme_content = f.read()

        # 데이터프레임을 사용해 마크다운 테이블 생성
        df = pd.DataFrame({'푼 문제들': solved_list})
        markdown_table = df.to_markdown(index=False)

        # README.md에서 주석 사이의 내용을 교체
        start_marker = ""
        end_marker = ""

        start_index = readme_content.find(start_marker)
        end_index = readme_content.find(end_marker)

        if start_index == -1 or end_index == -1:
            print(f"❌ '{TARGET_FILE}'에서 마커({start_marker}, {end_marker})를 찾을 수 없습니다.")
            return
            
        new_content = (
            readme_content[:start_index + len(start_marker)] +
            "\n\n" +
            markdown_table +
            "\n\n" +
            readme_content[end_index:]
        )

        with open(TARGET_FILE, 'w', encoding='utf-8') as f:
            f.write(new_content)
            
        print(f"✅ '{TARGET_FILE}' 파일이 성공적으로 업데이트되었습니다.")

    except Exception as e:
        print(f"❌ 파일 업데이트 중 오류 발생: {e}")

if __name__ == "__main__":
    solved_problems = get_solved_problems(BAEKJOON_ID)
    if solved_problems:
        update_readme(solved_problems)
