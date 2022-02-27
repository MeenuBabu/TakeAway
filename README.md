#TakeAway Assignment

##Goal
The goal of this assignment is to implement a sample project, where you visualize a restaurant list. You are able to sort the restaurant list based on its current openings state and you can select a sort value to further sort the list. Additionally, we would like to see you add an option to filter the restaurant list based on the restaurant's name.
The list item should contain the name of the restaurants, the current opening state, the selected sorting and the sort value for a restaurant.
Sort options should also be in a logical order when they are selected. Eg. Popularity descending, distance ascending etc.

##Description
The json(example.json) file contains all the necessary data. Parse the JSON file and visualize it as a restaurant list. Use the following rules for the sorting:
1.	Restaurant open state: The list has to constantly be sorted by the restaurant open state. Open restaurants should be on the top of the list, order-ahead restaurants in the middle, closed restaurants in the bottom. (Values available in sample.json)
2.	Sort options: Users should be able to apply additional sorting methods and this can be best-match, newest, rating average, distance, popularity, average product price, delivery costs or the minimum cost. (Values available in sample.json)

The application reads the content from sample json file.

##RestaurantActivity
Activity responsible for listing restaurants in recyclerview.

## Sorting Options

Spinner UI element is used for sorting options.
Touching the spinner displays a dropdown menu with all other available values which can be;
 - best match
 - newest
 - average
 - distance
 - popularity
 - average product price
 - delivery costs
 - the minimum cost.

 ####  Build
 This application uses the Gradle build system. To build this project use "Import Project" in Android Studio and use the following command.
 `> gradlew build`

###  Test
Test suite class (UITestSuite.class)  created to run all instrumentation test cases with single click.

