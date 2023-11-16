package io.github.overlordsiii;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class LotkaVolterraGraph extends JFrame {
    private DefaultCategoryDataset dataset;

    private ChartPanel chartPanel;
    private JFreeChart chart;
    double alpha = 0.0045890411;
    double beta = 0.000696164;
    double gamma = 0.1625;
    double delta = 0.006053;
    double initialPrey = 45;
    double initialPredator = 21;
    int years = 50;

    public LotkaVolterraGraph(String title) {
        super(title);

        // Create the dataset and chart
        createDataset();
        chart = createChart();

        // Create and set up the chart panel
        this.chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Add sliders
        createSlidersFrame();

        // Set up the layout
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createSlidersFrame() {
        JFrame slidersFrame = new JFrame("Sliders");
        slidersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        slidersFrame.setLayout(new BorderLayout());

        // Add sliders to the separate frame
        addSliders(slidersFrame);

        slidersFrame.pack();
        slidersFrame.setLocationRelativeTo(null);
        slidersFrame.setVisible(true);
    }

    private void addSliders(JFrame frame) {
        JSlider alphaSlider = createDoubleSlider("Alpha", alpha, alpha / 4, alpha * 4, 100000);
        JSlider betaSlider = createDoubleSlider("Beta", beta, beta / 4, beta * 4, 100000);
        JSlider gammaSlider = createDoubleSlider("Gamma", gamma, gamma / 4, gamma * 4, 1000);
        JSlider deltaSlider = createDoubleSlider("Delta", delta, delta / 4, delta * 4, 100000);

        JPanel sliderPanel = new JPanel(new GridLayout(7, 2));
        sliderPanel.add(new JLabel("Adjust Parameters:"));
        sliderPanel.add(new JLabel()); // Empty label for spacing
        sliderPanel.add(new JLabel("Alpha:"));
        sliderPanel.add(alphaSlider);
        sliderPanel.add(new JLabel("Beta:"));
        sliderPanel.add(betaSlider);
        sliderPanel.add(new JLabel("Gamma:"));
        sliderPanel.add(gammaSlider);
        sliderPanel.add(new JLabel("Delta:"));
        sliderPanel.add(deltaSlider);

        frame.add(sliderPanel, BorderLayout.CENTER);

        // Add listeners to the sliders to update the parameters and chart
        alphaSlider.addChangeListener(e -> {
            alpha = alphaSlider.getValue() / 100000.0;
            updateChart();
        });

        betaSlider.addChangeListener(e -> {
            beta = betaSlider.getValue() / 100000.0;
            updateChart();
        });

        gammaSlider.addChangeListener(e -> {
            gamma = gammaSlider.getValue() / 1000.0;
            updateChart();
        });

        deltaSlider.addChangeListener(e -> {
            delta = deltaSlider.getValue() / 100000.0;
            updateChart();
        });
    }

    private JSlider createDoubleSlider(String label, double initialValue, double min, double max, int scale) {
        int scaledInitialValue = (int) (initialValue * scale);
        int scaledMin = (int) (min * scale);
        int scaledMax = (int) (max * scale);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, scaledMin, scaledMax, scaledInitialValue);
        slider.setMajorTickSpacing((int) ((max - min) * scale) / 5);
        slider.setMinorTickSpacing((int) ((max - min) * scale) / 10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JLabel sliderLabel = new JLabel(label);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(sliderLabel, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.CENTER);

        slider.addChangeListener(e -> {
            double scaledValue = slider.getValue() / (double) scale;
            System.out.println(label + ": " + scaledValue);
            // You can use the scaledValue as needed (e.g., update your chart parameters)
        });

        return slider;
    }


    private void createDataset() {
        dataset = new DefaultCategoryDataset();

        double prey = initialPrey;
        double predator = initialPredator;

        dataset.addValue(prey, "Prey", "0");
        dataset.addValue(predator, "Predator", "0");

        for (int t = 1; t <= years; t++) {
            double dPrey = alpha * prey - beta * prey * predator;
            double dPredator = delta * prey * predator - gamma * predator;

            prey += dPrey;
            predator += dPredator;

            dataset.addValue(prey, "Prey", String.valueOf(t));
            dataset.addValue(predator, "Predator", String.valueOf(t));
        }
    }

    private JFreeChart createChart() {
        return ChartFactory.createLineChart(
            "Lotka-Volterra Model",
            "Year",
            "Population",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
    }

    private void updateChart() {
        createDataset();
        chart.getCategoryPlot().setDataset(dataset);
        chart.fireChartChanged();
        chartPanel.repaint();

    }

    public static void main(String[] args) {
        new LotkaVolterraGraph("Lotka-Volterra Model");
    }
}
