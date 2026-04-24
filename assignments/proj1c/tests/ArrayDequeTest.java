import deque.Deque61B;
import deque.ArrayDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {

    @Test
    /** test iterator */
    public void iteratorTest() {
        Deque61B<String> ard1 = new ArrayDeque61B<>();

        ard1.addLast("front"); // after this call we expect: ["front"]
        ard1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ard1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ard1).containsExactly("front", "middle", "back");
    }

    @Test
    /** test equal */
    public void equalTest() {
        Deque61B<String> ard1 = new ArrayDeque61B<>();

        ard1.addLast("front"); // after this call we expect: ["front"]
        ard1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ard1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        Deque61B<String> ard2 = new ArrayDeque61B<>();

        ard2.addLast("front"); // after this call we expect: ["front"]
        ard2.addLast("middle"); // after this call we expect: ["front", "middle"]
        ard2.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        assertThat(ard1.equals(ard2)).isTrue();
    }
}
