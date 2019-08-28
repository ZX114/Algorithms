#include <iostream>
#include <string>
#include <vector>

using std::cout;
using std::cin;
using std::cerr;
using std::endl;
using std::vector;
using std::string;

static const int R = 256;

static void LSD_sort(vector<string>& a, int width)
{
    vector<string> aux(a.size());
    for (size_t i = 0; i < a.size(); i++) {
        aux[i] = a[i];
    }

    for (int d = width-1; d >= 0; --d) {
        int count[R+1] = {0};
        // 1-counts
        for (size_t i = 0; i < a.size(); i++) {
            ++count[static_cast<int>(a[i].at(d))+1];
        }

        // 2-cumulative counts
        for (int i = 0; i < R; i++) {
            count[i+1] += count[i];
        }

        // 3-move to aux
        for (size_t i = 0; i < a.size(); i++) {
            aux[count[static_cast<int>(a[i].at(d))]++] = a[i];
        }

        // 4-copy back
        for (size_t i = 0; i < a.size(); i++) {
            a[i] = aux[i];
        }
    }
}


int main(int argc, char* argv[])
{
    vector<string> a;
    string s;
    cin >> s;
    a.push_back(s);
    size_t width = s.size();
    while (cin >> s) {
        if (s.size() != width) {
            cerr << "Unequal size of strings" << endl;
            exit(1);
        }
        a.push_back(s);
    }

    LSD_sort(a, width);

    for (size_t i = 0; i < a.size(); i++) {
        cout << i << " " << a[i] << endl;
    }

    return 0;
}
