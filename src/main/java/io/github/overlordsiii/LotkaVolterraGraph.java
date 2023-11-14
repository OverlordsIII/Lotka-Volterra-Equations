package io.github.overlordsiii;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class LotkaVolterraGraph {
    private DefaultCategoryDataset createDataset(double alpha, double beta, double gamma, double delta, double initialPrey, double initialPredator, int years) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // this variable will track the amount of prey year-to-year
        double prey = initialPrey;

        // this variable will track the amount of predators year-to-year
        double predator = initialPredator;

        // we then plot the current prey value for initial population
        dataset.addValue(prey, "Prey", "0");

        // we then plot the current predator value for initial population
        dataset.addValue(predator, "Predator", "0");

        // this essentially runs per year, from 1 to 100 years in our case (specified by the years variable)
        for (int t = 1; t <= years; t++) {

            // this finds the change in prey per year. This is the equivalent to the lotka-volterra equation for prey
            double dPrey = alpha * prey - beta * prey * predator;

            // this finds the change in predators per year. This is the equivalent to the lotka-volterra equation for predators
            double dPredator = delta * prey * predator - gamma * predator;

            // we then add the change in prey to the prey after we calculate the prey
            prey += dPrey;

            // we do the same for the predators
            predator += dPredator;

            // we then plot the current prey value for the year we calculated
            dataset.addValue(prey, "Prey", String.valueOf(t));

            // we then plot the current predator value for the year we calculated
            dataset.addValue(predator, "Predator", String.valueOf(t));
        }



        return dataset;
    }

    public JFreeChart createChart(double alpha, double beta, double gamma, double delta, double initialPrey, double initialPredator, int timeSteps) {
        DefaultCategoryDataset dataset = createDataset(alpha, beta, gamma, delta, initialPrey, initialPredator, timeSteps);

        JFreeChart chart = ChartFactory.createLineChart(
                "Orca vs Sea Lion Population Predictions",
                "Year",
                "Population (thousands)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        for (int r = 0; r < dataset.getRowCount(); r++) {
            String series = (String) dataset.getRowKey(r);
            for (int c = 0; c < dataset.getColumnCount(); c++) {
                System.out.println(series
                    + ", " + dataset.getColumnKey(c)
                    + ", " + dataset.getValue(r, c));
            }
        }

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
