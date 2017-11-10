import java.util.ArrayList;

/**
 * Last Update: 8:18 P.M. 11/9/2017.
 * 
 * SDS programming challenge.
 * 
 * @author Jonathan Mercer
 *
 * Subject: SDS programming challenge
 * Hi and welcome to the Inn. As you know, we are a small inn with a prime location in a 
 * prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the 
 * finest goods. Unfortunately, our goods are constantly degrading in quality as they 
 * approach their sell by date. We have a system in place that updates our inventory for us. 
 * It was developed by a no-nonsense guy named Paul, who has moved on to new adventures. 
 * Your task is to add the new feature to our system so that we can begin selling a new 
 * category of items. First an introduction to our system:
 * 
 * All items have a SellIn value which denotes the number of days we have to sell the item
 * All items have a Quality value which denotes how valuable the item is
 * At the end of each day our system lowers both values for every item
 * 
 * Pretty simple, right? Well this is where it gets interesting:
 * Once the sell by date has passed, Quality degrades twice as fast
 * The Quality of an item is never negative
 * "Aged Brie" actually increases in Quality the older it gets
 * The Quality of an item is never more than 50
 * "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
 * "Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches; 
 * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or 
 * less but Quality drops to 0 after the concert.
 * 
 * We have recently signed a supplier of conjured items. This requires an update to our 
 * system:
 *
 * "Conjured" items degrade in Quality twice as fast as normal items
 * Feel free to make any changes to the UpdateQuality method and add any new code as long as
 * everything still works correctly. However, do not alter the Item class or Items property 
 * as those belong to another team that doesn’t believe in shared code ownership (you can 
 * make the UpdateQuality method and Items property static if you like, we'll cover for you).
 * Just for clarification, an item can never have its Quality increase above 50, however 
 * "Sulfuras" is a legendary item and as such its Quality is 80 and it never alters.
 * PLEASE RETURN ALL OF THE FILES THAT YOU CREATE. FEEL FREE TO ZIP THE ENTIRE SOLUTION AND 
 * RETURN IT TO US.
 *
 * Assumptions:
 * 
 * Concerts are held in the evening, so backstage passes sold the same day still have quality
 * to them.
 * 
 * The requirements do not explicitly state the rate at which normal items depreciate. Since
 * the starter code has them depreciate at the rate of -1 quality per day, this is the 
 * assumed rate of decay. Likewise, it is assumed that Aged Brie increases in quality by 1 
 * each day.
 * 
 * It is assumed that "Conjured" items can be discerned as such because their name begins 
 * with the word "Conjured".
 * 
 * Conjured items increase in value over time, such as Aged Brie, will have the same rate of 
 * quality growth as normal items of the same type. The property of an item being conjured 
 * only affects its rate of depreciation, not an increase in quality.
 * 
 * Author Notes:
 * 
 * Much of the provided code has been restructured, in addition to adding the new "conjured 
 * item" functionality. The most notable additions include:
 * 
 * 1. Creating an updateItems method, which handles the updating of the quality and sellIn 
 * values of a list of Items. 
 * 
 * 2. Separating the functionality of updating an item's Quality and its sellIn into two 
 * different methods. 
 * 
 * 3. Added methods to increment/decrement quality to reduce duplicate code.
 * 
 * Further Work:
 * 
 * As of the time of writing, I am attempting to finish the challenge for submission before
 * tomorrow morning, 11/10/2017, but acknowledge there is more I would like to change:
 * 
 * 1. Writing new classes that inherit from Item and implement the unique functionality of
 * many of the items shown in the requirements. For example, 
 * 
 * a nonStandardDepreciatingItem class to model:
 * the Aged Brie, which gains quality over time instead of losing it.
 * Sulfuras, which does not depreciate at all.
 * Conjured items, which depreciate at twice the normal rate.
 * the backstage passes, which depreciate at a rate dependent on sellIn.
 * 
 * a showpieceItem that does not have a relevant sellIn value, to model Sulfuras.
 * 
 * a legendaryItem that can have a quality above 50.
 */
public class SDSChallenge {

	/**
	 * Updates the quality and remaining days until expiration of a list of items assuming 
	 * one day has passed. The amount to update each value is based on the type of the item;
	 * more info on this logic can be found at the top of the class.
	 *
	 * @param items An array of items to update.
	 */
	
	public static void updateItems(Item[] items) {
		for (int i = 0; i < items.length; i++) {
			Item currentItem = items[i];
			updateSellIn(currentItem);
			updateQuality(currentItem);
			
		}
	}

	/**
	 * Updates the quality of a single assuming one day has passed. The amount to change each
	 * value by is based on the type of the item; more info on this logic can be found at the 
	 * top of the class.
	 *
	 * @param currentItem the item to update the quality of.
	 */
	private static void updateQuality(Item currentItem) {
		assert (currentItem.getQuality() >= 0 &&
				(currentItem.getName() == "Sulfuras, Hand of Ragnaros" || 
				currentItem.getQuality() <= 50));
				// Invariant: an item's quality (other than Sulfuras) must 
			    // be non-negative and no greater than 50.
		int changeInQuality = 0;

		if (currentItem.getName() == "Aged Brie") {
			changeInQuality++;
		} else if (currentItem.getName() == "Backstage passes to a TAFKAL80ETC concert") {
			if (currentItem.getSellIn() < 0) {
				currentItem.setQuality(0);
			} else {
				changeInQuality++;
				if (currentItem.getSellIn() < 11) {
					changeInQuality++;
				}
				if (currentItem.getSellIn() < 6) {
					changeInQuality++;
				}
			}
		} else if (currentItem.getName() != "Sulfuras, Hand of Ragnaros") {
			changeInQuality--;
			if (currentItem.getSellIn() < 0) {
				changeInQuality *= 2;
			}
			if (currentItem.getName().startsWith("Conjured")) {
				changeInQuality *= 2;
			}
		}
		if (changeInQuality > 0) {
			for (int i = 0; i < changeInQuality; i++) {
				incrementQuality(currentItem);
			}
		} else if (changeInQuality < 0) {
			for (int i = changeInQuality; i < 0; i++) {
				decrementQuality(currentItem);
			}
		}
		assert (currentItem.getQuality() >= 0 &&
				(currentItem.getName() == "Sulfuras, Hand of Ragnaros" || 
				currentItem.getQuality() <= 50));
				// Invariant: an item's quality (other than Sulfuras) must 
			    // be non-negative and no greater than 50.
	}

	/**
	 * Updates the days until expiration of a single assuming one day has passed. The amount
	 * to change each value by is based on the type of the item; more info on this logic can
	 * be found at the top of the class.
	 *
	 * @param currentItem the item to update the sellIn of.
	 */
	private static void updateSellIn(Item currentItem) {
		if (currentItem.getName() != "Sulfuras, Hand of Ragnaros") {
			currentItem.setSellIn(currentItem.getSellIn() - 1);
		}
	}
	
	/**
	 * Increments the quality of an item, while not allowing it to exceed 50.
	 * 
	 * @param currentItem The item to increment the quality of.
	 */
	private static void incrementQuality(Item currentItem) {
		if (currentItem.getQuality() < 50) {
			currentItem.setQuality(currentItem.getQuality() + 1);
		}
	}

	/**
	 * Decrements the quality of an item, while not allowing it to go below 0.
	 * 
	 * @param currentItem The item to decrement the quality of.
	 */
	private static void decrementQuality(Item currentItem) {
		if (currentItem.getQuality() > 0) {
			currentItem.setQuality(currentItem.getQuality() - 1);
		}
	}

}
