import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SDSChallengeTest {
	
 Item[] items = new Item[1];
	
	@After 
	public void cleanUp() {
		items = new Item[1];
	}

	@Test
	public void shouldUpdateNormalItem() {
		items[0] = new Item("+5 Dexterity Vest", 10, 20);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 9
				&& items[0].getQuality() == 19
				);
	}
	
	
	@Test
	public void shouldUpdateMultipleItems() {
		items = new Item[2];
		
		items[0] = new Item("+5 Dexterity Vest", 10, 20);
		items[1] = new Item("Elixir of the Mongoose", 5, 7);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 9
				&&items[0].getQuality() == 19
				&& items[1].getSellIn() == 4
				&& items[1].getQuality() == 6
				);
	}
	
	@Test
	public void shouldDegradeExpiredItemsQuicker() {
		items[0] = new Item("+5 Dexterity Vest", -5, 20);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == -6
				&& items[0].getQuality() == 18
				);
	}
	
	@Test
	public void shouldNotDegradeLegendaries() {
		items[0] = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 0
				&& items[0].getQuality() == 80
				);
	}
	
	@Test
	public void shouldImproveAgedBrieQuality() {
		items[0] = new Item("Aged Brie", 2, 4);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 1
				&& items[0].getQuality() == 5
				);
	}
	
	@Test
	public void shouldNotIncreaseQualityPast50() {
		items[0] = new Item("Aged Brie", 10, 50);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 9
				&& items[0].getQuality() == 50
				);
	}
	
	@Test
	public void shouldNotReduceQualityBelow0() {
		items[0] = new Item("Unstable Arcanocrystal", 10, 0);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 9
				&& items[0].getQuality() == 0
				);
	}
	
	@Test
	public void shouldImproveBackstagePassQuality() {
		items = new Item[3];
		
		items[0] = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
		items[1] = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
		items[2] = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
		
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 14
				&& items[0].getQuality() == 21
				&& items[1].getSellIn() == 9
				&& items[1].getQuality() == 22
				&& items[2].getSellIn() == 4
				&& items[2].getQuality() == 23
				);
		
	}
	
	@Test
	public void shouldDegradeBackstagePageQuality() {
		items[0] = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
				
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == -1
				&& items[0].getQuality() == 0
				);
	}
	
	@Test
	public void shouldDegradeConjuredItems() {
		items[0] = new Item("Conjured Mana Cake", 3, 6);
		SDSChallenge.updateItems(items);
		assert (items[0].getSellIn() == 2
				&& items[0].getQuality() == 4
				);
	}
}
