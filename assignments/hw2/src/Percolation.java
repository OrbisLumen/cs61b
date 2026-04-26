import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.

    // Using two UF to complete Percolation problem
    // Adding two virtual site virtualTop and virtualBottom
    // ufp -> UF for percolation (N * N + 2 sites)
    // (connect all the first line site to virtualTop and all the last line site to virtualBottom)
    // uff -> UF for full judging (N * N + 1 sites)
    // (connect all the first line site to virtualTop and do not add the virtualBottom)


    private int openSites;
    private final int N;
    private final int vt;
    private final int vb;
    private boolean[][] open;
    private WeightedQuickUnionUF ufp, uff;

    private int xyMap (int row, int col) {
        return row * N + col + 1;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Index Out of Bounds.");
        }
    }

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        vt = 0;
        vb = N * N + 1;
        openSites = 0;
        open = new boolean[N][N];
        ufp = new WeightedQuickUnionUF(N * N + 2);
        uff = new WeightedQuickUnionUF(N * N + 1);
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        if (open[row][col]) {
            return;
        }
        open[row][col] = true;
        openSites++;
        ufConnect(row, col);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);
        return uff.connected(xyMap(row, col), vt) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return ufp.connected(vt, vb);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private void ufConnect(int r, int c) {
        ufConnectUp(r, c);
        ufConnectDown(r, c);
        ufConnectLeft(r, c);
        ufConnectRight(r, c);
    }

    private void ufConnectUp(int r, int c) {
        int index = xyMap(r, c);
        if (r == 0) {
            ufp.union(index, vt);
            uff.union(index, vt);
        }
        else {
            if (open[r - 1][c]) {
                ufp.union(index, xyMap(r - 1, c));
                uff.union(index, xyMap(r - 1, c));
            }
        }
    }

    private void ufConnectDown(int r, int c) {
        int index = xyMap(r, c);
        if (r == N - 1) {
            ufp.union(index, vb);
        }
        else {
            if (open[r + 1][c]) {
                ufp.union(index, xyMap(r + 1, c));
                uff.union(index, xyMap(r + 1, c));
            }
        }
    }

    private void ufConnectLeft(int r, int c) {
        if (c == 0) {
            return;
        }
        int index = xyMap(r, c);
        if (open[r][c - 1]) {
            ufp.union(index, xyMap(r, c - 1));
            uff.union(index, xyMap(r, c - 1));
        }
    }

    private void ufConnectRight(int r, int c) {
        if (c == N - 1) {
            return;
        }
        int index = xyMap(r, c);
        if (open[r][c + 1]) {
            ufp.union(index, xyMap(r, c + 1));
            uff.union(index, xyMap(r, c + 1));
        }
    }

}
