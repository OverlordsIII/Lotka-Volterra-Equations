package io.github.overlordsiii;

import org.jfree.chart.JFreeChart;

import java.util.HashMap;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new LotkaVolterraGraph("Lotka-Volterra Model"));
	}
}