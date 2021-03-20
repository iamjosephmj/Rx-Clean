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
    * [Dependencies](#Dependencies)
    * [Layers of Clean Architecture](#Layers-of-Clean-Architecture)
    * [Project Structure](#Project-Structure)
    * [The Data layer](#The-Data-layer)
        * [Creating DataSource](#Creating-DataSource)
    * [The Use Cases Layer](#The-Use-Cases-Layer)
    * [Framework Layer](#Framework-Layer)
    * [Presentation Layer](#Presentation-Layer)
    * [Sources Implementation](#Sources-Implementation)
    * [Dependency Injection](#Dependency-Injection)

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
you’ll learn how to use the Clean architecture pattern to build a <a href="https://jobs.github.com/api">`GitHubJobs`</a> app, a simple app that displays jobs that are available in github and display that on a list.

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

* Parts of the code get decoupled
* Easier to reuse.
* Easier to write unit-tests.
* There’s a method to the madness. When someone else works on your code, 
  they can learn the app’s architecture and will understand it better.
  
Before we dive in to the code, I would suggest you to read a bit about 
<a href= "https://en.wikipedia.org/wiki/SOLID">`SOLID`</a> principles.

### Dependencies

* <a href="https://dagger.dev/">`Dagger2`</a>
* <a href="https://github.com/sockeqwe/AdapterDelegates">`AdapterDelegates`</a>
* <a href="https://github.com/ReactiveX/RxKotlin">`RxKotlin`</a>
* <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">`ViewModels`</a>
* <a href="https://github.com/square/moshi">`Moshi`</a>
* <a href="https://github.com/airbnb/lottie-android">`lottie-android`</a>
* <a href="https://github.com/willowtreeapps/spruce-android">`spruce-android`</a>
* <a href="https://github.com/bumptech/glide">`Glide`</a>

### Layers of Clean Architecture

There are different theories about layers of the clean architecture, the architecture is in such a way that it gives an abstract idea on which we 
can adapt the number of layers to our needs. To keeps things much precise, we will use five layers:

* Presentation: A layer that interacts with the UI.
* Use cases: Sometimes called interactors. Defines actions the user can trigger.
* Domain: Contains the business logic of the app.
* Data: Abstract definition of all the data sources.
* Framework: Implements interaction with the Android SDK and provides concrete implementations 
  for the data layer.
  
### Project Structure

We had pointed out the different layers of the clean, but it is important to know how to implement it. The project 
is divided into two modules:

* The existing app module.
* A new core module that will hold all the code that doesn't depend on Android SDK.

<p align="center">
  <img src="https://github.com/iamjosephmj/Rx-Clean/blob/master/repo-res/images/moduleStructure.png" />
</p>

Follow these steps:

* Create a module named "core".
* Add core module to main app module as dependency.
* create packages named
  * data
  * domain
  * interactors
  under the core module.
    
Now you are all set to go :)
<br>
We will start from inner-most layers to the most concrete ones

### The Data layer

This layer provides abstract definitions for accessing data sources like a database or the internet. 
we’ll use Repository pattern in this layer. The main purpose of the repository pattern is to abstract
away the concrete implementation of data access. To achieve this, you’ll add one
interface and one class for each model:

* `DataSource interface`: The interface that the Framework layer must implement.
* `Repository class`: Provides methods for accessing the data that delegate to DataSource.

<p align="center">
  <img src="https://github.com/iamjosephmj/Rx-Clean/blob/master/repo-res/images/dataLayer.png" />
</p>

Using the repository pattern is a good example of the `Dependency Inversion` Principle because:

* A Data layer which is of a higher, more abstract level.
* Doesn't depend on a framework, lower-level layer.
* The repository is an abstraction of Data Access and it does not depend on details.
  It depends on abstraction.
  

#### Creating DataSource

* you can see the abstract implementation of datasource in <a href = "https://github.com/iamjosephmj/Rx-Clean/blob/master/core/src/main/java/io/iamjosephmj/core/data/datasource/GitHubJobsDataSource.kt">`GitHubJobsDataSource`</a>.
In a broader sense, this is the interface that the Framework layer must implement.

* you can see the abstract implementation of data repository in <a href = "https://github.com/iamjosephmj/Rx-Clean/blob/master/core/src/main/java/io/iamjosephmj/core/data/repo/GitHubJobsRepository.kt">`GitHubJobsRepository`</a>.. The main purpose of the repository pattern is to abstract away 
  the concrete implementation of data access. 

### The Use Cases Layer

This layer is responsible to collect user actions and in-turn pass it to the inner layers.
<br>
Our application has two key functionalities:

* Fetching the list from gitHub jobs url.
* displaying it in a list view.

From that, you can list the actions users should be able to perform:

* Fetching jobs(based on <a href ="https://github.com/iamjosephmj/Rx-Clean/blob/master/core/src/main/java/io/iamjosephmj/core/data/models/SearchRequest.kt">`domain and page number`</a>) 

Let's start creating use-case class. We are going to create the classes under <a href="https://github.com/iamjosephmj/Rx-Clean/tree/master/core/src/main/java/io/iamjosephmj/core/interactors">`interactors`</a> 

Each of the use cases class has only one function that invokes the use case. For convenience, 
you’re overloading the invoke operator. This enables you to simplify the function call on SearchForJobs 
instance to SearchForJobs() instead of searchForJobs.invoke().

### Framework Layer

By this, we come to the end of the three inner layers of the core module. We can now take a look at 
Framework layer. The Framework layer holds implementations of interfaces defined in the Data layer.
Our next job is to provide implementations of Data source interfaces from the Data layer.

* <a href="https://github.com/iamjosephmj/Rx-Clean/blob/master/presentation/src/main/java/io/iamjosephmj/presentation/framework/GitHubJobsDataSourceImpl.kt">`GitHubJobsDataSourceImpl.kt`</a>

Now we are all set, let's connect all the dots and display the data.

### Presentation Layer

Presentation layer contains the UI related code. This layer is in the same circle as the framework 
layer, so we can depend on its classes. 

### Architecture

For this projects, we ar going to use MVVM pattern as it is supported by <a href = "https://developer.android.com/topic/libraries/architecture">`Android Jetpack`</a>. 
I cannot specifically say that MVVM is the best suite for this project, as there are many other cool architectural 
patterns like MVP, MVI, VIPER, MvRx...etc.. and I don't really want to do a comparison of design patterns here... that's something for some other day. 
To stay in the context of our project lets stick on to MVVM.

This is how we are going to implement MVVM:

<p align="center">
  <img src="https://github.com/iamjosephmj/Rx-Clean/blob/master/repo-res/images/mvvm.png" />
</p>

MVVM pattern consists of three components:

* View: responsible for rendering the UI.
* Model: Contains business logic and data.
* ViewModel: Acts as a bridge between data and UI.

While we are speaking in terms of Clean Architecture, instead of relying on Models, you’ll communicate 
with Interactors from the Use Case layer.

<p align="center">
  <img src="https://github.com/iamjosephmj/Rx-Clean/blob/master/repo-res/images/clean-mvvm.png" />
</p>

Yep! This is the last piece of our puzzle :)

### Sources Implementation

Before moving on to implementing the presentation layer, we will need to provide the Data sources 
to the data layer. You should usually do this using dependency injection. It is the process of 
separating provider functions or factories for dependencies, and their usage. This makes your classes 
cleaner, as they don’t create dependencies in their constructors.

### Dependency Injection

I had implemented <a href="https://dagger.dev/dev-guide/">`dagger2`</a> for dependency management. You can go through 
these class to know how I categorized application and presentation layer dependencies

* Application Level <a href="https://github.com/iamjosephmj/Rx-Clean/blob/master/app/src/main/java/io/iamjosephmj/clean/di/component/ApplicationComponent.kt">`ApplicationComponent`</a> - <a href="https://github.com/iamjosephmj/Rx-Clean/blob/master/app/src/main/java/io/iamjosephmj/clean/di/module/ApplicationModule.kt">`Application Module`</a>
* Presentation Level <a href="https://github.com/iamjosephmj/Rx-Clean/blob/master/app/src/main/java/io/iamjosephmj/clean/di/component/ActivityComponent.kt">`ActivityComponent`</a> - <a href="https://github.com/iamjosephmj/Rx-Clean/blob/master/app/src/main/java/io/iamjosephmj/clean/di/module/ActivityModule.kt">`Activity Module`</a>
* Presentation Level <a href="https://github.com/iamjosephmj/Rx-Clean/blob/master/app/src/main/java/io/iamjosephmj/clean/di/component/ViewModelComponent.kt">`ViewModelComponent`</a> - <a href="https://github.com/iamjosephmj/Rx-Clean/blob/master/app/src/main/java/io/iamjosephmj/clean/di/module/ViewModelModule.kt">`ViewModel Module`</a>

note: feel free to change it to your own implementations.

