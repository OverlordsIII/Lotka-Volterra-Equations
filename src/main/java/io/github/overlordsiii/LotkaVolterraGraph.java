package io.github.overlordsiii;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class LotkaVolterraGraph {
    private DefaultCategoryDataset createDataset(double alpha, double beta, double gamma, double delta, double initialPrey, double initialPredator, int timeSteps) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        double prey = initialPrey;
        double predator = initialPredator;

        for (int t = 1; t <= timeSteps; t++) {
            double dPrey = alpha * prey - beta * prey * predator;
            double dPredator = delta * prey * predator - gamma * predator;

            prey += dPrey;
            predator += dPredator;

            dataset.addValue(prey, "Prey", "Year " + t);
            dataset.addValue(predator, "Predator", "Year " + t);
        }

        return dataset;
    }

    public JFreeChart createChart(double alpha, double beta, double gamma, double delta, double initialPrey, double initialPredator, int timeSteps) {
        DefaultCategoryDataset dataset = createDataset(alpha, beta, gamma, delta, initialPrey, initialPredator, timeSteps);

        JFreeChart chart = ChartFactory.createLineChart(
                "Lotka-Volterra Model",
                "Year",
                "Population",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        return chart;
    }

    // Display the chart
    public void displayChart(JFreeChart chart) {
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        JFrame frame = new JFrame("Lotka-Volterra Model");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

}
