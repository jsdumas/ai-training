package io.jsd.training.artificialintelligence2.singleneuralnetwork;

public class ActivationFunction {

	public static int stepFunction(float activation) {
		
		if( activation >= 1)
			return 1;
		
		return 0;
	}
}
