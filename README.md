# chalengeAutomationWeb

This project is a chalenge frontEnd automation of a e-commerce using Selenium framework, Maven, TestNG and Java.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Installation](#installation)

## Overview

In this project we have 5 tests to simulate some flows that costumer will do when he/she enter on e-commerce and wants to buy some product.

## Features
List of the Tests:

- Test 1 - addItemIntoCart - Will simulate adding a product into the cart screen.
- Test 2 - updateQuantityItemIntoCart - Will simulate updating a quantity of items into the cart screen. 
- Test 3 - removeItemFromCart - Will simulate removing the items from the cart.
- Test 4 - jumpToItemDetails - Will simulate a customer clicking on specific product to see the details about it.
- Test 5 - startCheckOutProcess - Will simulate E2E flow from begining until the end when the process is done successfully,


## Installation

To run all the tests through the bash you must type:
mvn test -Dtest="eCommerceTest"


