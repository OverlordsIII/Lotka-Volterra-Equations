package io.github.overlordsiii;

import org.jfree.chart.JFreeChart;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {


		/*
        double alpha = 0.1;
        double beta = 0.02;
        double gamma = 0.3;
        double delta = 0.01;
        double initialPrey = 40;
        double initialPredator = 9;
        int timeSteps = 100;
		 */

		double alpha = 0.006218;
		double beta = 0.03764;
		double gamma = 0.1589;
		double delta = 0.000006117;
		double initialPrey = 45000;
		double initialPredator = 21100;
		int timeSteps = 10;

		double prey = initialPrey;
		double predator = initialPredator;

		HashMap<Double, Integer> preyMap = new HashMap<>();
		HashMap<Double, Integer> predatorMap = new HashMap<>();

		for (int t = 1; t <= timeSteps; t++) {
			double dPrey = alpha * prey - beta * prey * predator;
			double dPredator = delta * prey * predator - gamma * predator;

			prey += dPrey;
			predator += dPredator;

			preyMap.put(prey, t);
			predatorMap.put(predator, t);
		}



		//LotkaVolterraGraph lvGraph = new LotkaVolterraGraph();
		//JFreeChart chart = lvGraph.createChart(alpha, beta, gamma, delta, initialPrey, initialPredator, timeSteps);
		//lvGraph.displayChart(chart);
	}
}