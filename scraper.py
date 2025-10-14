# scraper.py

import requests
import pandas as pd
import time
import os

# ------------------- 설정 ------------------- #
SOLVEDAC_ID = "hottae00311"
TARGET_FILE = "README.md"
# ------------------------------------------- #

def level_to_tier_image(level):
    """
    Solved.ac API의 레벨 번호를 티어 아이콘 이미지로 변환합니다.
    """
    if not (0 <= level <= 30):
        return "Unrated"
    
    tier_name = [
        "Unrated", "Bronze V", "Bronze IV", "Bronze III", "Bronze II", "Bronze I",
        "Silver V", "Silver IV", "Silver III", "Silver II", "Silver I", "Gold V",
        "Gold IV", "Gold III", "Gold II", "Gold I", "Platinum V", "Platinum IV",
        "Platinum III", "Platinum II", "Platinum I", "Diamond V", "Diamond IV",
        "Diamond III", "Diamond II", "Diamond I", "Ruby V", "Ruby IV", "Ruby III",
        "Ruby II", "Ruby I"
    ][level]
    
    # 이미지 태그와 함께 티어 이름을 반환합니다.
    return f'<img src="https://static.solved.ac/tier_small/{level}.svg" height="16px"/> {tier_name}'

def get_solved_problems_from_api(user_id: str):
    """
    Solved.ac API를 이용해 사용자가 맞은 문제의 상세 정보를 가져옵니다.
    """
    print(f"'{user_id}'님의 맞은 문제 목록을 Solved.ac API에서 가져오는 중...")
    url = f"https://solved.ac/api/v3/search/problem?query=solved_by:{user_id}&sort=level&direction=desc"
    headers = {"Content-Type": "application/json"}
    
    try:
        response = requests.get(url, headers=headers)
        if response.status_code != 200:
            print(f"❌ API 요청 실패. (상태 코드: {response.status_code})")
            print(f"응답 내용: {response.text}")
            return None

        data = response.json()
        # 문제의 상세 정보가 담긴 'items' 리스트를 그대로 반환합니다.
        problems = data.get('items', [])
        
        print(f"✅ 총 {len(problems)}개의 맞은 문제를 API에서 찾았습니다.")
        return problems
        
    except Exception as e:
        print(f"❌ API 처리 중 오류 발생: {e}")
        return None

def update_readme(problems: list):
    """
    README.md 파일의 내용을 새로운 문제 목록 표로 업데이트합니다.
    """
    if not problems:
        print("업데이트할 문제 목록이 없습니다.")
        return

    try:
        with open(TARGET_FILE, 'r', encoding='utf-8') as f:
            readme_content = f.read()
            
        # 상세 정보 리스트를 DataFrame으로 변환
        problem_data = {
            '번호': [p['problemId'] for p in problems],
            '제목': [p['titleKo'] for p in problems],
            '티어': [level_to_tier_image(p['level']) for p in problems],
            '태그': [', '.join([tag['displayNames'][0]['name'] for tag in p['tags'] if tag['displayNames']]) for p in problems]
        }
        df = pd.DataFrame(problem_data)
        
        # DataFrame을 마크다운 테이블로 변환
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
    solved_problems = get_solved_problems_from_api(SOLVEDAC_ID)
    if solved_problems:
        update_readme(solved_problems)
