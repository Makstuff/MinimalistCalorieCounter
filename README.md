## About this app

After testing lots of different calorie counter apps without finding one I was completely happy with, I finally decided to take matters into my own hand and learn how to code for Android myself. This resulting app is exactly what I personally want and fits my every need, but I can understand that might not be the case everyone. The main barriers are probably
1. my decision to not use a large external food database, because I prefer to gather data either directly from the product labels or, if that's not possible, from other sources like my nutrition book or websites that I can choose and double-check if some numbers seem questionable, and
2. the interface, which is optimized for using a minimum amount of clicks in the long-term and sacrifices a lot of potential intuitivity for that.

I hope that despite all that, this app will find its niche in the Play Store universe and make some lives a little easier.

## User guide

- I couldn't think of a clever way to label all the nutrient numbers in the scrollable lists, so I decided to just leave them out and have the user memorize their order instead, which is given below. Note that in the Database view, these values are per `100g`, but anywhere else they are already multiplied by the respective weight.
```
              Carbohydrate [g]               Fat [g]
Energy [kcal                    Protein [g]                     Fiber [g]  Cost [€]
              Sugar [g]                      Saturated fat [g]            
```

- If you want to use the Starting Database in another language than the app itself, you can set the app to the language you want the Database to be in, reset the Database, then set the app back to the language you want for the app. As long as you don't reset the Database again, it will keep its language.
- You can long-press Database, Day and Recipe Entries to instantly delete them. I should mention that I didn't add this functionality for Archive Entries because I felt it would not really be needed and potentially cause accidental deletions. To delete single Archive Entries, short-press them and then use the "Delete" button at the bottom. To delete all Archive Entries, use "Clear Archive" in the Options menu.
- You can long-press Quickselect Panels to instantly disable Quickselect for this Database Entry, without deleting it completely. To ENABLE Quickselect, click on the Entry in the Database view, enable the Quickselect switch and press "Save Changes".
- In the input screens with multiple text fields, in most of them the "Enter" key on the keyboard is replaced by a "Next" key that allows you to directly jump to the next text field. On the last text field there is a "Checkmark" key instead that acts as an alternate confirm button.
- In the Starting Database,
    - "Beef", "Chicken" and "Pork" are average values that I eyeballed inbetween a variety of common cuts.
    - "Meal (healthy)" is 1/3 chicken, brown rice and broccoli each, with a price like you could expect at a cheap restaurant.
    - "Meal (fast food)" is calculated from a large burger with large fries, using the price and nutrient data from McDonalds. 
    - "Meal (average)" is the average between Meal (healthy) and Meal (fast food).
    - "Bread" is the average between whole grain rye bread and white wheat toast.
    - "Pastry" is the eyeballed average over a wider range of sweet baked goods.
    - "Soda" is an average lemonade with no nutrients except 10g sugar.

## Support

I would very much appreciate help with translations, as I am only able to provide English and German myself. The two files that would need to be translated are

- the [App content](https://github.com/Makstuff/MinimalistCalorieCounter/blob/1b401b534dda54ebaec0f2cc5db30fb2c8f58ef1/app/src/main/res/values/strings.xml) and
- the [Starting Database](https://github.com/Makstuff/MinimalistCalorieCounter/blob/1b401b534dda54ebaec0f2cc5db30fb2c8f58ef1/app/src/main/res/raw/database.csv).

I'd guess for someone who's a little familiar with the app already, it would take around 1h overall. If you are willing to help out, you can either submit a pull-request or open an issue on here on Github, or just message me at makstuff@use.startmail.com.

[Buy me a coffee.](https://www.buymeacoffee.com/makstuff)

## Compatibility with USA-type food labels

Unfortunately, through much of the development of this app, I was not aware how different USA and other countries' food labels can be compared to EU ones. The USA ones seem to deal almost exclusively in arbitrary "portions" instead of in fixed weights like 100g, and on top of that they include fiber in carbohydrate. If I had known about this sooner, I would have definitely included an option in the app, but now I would have to rewrite a whole lot of my messy code, and I honestly don't have the motivation at the moment. Might be something to tackle in the future, but for now I have just excluded all non-EU countries from the target regions on the Play Store.

## Screenshots

![Combo](https://github.com/Makstuff/MinimalistCalorieCounter/assets/57408125/48908d2c-10d4-4b01-91a2-13dba96b1026)

<a href='https://play.google.com/store/apps/details?id=com.makstuff.minimalistcaloriecounter&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png' width="200"/></a>

