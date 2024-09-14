import time

from math import sqrt
from random import randint
from statistics import mean, stdev


class UnionFind:
    def __init__(self, n: int) -> None:
        self.n = n
        self.parent = list(range(n))
        self.size = [1] * n

    def find(self, p: int) -> int:
        if not 0 <= p < self.n:
            raise ValueError("p is out of range")

        while p != self.parent[p]:
            # point parent to grandparent to keep the tree flat
            self.parent[p] = self.parent[self.parent[p]]
            p = self.parent[p]
        return p

    def union(self, p: int, q: int) -> None:
        if not 0 <= p < self.n:
            raise ValueError("p is out of range")
        if not 0 <= q < self.n:
            raise ValueError("q is out of range")

        root1 = self.find(p)
        root2 = self.find(q)

        # keep size(root1) <= size(root2)
        if self.size[root1] > self.size[root2]:
            root1, root2 = root2, root1

        self.parent[root1] = root2
        self.size[root2] += self.size[root1]


class Percolation:
    def __init__(self, n: int) -> None:
        if n <= 0:
            raise ValueError("n must be greater than 0")

        self.n = n
        self.grid = [[False] * n for _ in range(n)]

        # use union-find to represent how sites are connected
        self.uf = UnionFind(n**2 + 2)
        # virtual top is n**2 and virtual bottom is n**2 + 1
        self._vtop = n**2
        self._vbot = n**2 + 1

    def _in_range(self, i: int, j: int) -> bool:
        return 0 <= i < self.n and 0 <= j < self.n

    def _index(self, i: int, j: int) -> int:
        return i * self.n + j

    def _try_union(self, i1: int, j1: int, i2: int, j2: int) -> None:
        if not self._in_range(i1, j1) or not self._in_range(i2, j2):
            return

        if self.grid[i1][j1] and self.grid[i2][j2]:
            self.uf.union(self._index(i1, j1), self._index(i2, j2))

    def open(self, i: int, j: int) -> None:
        if not self._in_range(i, j):
            raise ValueError("i and j must be in range")

        self.grid[i][j] = True

        self._try_union(i, j, i - 1, j)
        self._try_union(i, j, i + 1, j)
        self._try_union(i, j, i, j - 1)
        self._try_union(i, j, i, j + 1)

        if i == 0:
            self.uf.union(self._index(i, j), self._vtop)

        if i == self.n - 1:
            self.uf.union(self._index(i, j), self._vbot)

    def is_open(self, i: int, j: int) -> bool:
        if not self._in_range(i, j):
            raise ValueError("i and j must be in range")

        return self.grid[i][j]

    def is_full(self, i: int, j: int) -> bool:
        if not self._in_range(i, j):
            raise ValueError("i and j must be in range")

        return self.uf.find(self._index(i, j)) == self.uf.find(self._vtop)

    def percolates(self) -> bool:
        return self.uf.find(self._vtop) == self.uf.find(self._vbot)


class PercolationStats:
    def __init__(self, n: int, t: int) -> None:
        if n <= 0:
            raise ValueError("n must be greater than 0")
        if t <= 0:
            raise ValueError("t must be greater than 0")

        self.n = n
        self.t = t
        self.stats: list[float] = []

        for _ in range(t):
            percolation = Percolation(n)
            opened = 0
            while not percolation.percolates():
                i = randint(0, n - 1)
                j = randint(0, n - 1)
                if not percolation.is_open(i, j):
                    percolation.open(i, j)
                    opened += 1
            self.stats.append(opened / (n**2))
            print(".", end="", flush=True)

        print()

    @property
    def mean(self) -> float:
        return mean(self.stats)

    @property
    def stdev(self) -> float:
        return stdev(self.stats)

    @property
    def confidence_low(self) -> float:
        return self.mean - 1.96 * self.stdev / sqrt(self.t)

    @property
    def confidence_high(self) -> float:
        return self.mean + 1.96 * self.stdev / sqrt(self.t)

    def __repr__(self) -> str:
        return (
            f"mean = {self.mean}\n"
            f"stdev = {self.stdev}\n"
            f"95% confidence interval = [{self.confidence_low}, {self.confidence_high}]"
        )


if __name__ == "__main__":
    n = int(input("n = "))
    t = int(input("t = "))

    st = time.monotonic()
    stats = PercolationStats(n, t)
    et = time.monotonic()

    print(f"elapsed = {et - st}")
    print(stats)
