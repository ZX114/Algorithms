#include <iostream>
#include <random>
#include <ctime>
#include <vector>

#include "Quick.hpp"
#include "Quick3way.hpp"

using std::vector;
using std::cout;
using std::cerr;
using std::endl;
using std::default_random_engine;
using std::uniform_real_distribution;
using std::clock;


default_random_engine e;
uniform_real_distribution<double> u(0,100);
vector<double> a(1000000);
clock_t t0, t1;

void test(void (*sort)(vector<double>&))
{
    e.seed(1000);
    t0 = clock();
    for (auto it = a.begin(); it != a.end(); it++)
        *it = u(e);
    sort(a);
    for (size_t i = 1; i < a.size(); i++)
        if (a[i] < a[i-1]) { cout << "Not "; break; }
    cout << "Sorted -- with Quick" << endl;
    t1 = clock();
    cout << "\tRun time   " << static_cast<double>(t1 - t0) / CLOCKS_PER_SEC
         << " s" << endl;
}

int main()
{
    test(&quicksort);
    test(&quick3waysort);

    return 0;
}
