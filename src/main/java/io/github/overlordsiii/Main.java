package io.github.overlordsiii;

import org.jfree.chart.JFreeChart;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {

		double alpha = 0.0045890411;
		double beta = 0.000696164;
		double gamma = 0.1625;
		double delta = 0.006053;
		double initialPrey = 45;
		double initialPredator = 21;
		int timeSteps = 50;


		LotkaVolterraGraph lvGraph = new LotkaVolterraGraph();
		JFreeChart chart = lvGraph.createChart(alpha, beta, gamma, delta, initialPrey, initialPredator, timeSteps);
		lvGraph.displayChart(chart);
	}
}