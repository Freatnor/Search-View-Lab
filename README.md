---
title: Enable Search - Grocery List
type: lab
duration: "1:25"
creator:
    name: Drew Mahrt
    city: NYC
---



# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Enable Search - Grocery List

## Introduction

> ***Note:*** _This can be a pair programming activity or done independently._

In this lab, you will be adding the ability to search through your grocery list to the toolbar, based on the name of the items. Your search must operate in one of two ways:

- Your search term can appear at the beginning of a word in the name
- Your search term can appear anywhere in the name (even in the middle of a word)

The grocery list table looks like this:

![Grocery List](./screenshots/table.png)

See the SQLiteOpenHelper in the starter code for the data types.

## Exercise

#### Requirements

- Allow the user to search through the grocery list
- Search must work in one of the two required ways (search can appear at the beginning of the word, or anywhere in the word)
- Display the entire grocery list when the app launches

**Bonus:**

- Complete the search in one Activity
- Display the type in the list item
- Add the ability to search by type

#### Starter code

The [starter-code](starter-code) folder contains an Android project with the following:

- An Android project with a pre-populated database

#### Deliverable

Below you can see screenshots of the app with the included one Activity bonus requirement:

  <img src="./screenshots/screen1.png" width="200">
  <img src="./screenshots/screen2.png" width="200">
  <img src="./screenshots/screen3.png" width="200">

## Additional Resources

- [SearchView](http://developer.android.com/reference/android/widget/SearchView.html)
- [Creating a Search Interface](http://developer.android.com/guide/topics/search/search-dialog.html)
