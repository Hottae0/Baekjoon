# scraper.py (API version)

import requests
import time
import os

# ------------------- 설정 ------------------- #
SOLVEDAC_ID = "hottae00311"
TARGET_FILE = "README.md"      # 업데이트할 파일명
# ------------------------------------------- #

def get_solved_problems_from_api(user_id: str):
    """
    Solved.ac API를 이용해 사용자가 맞은 문제 목록을 가져옵니다.
    """
    print(f"'{user_id}'님의 맞은 문제 목록을 Solved.ac API에서 가져오는 중...")
    url = f"https://solved.ac/api/v2/search/problem.json?query=solved_by:{user_id}&sort=id&direction=asc"
    headers = {"Content-Type": "application/json"}
    
    try:
        response = requests.get(url, headers=headers)
        if response.status_code != 200:
            print(f"❌ API 요청 실패. (상태 코드: {response.status_code})")
            return None

        data = response.json()
        problem_ids = [item['problemId'] for item in data['items']]
        
        print(f"✅ 총 {len(problem_ids)}개의 맞은 문제를 API에서 찾았습니다.")
        return problem_ids
        
    except Exception as e:
        print(f"❌ API 처리 중 오류 발생: {e}")
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

        # 마크다운 문자열 생성 (한 줄에 10개씩)
        solved_text = ""
        for i, problem_id in enumerate(solved_list):
            solved_text += f"`{problem_id}` "
            if (i + 1) % 10 == 0:
                solved_text += "\n"
        
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
            "\n" +
            solved_text.strip() +
            "\n" +
            readme_content[end_index:]
        )

        with open(TARGET_FILE, 'w', encoding='utf-8') as f:
            f.write(new_content)
            
        print(f"✅ '{TARGET_FILE}' 파일이 성공적으로 업데이트되었습니다.")

    except Exception as e:
        print(f"❌ 파일 업데이트 중 오류 발생: {e}")

if __name__ == "__main__":
    solved_problems = get_solved_problems_from_api(SOLVEDAC_ID)
    if solved_problems:
        update_readme(solved_problems)
