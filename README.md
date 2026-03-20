## About this app

After testing lots of different calorie counter apps without finding one I was completely happy with, I finally decided to take matters into my own hand and learn how to code for Android myself. This resulting app is exactly what I personally want and fits my every need, but I can understand that might not be the case everyone. The main barriers are probably
1. my decision to not use a large external food database, because I prefer to gather data either directly from the product labels or, if that's not possible, from other sources like my nutrition book or websites that I can choose and double-check if some numbers seem questionable, and
2. the interface, which is optimized for using a minimum amount of clicks in the long-term and sacrifices a lot of potential intuitivity for that.

I hope that despite all that, this app will find its niche in the Play Store universe and make some lives a little easier.

## User guide

Many of the functions and elements should be pretty self-explanatory, this is just a short rundown of a few which might not be as clear. Please let me know if you feel anything essential is missing.

- If you often eat the same amount of a certain food, you can add a set of "custom weights" to make these weights available with just one click. To add these to an existing Entry, click on it in the Database, fill in the bottom field with the weights and names separated by colons and hyphens (e.g. "55:1 Scoop-110:2 Scoops") and save the changes. For your most-eaten foods, you can even flip the Quickselect switch there, which will make them appear directly on the homescreens for a simple one-click add.
- When creating a recipe, you can leave the "Overall weight" empty if you want to use the sum of ingredient weights, which is then calculated automatically.
- If you want to use the Starting Database in another language than the app itself, you can set the app to the language you want the Database to be in, reset the Database, then set the app back to the language you want for the app. As long as you don't reset the Database again, it will keep its language.
- Repeatedly pressing the "Day" button in the bottom bar switches back and forth between "Add" and "Edit".
- In the input screens with multiple text fields, in most of them the "Enter" key on the keyboard is replaced by a "Next" key that allows you to directly jump to the next text field. On the last text field there is a "Checkmark" key instead that acts as an alternate confirm button.
- If you do not like the provided standard database, you can import [database_empty.csv](https://github.com/Makstuff/MinimalistCalorieCounter/blob/9da0ef3786e6f7f54b71d335681c2f8e5c225166/database_empty.csv) to clear out everything.
- Note that I used Gemini for the French, Italian and Spanish translations. More than anything this was an experiment if people are interested in more translations at all. If there is some nonsense in the translated files somewhere, please let me know here or at message.makstuff@outlook.com.

[Buy me a coffee.](https://www.buymeacoffee.com/makstuff)

## Compatibility with USA-type food labels

Unfortunately, through much of the development of this app, I was not aware how different USA and other countries' food labels can be compared to EU ones. The USA ones seem to deal almost exclusively in arbitrary "portions" instead of in fixed weights like 100g, and on top of that they include fiber in carbohydrate. If I had known about this sooner, I would have definitely included an option in the app. But now I would have to rewrite a lot of my messy code, and I honestly don't have the motivation at the moment. Might be something to tackle in the future, but for now I have just excluded all non-EU countries from the target regions on the Play Store.

## Screenshots

![Combo](https://github.com/Makstuff/MinimalistCalorieCounter/assets/57408125/48908d2c-10d4-4b01-91a2-13dba96b1026)

<a href='https://play.google.com/store/apps/details?id=com.makstuff.minimalistcaloriecounter&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png' width="200"/></a>

