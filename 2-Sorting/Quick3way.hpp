#ifndef QUICK3WAY_HPP_
#define QUICK3WAY_HPP_

#include <vector>

inline
static void quick3wayexch(std::vector<double>& a, int i, int j)
{
    double t = a[i];
    a[i] = a[j];
    a[j] = t;
}

static void quick3waysort(std::vector<double>& a, int lo, int hi)
{
    if (hi <= lo) return;

    int lt = lo, i = lo +1, gt = hi;
    double cmp = a[lo];
    while (i <= gt) {
        if (a[i] < cmp) quick3wayexch(a, i++, lt++);
        else if (a[i] > cmp) quick3wayexch(a, gt--, i);
        else i++;
    }
    quick3waysort(a, lo, lt-1);
    quick3waysort(a, gt+1, hi);
}

void quick3waysort(std::vector<double>& a)
{
    quick3waysort(a, 0, a.size()-1);
}


#endif  // QUICK3WAY_HPP_
