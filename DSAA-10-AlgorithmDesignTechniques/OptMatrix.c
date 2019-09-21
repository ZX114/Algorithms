#include <stdio.h>
#include <limits.h>

#define INFINITY INT_MAX
#define NMAT 4
typedef int TwoDimArray[NMAT+1][NMAT+1];

void OptMatrix(const int C[], int N, TwoDimArray M)
{
    for (int i = 1; i <= N; i++)
        M[i][i] = 0;

    /* k is right - left */
    for (int k = 1; k < N; k++) {
        for (int left = 1; left <= N - k; left++) {
            int right = left + k;
            M[left][right] = INFINITY;
            for (int i = left; i < right; i++) {
                int mult = M[left][i] + M[i+1][right] + C[left-1]*C[i]*C[right];
                M[left][right] = (mult < M[left][right] ? mult : M[left][right]);
            }
        }
    }
}

int main(int argc, char* argv[])
{
    /* C contains number of columns for each of the NMAT matrices */
    /* C[0] is the number of rows in matrix 1 */
    /* M[left][right] is the number of multiplications */
    /* required in an optimal ordering for matrices from left to right */
    /* M indexed starting at 1, instead of 0 */
    int C[NMAT+1] = {50, 10, 40, 30, 5};
    int M[NMAT+1][NMAT+1];
    int i, j;

    OptMatrix(C, NMAT, M);
    for(i = 1; i <= NMAT; i++) {
        for(j = 1; j <= NMAT; j++)
            printf("%14d", M[i][j] );
        printf("\n");
    }

    return 0;
}
