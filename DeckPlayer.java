import java.util.Iterator;
import java.util.List;

public class DeckPlayer {
	private List<String> deck;

	private int handSize;

	private boolean hold;

	private boolean print;

	private int turnSpread = 0;

	private int turnsToShuffle = 0;

	public DeckPlayer(List<String> deck, int handSize, boolean hold, boolean print) {
		this.deck = deck;
		this.handSize = handSize;
		this.hold = hold;
		this.print = print;
	}

	public void playDeck() {
		boolean countSpread = false;
		while (deck.size() >= handSize) {
			turnsToShuffle++;
			if (countSpread) {
				turnSpread++;
			}
			if (print) {
				printDeckWithHandMark(deck, handSize);
			}
			String houseToPlay;
			try {
				houseToPlay = pickHouseToPlay(deck.subList(0, handSize));
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			if (print) {
				System.out.println("Playing " + houseToPlay);
			}
			int i = 0;
			boolean addC = false;
			boolean playedC = "C".equals(houseToPlay);
			if (countSpread && playedC) {
				countSpread = false;
			}
			
			for (Iterator<String> iterator = deck.iterator(); iterator.hasNext();) {
				if (i >= handSize) {
					break;
				}
				String card = iterator.next();
				if (card.equals(houseToPlay)) {
					iterator.remove();
				} else if (playedC && "X".contentEquals(card)) {
					iterator.remove();
					if (hold) {						
						addC = true;
					}
					countSpread = true;
				}
				i++;
			}
			if (addC) {
				addC = false;
				deck.add(0, "C");
			}

		}
	}

	public static void printDeckWithHandMark(List<String> deck, int handSize) {
		for (int i = 0; i < deck.size(); i++) {
			if (i == handSize) {
				System.out.print("| ");
			}
			System.out.print(deck.get(i) + " ");
		}
		System.out.println();
	}

	private String pickHouseToPlay(List<String> hand) throws Exception {
		int aCount = 0;
		int bCount = 0;
		int cCount = 0;
		for (int i = 0; i < hand.size(); i++) {
			if ("A".equals(hand.get(i))) {
				aCount++;
			} else if ("B".equals(hand.get(i))) {
				bCount++;
			} else if ("C".equals(hand.get(i))) {
				cCount++;
			} else if (!hold && "X".equals(hand.get(i))) {
				cCount++;
			}
		}
		if (cCount >= aCount && cCount >= bCount) {
			return "C";
		}
		if (bCount >= aCount && bCount >= cCount) {
			return "B";
		}
		if (aCount >= bCount && aCount >= cCount) {
			return "A";
		}

		throw new Exception("Weird shit.");
	}

	public int getTurnSpread() {
		return turnSpread;
	}

	public int getTurnsToShuffle() {
		return turnsToShuffle;
	}
}
