
public class TriangleDrawer {

    public static void drawTriangle(int n) {
        String line = "*";
        while (n > 0) {
            n--;
            System.out.println(line);
            line += "*";
        }
    }

    public static void main(String[] args) {
        drawTriangle(10);
    }
}
