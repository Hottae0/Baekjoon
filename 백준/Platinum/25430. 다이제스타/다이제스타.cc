#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>
#include <algorithm>
#include <climits>

using namespace std;

const long long INF = LLONG_MAX;

// 그래프의 간선 정보를 저장하는 구조체 (인접 리스트용)
struct Node {
    int i;
    long long cost;

    // 내림차순 정렬을 위한 비교 연산자
    bool operator<(const Node& other) const {
        return cost < other.cost;
    }
};

// 다익스트라 탐색 상태를 저장하는 구조체 (우선순위 큐용)
struct Info {
    int i;
    long long cost;
    long long previous;

    // 우선순위 큐를 min-heap으로 만들기 위한 비교 연산자 (총 비용 오름차순)
    bool operator>(const Info& other) const {
        return cost > other.cost;
    }
};

int N, M;
vector<Node> arr[50000];
unordered_map<long long, long long> dis[50000];

long long Dijkstra(int start, int end) {
    if (start == end) return 0;

    // 우선순위 큐 (min-heap)
    priority_queue<Info, vector<Info>, greater<Info>> pq;

    // 시작 상태 초기화
    pq.push({start, 0, 0});
    dis[start][0] = 0;

    long long ans = INF;

    while (!pq.empty()) {
        Info current = pq.top();
        pq.pop();

        int from = current.i;
        long long sum = current.cost;
        long long previous = current.previous;

        // 저장된 최단 경로보다 현재 경로가 더 비효율적이면 건너뛰기
        // C++에서는 getOrDefault가 없으므로, 키 존재 여부를 확인 후 접근
        if (dis[from].find(previous) == dis[from].end() || sum > dis[from][previous]) {
            continue;
        }
        
        // 도착지에 도달했다면 정답 갱신
        if (from == end) {
            ans = min(ans, sum);
        }

        // 인접한 노드 탐색
        for (const auto& next_node : arr[from]) {
            long long cost = next_node.cost;

            // 핵심 최적화: 인접 리스트가 내림차순 정렬되어 있으므로,
            // 현재 간선 가중치가 previous보다 작거나 같으면 그 뒤는 볼 필요 없음
            if (cost <= previous) {
                break;
            }

            int to = next_node.i;
            
            // 새로운 경로가 기존 경로보다 더 효율적인지 확인
            long long new_sum = sum + cost;
            if (dis[to].find(cost) == dis[to].end() || new_sum < dis[to][cost]) {
                dis[to][cost] = new_sum;
                pq.push({to, new_sum, cost});
            }
        }
    }

    return ans;
}

int main() {
    // C++ 빠른 입출력 설정
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;

    for (int i = 0; i < M; ++i) {
        int u, v;
        long long w;
        cin >> u >> v >> w;
        --u; --v; // 0-based index
        arr[u].push_back({v, w});
        arr[v].push_back({u, w});
    }

    int start, end;
    cin >> start >> end;
    --start; --end;

    // 모든 인접 리스트를 내림차순으로 정렬
    for (int i = 0; i < N; ++i) {
        sort(arr[i].begin(), arr[i].end());
        reverse(arr[i].begin(), arr[i].end());
    }

    long long result = Dijkstra(start, end);

    if (result == INF) {
        cout << "DIGESTA" << "\n";
    } else {
        cout << result << "\n";
    }

    return 0;
}
