package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    private Comparator<T> cp;

    public MaxArrayDeque61B(Comparator<T> c) {
        cp = c;
    }

    public T max(){
        return max(cp);
    }

    public T max(Comparator<T> c) {
        if (size() == 0) return null;

        T best = get(0);
        for (int i = 1; i < size(); i++) {
            T cur = get(i);
            if (c.compare(cur, best) > 0) {
                best = cur;
            }
        }
        return best;
    }

}
