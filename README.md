# SDSChallenge

SDS programming challenge.

Author: Jonathan Mercer
 
## Requirements

Hi and welcome to the Inn. As you know, we are a small inn with a prime location in a 
prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the 
finest goods. Unfortunately, our goods are constantly degrading in quality as they 
approach their sell by date. We have a system in place that updates our inventory for us. 
It was developed by a no-nonsense guy named Paul, who has moved on to new adventures. 
Your task is to add the new feature to our system so that we can begin selling a new 
category of items. First an introduction to our system:

All items have a SellIn value which denotes the number of days we have to sell the item
All items have a Quality value which denotes how valuable the item is
At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:
Once the sell by date has passed, Quality degrades twice as fast
The Quality of an item is never negative
"Aged Brie" actually increases in Quality the older it gets
The Quality of an item is never more than 50
"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
"Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches; 
Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or 
less but Quality drops to 0 after the concert.

We have recently signed a supplier of conjured items. This requires an update to our 
system:

"Conjured" items degrade in Quality twice as fast as normal items
Feel free to make any changes to the UpdateQuality method and add any new code as long as
everything still works correctly. However, do not alter the Item class or Items property 
as those belong to another team that doesn’t believe in shared code ownership (you can 
make the UpdateQuality method and Items property static if you like, we'll cover for you).
Just for clarification, an item can never have its Quality increase above 50, however 
"Sulfuras" is a legendary item and as such its Quality is 80 and it never alters.
PLEASE RETURN ALL OF THE FILES THAT YOU CREATE. FEEL FREE TO ZIP THE ENTIRE SOLUTION AND 
RETURN IT TO US.

## Assumptions:

Concerts are held in the evening, so backstage passes sold the same day still have quality
to them.

The requirements do not explicitly state the rate at which normal items depreciate. Since
the starter code has them depreciate at the rate of -1 quality per day, this is the 
assumed rate of decay. Likewise, it is assumed that Aged Brie increases in quality by 1 
each day.

It is assumed that "Conjured" items can be discerned as such because their name begins 
with the word "Conjured".

Conjured items increase in value over time, such as Aged Brie, will have the same rate of 
quality growth as normal items of the same type. The property of an item being conjured 
only affects its rate of depreciation, not an increase in quality.

## Functionality

The Class Item is functionally unchanged from the starting code, with the only additions 
being a pair of constructors to initialize none or all of the fields respectively.

The Class SDSChallenge addresses the requirements of the system. The updateItems method 
is the sole entry point, and updates the sell-by date and quality of every item in an array 
passed into it.

The Class SDSChallengeTest provides a number of basic unit tests for the methods in 
SDSChallenge.

Much of the provided code has been restructured, in addition to adding the new "conjured 
item" functionality. The most notable additions include:

1. Creating an updateItems method, which handles the updating of the quality and sellIn 
values of a list of Items. 

2. Separating the functionality of updating an item's Quality and its sellIn into two 
different methods. 

3. Added methods to increment/decrement quality to reduce duplicate code.

## Further Work:

As of the time of writing, I am attempting to finish the challenge for submission before
tomorrow morning, 11/10/2017, but acknowledge there is more I would like to change/implement:

1. Writing new classes that inherit from Item and implement the unique functionality of
many of the items shown in the requirements. For example, 

a nonStandardDepreciatingItem class to model:
the Aged Brie, which gains quality over time instead of losing it.
Sulfuras, which does not depreciate at all.
Conjured items, which depreciate at twice the normal rate.
the backstage passes, which depreciate at a rate dependent on sellIn.

a showpieceItem that does not have a relevant sellIn value, to model Sulfuras.

a legendaryItem that can have a quality above 50.
