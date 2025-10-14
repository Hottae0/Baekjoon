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
    headers = {"User-Agent": "Mozilla/5.0"}
    
    try:
        response = requests.get(url, headers=headers)
        if response.status_code != 200:
            print(f"❌ '{user_id}'님의 페이지를 찾을 수 없습니다. (상태 코드: {response.status_code})")
            return None

        soup = BeautifulSoup(response.text, "html.parser")
        
        # '맞은 문제' 섹션을 찾습니다.
        problem_list_div = soup
