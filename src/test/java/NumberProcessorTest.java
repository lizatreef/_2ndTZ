import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberProcessorTest {
    private int[] numbers;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        numbers = NumberProcessor.readNumbersFromFile("src/test/resources/New");
    }

    @Test
    public void testFindMin() {
        assertEquals(1, NumberProcessor.findMin(numbers));
    }

    @Test
    public void testFindMax() {
        assertEquals(4, NumberProcessor.findMax(numbers));
    }

    @Test
    public void testCalculateSum() {
        assertEquals(10, NumberProcessor.calculateSum(numbers));
    }

    @Test
    public void testCalculateProduct() {
        assertEquals(24, NumberProcessor.calculateProduct(numbers));
    }

    //3rd point

    private static final int LARGE_SIZE = 1000000;
    private static final int[] largeTestData = new int[LARGE_SIZE];

    static {
        Random random = new Random();
        for (int i = 0; i < LARGE_SIZE; i++) {
            largeTestData[i] = random.nextInt(100000);
        }
    }

    @Test
    public void performanceTest() {
        long startTime = System.currentTimeMillis();
        int min = NumberProcessor.findMin(largeTestData);
        int max = NumberProcessor.findMax(largeTestData);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Processing time for " + LARGE_SIZE + " elements: " + duration + " ms");

        assertNotNull(min);
        assertNotNull(max);
    }

    //4th point (check empty file)

    @Test
    public void testEmptyFile() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            int[] numbers = NumberProcessor.readNumbersFromFile("src/test/resources/Empty");
            NumberProcessor.findMin(numbers);
        });

        assertTrue(exception.getMessage().contains("No numbers to process"));
    }

    //5th point
    @Test
    public void testPerformanceAndPlot() {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000};
        List<Double> times = new ArrayList<>();
        List<Integer> sizeList = new ArrayList<>();
        Random random = new Random();

        for (int size : sizes) {
            int[] testData = new int[size];
            for (int i = 0; i < size; i++) {
                testData[i] = random.nextInt(100000);
            }

            long startTime = System.currentTimeMillis();
            NumberProcessor.findMin(testData);
            long endTime = System.currentTimeMillis();

            long duration = endTime - startTime;
            times.add((double) duration);
            sizeList.add(size);

            System.out.println("Processing time for " + size + " elements: " + duration + " ms");
        }

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Execution Time Analysis").xAxisTitle("Number of Elements").yAxisTitle("Time (ms)").build();
        chart.addSeries("Execution time", sizeList, times).setMarker(SeriesMarkers.CIRCLE);

        // Show it
        new SwingWrapper(chart).displayChart();
    }
}

