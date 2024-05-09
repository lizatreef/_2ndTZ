
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberProcessorTest {
    @Test
    public void testFindMin() {
        assertEquals(1, NumberProcessor.findMin(new int[] {1, 4, 2, 3}));
    }

    @Test
    public void testFindMax() {
        assertEquals(4, NumberProcessor.findMax(new int[] {1, 4, 2, 3}));
    }

    @Test
    public void testCalculateSum() {
        assertEquals(10, NumberProcessor.calculateSum(new int[] {1, 4, 2, 3}));
    }

    @Test
    public void testCalculateProduct() {
        assertEquals(24, NumberProcessor.calculateProduct(new int[] {1, 4, 2, 3}));
    }
}
