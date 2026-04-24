import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDequeTest {

    @Test
    /** test iterator */
    public void iteratorTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }

    @Test
    /** test equal */
    public void equalTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld2.addLast("front"); // after this call we expect: ["front"]
        lld2.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld2.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        assertThat(lld1.equals(lld2)).isTrue();
    }
}
