#ifndef QUICK_HPP_
#define QUICK_HPP_

#include <vector>

inline
static void quickexch(std::vector<double>& a, int i, int j)
{
    double t = a[i];
    a[i] = a[j];
    a[j] = t;
}

static int partition(std::vector<double>& a, int lo, int hi)
{
    double cmp = a[lo];
    int i = lo + 1, j = hi;
    while (i <= j) {
        if (a[i] <= cmp) ++i;
        else if (a[j] >= cmp) --j;
        else { quickexch(a, i, j); ++i; --j; }
    }
    quickexch(a, lo, j);
    return j;
}

static void quicksort(std::vector<double>& a, int lo, int hi)
{
    if (hi <= lo) return;

    int f = partition(a, lo, hi);

    quicksort(a, lo, f-1);
    quicksort(a, f+1, hi);
}

void quicksort(std::vector<double>& a)
{
    quicksort(a, 0, a.size()-1);
}

#endif  // QUICK_HPP_
