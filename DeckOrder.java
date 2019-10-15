import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DeckOrder {
	
	private static int HAND_SIZE = 6;
	private static int ITERATIONS = 10000;
	
	public static void main(String[] args) {
		List<String> deck = new ArrayList<String>();
		for (int i = 0; i < 36; i++) {
			if (i<12) {
				deck.add("A");
			} else if (i<24) {
				deck.add("B");
			} else if (i<35) {
				deck.add("C");
			} else {
				deck.add("X");
			}
		}
		int turnsToShuffleHold = 0;
		int turnSpreadHold = 0;
		int turnsToShuffleNoHold = 0;
		int turnSpreadNoHold = 0;
		for (int i = 0; i < ITERATIONS; i++) {
			Collections.shuffle(deck);
			
			if (ITERATIONS <=1) {				
				System.out.println("Hold:");
			}
			List<String> hold = new ArrayList<String>(deck);			
			DeckPlayer deckPlayerHold = new DeckPlayer(hold, HAND_SIZE, true, ITERATIONS <=1);
			deckPlayerHold.playDeck();
			turnsToShuffleHold += deckPlayerHold.getTurnsToShuffle();
			turnSpreadHold += deckPlayerHold.getTurnSpread();
			
			if (ITERATIONS <=1) {				
				System.out.println("\nNo hold:");
			}
			List<String> noHold = new ArrayList<String>(deck);
			DeckPlayer deckPlayerNoHold = new DeckPlayer(noHold, HAND_SIZE, false, ITERATIONS <=1);
			deckPlayerNoHold.playDeck();
			turnsToShuffleNoHold += deckPlayerNoHold.getTurnsToShuffle();
			turnSpreadNoHold += deckPlayerNoHold.getTurnSpread();
		}
		System.out.println("Turns to shuffle hold: " + (float)turnsToShuffleHold/(float)ITERATIONS);
		System.out.println("Turns to shuffle no hold: " + (float)turnsToShuffleNoHold/(float)ITERATIONS);
		System.out.println("Turn spread hold: " + (float)turnSpreadHold/(float)ITERATIONS);
		System.out.println("Turn spread no hold: " + (float)turnSpreadNoHold/(float)ITERATIONS);
		
	}
	
}
