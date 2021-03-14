<p align="center">
  <img src="https://github.com/iamjosephmj/Rx-Clean/blob/master/repo-res/images/clean.png" />
</p>

# Rx-Clean

[![License MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=flat)]()
[![Public Yes](https://img.shields.io/badge/Public-yes-green.svg?style=flat)]()

<p>
I hope you all are going well. Are you looking for a sound architectural pattern that will help you to get rid of the 
complexity of your project? Have you heard of the Clean Architecture but don't know what all the fuzz is about? You are in the 
right place. 
<br>
In this tutorial, I will tke you through :

* What Clean Architecture is
* Why it’s important to use architecture patterns in software
* When to use Clean Architecture and SOLID principles
* How to implement Clean Architecture on Android 

<br>

The networking library is implemented with RxKotlin, please refer to <a style = "color: white" href ="https://github.com/iamjosephmj/learn-rx">`LearnRx`</a> to get a general understanding of 
Reactive Programming.
</p>

## Table of contents

* [Getting Started](#Getting-Started)
* [Clean Architecture](#Clean-Architecture)
    * [Why is this important](#Why-is-this-important-)
    * [The Approach](#The-Approach)
    * [Benefits](#Benefits)

## Getting Started

When it comes to Android development, there has always been an open debate, on which architectural pattern to use. Since my early days of 
Android development I got a feeling that thing weren't right the way they were setup, This is the case with more than 90% of developers, which 
in turn; caused a lot of developers to struggle with the architecture in general.

However, for quite a long time, there have been talks about writing your applications in a clean way. 
Furthermore, one of the most influential persons in the global programmer community, 
Robert C. Martin( aka Uncle Bob) has written a book, specifically on this topic(I personally suggest you guys to read it).

Because the Clean architecture can be used in any application and platform, not just Android, 
it’s very informative to understand the idea behind it, and why it’s a good solution, 
for most problems we find nowadays, as programmers. With that in mind, in this tutorial, 
you’ll learn how to use the Clean architecture pattern to build a `GitHubJobs` app, a simple app that displays jobs that are available in github and display that on a list.

## Clean Architecture

Clean architecture is a software design philosophy that separates the elements of a design into ring levels. 
An important goal of clean architecture is to provide developers with a way to organize code in such a way that 
it encapsulates the business logic but keeps it separate from the delivery mechanism.

### Why is this important ?

When you take a real software development, Implementing an architecture have one common - to maintain the complexity 
of your codebase. This may not be a big issue if the codebase are small, but trust me, this is be real lifesaver when 
it come to larger ones.

### The Approach

<p align="center">
  <img src="https://github.com/iamjosephmj/Rx-Clean/blob/master/repo-res/images/clean-graph.png" />
</p>

You might have seen this graph before. The circles represent different levels of your app. There are 
a couple of things that we should note:
* The inner circle is the most abstract, and the outer circle is the most concrete. This is called the Abstraction Principle. 
  This Abstraction Principle specifies that the inner circles should contain business logic, and outer circles should contain implementation details.
  (I know this is boring to read this stuff now... but trust me you will get more clarity moving forward).
* The Other principle of Clean Architecture is the Dependency Rule. This rule specifies, each circle can 
  depend only on the nearest inward circle(This is the backbone of the architecture).

The outer circle represents the concrete mechanisms that are specific to the platform such as networking 
and database access. Moving inward, each circle is more abstract and higher-level. The center circle is the 
most abstract and this is where we have our business logic, which doesn't rely on the platform or the framework
you’re using(in our case it is Android).

### Benefits

* Parts of the code get decoupled, and easier to reuse and test.
* There’s a method to the madness. When someone else works on your code, 
  they can learn the app’s architecture and will understand it better.
  
Before we dive in to the code, I would suggest you to read a bit about 
<a href= "https://en.wikipedia.org/wiki/SOLID">`SOLID`</a> principles.
