/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
    	markov.setRandom(seed);
        markov.setTraining(text);
        for(int k=0; k < 3; k++){
			System.out.println(markov.toString());
			String st = markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 615;
        // MarkovZero mz = new MarkovZero();
        // runModel(mz, st, size, seed);
    
        // MarkovOne mOne = new MarkovOne();
        // runModel(mOne, st, size, seed);
        
        EfficientMarkovModel mThree = new EfficientMarkovModel(5);
        runModel(mThree, st, size, seed);
        
        // MarkovFour mFour = new MarkovFour();
        // runModel(mFour, st, size, seed);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public String toString(IMarkovModel m) {
    	return "MarkovModel of order " + m.getClass();
	}

	public void compareMethods() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
    	
		MarkovModel mm = new MarkovModel(2);
    	        EfficientMarkovModel emm = new EfficientMarkovModel(2);
    	        int size = 1000;
    	        int seed = 42;
		System.nanoTime();
    	        runModel(mm, st, size, seed);
		System.nanoTime();
    	        runModel(emm, st, size, seed);
		System.nanoTime();
	}

	public void testHashMap() {
		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		st = st.replace('\n', ' ');
		int size = 50;
		int seed = 42;
		EfficientMarkovModel emm = new EfficientMarkovModel(2);
		runModel(emm, st, size, seed);
	}

	
}