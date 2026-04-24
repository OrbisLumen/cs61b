import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    /** This test is used to test addFirst, addLast and toString */
    public void addTest() {

        // for addFirst without resizing
        Deque61B<Integer> ard1 = new ArrayDeque61B<> ();
        // check empty
        assertThat(ard1.toList()).isEmpty();
        // ard1 [7, 6, 5, 4, 3, 2, 1, 0]
        ard1.addFirst(0);
        ard1.addFirst(1);
        ard1.addFirst(2);
        ard1.addFirst(3);
        ard1.addFirst(4);
        ard1.addFirst(5);
        ard1.addFirst(6);
        ard1.addFirst(7);
        assertThat(ard1.toList()).containsExactly(7,6,5,4,3,2,1,0).inOrder();

        // for addLast without resizing
        Deque61B<Integer> ard2 = new ArrayDeque61B<> ();
        // check empty
        assertThat(ard2.toList()).isEmpty();
        // ard2 [0, 1, 2, 3, 4, 5, 6, 7]
        ard2.addLast(0);
        ard2.addLast(1);
        ard2.addLast(2);
        ard2.addLast(3);
        ard2.addLast(4);
        ard2.addLast(5);
        ard2.addLast(6);
        ard2.addLast(7);
        assertThat(ard2.toList()).containsExactly(0,1,2,3,4,5,6,7).inOrder();

        // for addFirst and addLast combined together
        Deque61B<Integer> ard3 = new ArrayDeque61B<> ();
        // check empty
        assertThat(ard3.toList()).isEmpty();
        // ard3 [6, 4, 2, 0, 1, 3, 5, 7]
        ard3.addFirst(0);
        ard3.addLast(1);
        ard3.addFirst(2);
        ard3.addLast(3);
        ard3.addFirst(4);
        ard3.addLast(5);
        ard3.addFirst(6);
        ard3.addLast(7);
        assertThat(ard3.toList()).containsExactly(6,4,2,0,1,3,5,7).inOrder();

        // check resizing here
        ard1.addFirst(8);
        assertThat(ard1.toList()).containsExactly(8,7,6,5,4,3,2,1,0).inOrder();
        ard2.addLast(8);
        assertThat(ard2.toList()).containsExactly(0,1,2,3,4,5,6,7,8).inOrder();

    }


    @Test
    /** Test the get method */
    public void getTest() {

        Deque61B<Integer> ard = new ArrayDeque61B<> ();
        // check empty
        assertThat(ard.toList()).isEmpty();
        // check empty edge case
        assertThat(ard.get(0)).isNull();
        assertThat(ard.get(-1)).isNull();
        assertThat(ard.get(114514)).isNull();

        // ard      [6, 4, 2, 0, 1, 3, 5, 7]
        // index     0  1  2  3  4  5  6  7
        ard.addFirst(0);
        ard.addLast(1);
        ard.addFirst(2);
        ard.addLast(3);
        ard.addFirst(4);
        ard.addLast(5);
        ard.addFirst(6);
        ard.addLast(7);
        // check add correctly
        assertThat(ard.toList()).containsExactly(6,4,2,0,1,3,5,7).inOrder();
        // check get
        assertThat(ard.get(-1)).isNull();
        assertThat(ard.get(0)).isEqualTo(6);
        assertThat(ard.get(1)).isEqualTo(4);
        assertThat(ard.get(7)).isEqualTo(7);
        assertThat(ard.get(114514)).isNull();

        // check after resizing
        // ard      [8, 6, 4, 2, 0, 1, 3, 5, 7, 9]
        // index     0  1  2  3  4  5  6  7  8  9
        ard.addFirst(8);
        ard.addLast(9);
        assertThat(ard.get(0)).isEqualTo(8);
        assertThat(ard.get(1)).isEqualTo(6);
        assertThat(ard.get(7)).isEqualTo(5);
        assertThat(ard.get(8)).isEqualTo(7);
        assertThat(ard.get(9)).isEqualTo(9);

    }

    @Test
    /** This will check the isEmpty and size method */
    public void sizeTest() {

        Deque61B<Integer> ard = new ArrayDeque61B<> ();
        // check empty
        assertThat(ard.isEmpty()).isTrue();

        // ard      [6, 4, 2, 0, 1, 3, 5, 7]
        // index     0  1  2  3  4  5  6  7
        ard.addFirst(0);
        ard.addLast(1);
        ard.addFirst(2);
        ard.addLast(3);
        ard.addFirst(4);
        ard.addLast(5);
        ard.addFirst(6);
        ard.addLast(7);
        // check empty
        assertThat(ard.isEmpty()).isFalse();
        // check size
        assertThat(ard.size()).isEqualTo(8);

    }

    @Test
    /** This test will check the removing method with sizing */
    public void removeTest() {

        Deque61B<Integer> ard = new ArrayDeque61B<> ();
        // remove when empty
        assertThat(ard.removeFirst()).isNull();
        assertThat(ard.removeLast()).isNull();

        // ard      [6, 4, 2, 0, 1, 3, 5, 7]
        // index     0  1  2  3  4  5  6  7
        ard.addFirst(0);
        ard.addLast(1);
        ard.addFirst(2);
        ard.addLast(3);
        ard.addFirst(4);
        ard.addLast(5);
        ard.addFirst(6);
        ard.addLast(7);
        // remove when not empty
        assertThat(ard.removeFirst()).isEqualTo(6);
        assertThat(ard.removeLast()).isEqualTo(7);
        // check size change
        assertThat(ard.size()).isEqualTo(6);



        // check after resizing
        // ard      [8, 6, 4, 2, 0, 1, 3, 5, 7, 9]
        // index     0  1  2  3  4  5  6  7  8  9
        ard.addFirst(6);
        ard.addLast(7);
        ard.addFirst(8);
        ard.addLast(9);
        // remove after sizing up
        assertThat(ard.removeLast()).isEqualTo(9);
        assertThat(ard.removeFirst()).isEqualTo(8);
        // check sizing down
        assertThat(ard.toList()).containsExactly(6,4,2,0,1,3,5,7).inOrder();
        // check size change
        assertThat(ard.size()).isEqualTo(8);
        // remove after sizing down
        assertThat(ard.removeFirst()).isEqualTo(6);
        assertThat(ard.size()).isEqualTo(7);
        assertThat(ard.removeLast()).isEqualTo(7);
        assertThat(ard.size()).isEqualTo(6);

    }

    @Test
    /** wraparound + reuse space */
    public void wraparoundTest() {

            Deque61B<Integer> d = new ArrayDeque61B<>();

            // fill
            for (int i = 0; i < 8; i++) {
                d.addLast(i);
            }

            // remove some from front
            for (int i = 0; i < 5; i++) {
                d.removeFirst();
            }

            // now add again (forces wrap)
            for (int i = 8; i < 13; i++) {
                d.addLast(i);
            }

            // expected: [5,6,7,8,9,10,11,12]
            assertThat(d.toList()).containsExactly(
                    5,6,7,8,9,10,11,12
            ).inOrder();

    }


    @Test
    /** resize + wrap */
    public void resizeAndWrapTest() {

            Deque61B<Integer> d = new ArrayDeque61B<>();

            for (int i = 0; i < 20; i++) {
                d.addLast(i);
            }

            for (int i = 0; i < 15; i++) {
                d.removeFirst();
            }

            for (int i = 20; i < 35; i++) {
                d.addLast(i);
            }

            // check order consistency
            for (int i = 0; i < d.size(); i++) {
                assertThat(d.get(i)).isEqualTo(i + 15);
            }

    }

}
