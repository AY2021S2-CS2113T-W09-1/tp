# Lee Han Yong Andy's Project Portfolio Page

---

## Overview
Finux is a Command-Line Interface (CLI) application that allows you to record your expenses, savings and loans all
in one consolidated platform. It enhances the user experience by integrating of different features into one to remove
the hassle of constantly using different applications.

---

## Summary of Contributions

### Code Contribution

Contributions can be found on [RepoSense](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=leehanyongandy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=false&since=2021-03-05&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=LeeHanYongAndy&tabRepo=AY2021S2-CS2113T-W09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code).

---

### Contribution to Implementation

#### ParserHandler
During the discussion phase, Mark and I have decided that the ParserHandler logic will contain no form of validation.
The validation will be done when we are creating each command object itself. Following this approach, we reduce the
coupling arising from validation to only under the command creation period via the constructor. Considering the  future
development of Finux, I have decided to follow the pairwise logic of how a command line interface would work.
The pairwise logic ensure that, every option is followed by an argument with an empty string to indicate no
argument provided. This will facilitate the validation process. I have also decided to use a third-party
API, [Apache Commons Lang 3.11](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.11),
as it was easier to detect a collection of string containing the options.

#### ViewCommand
I have also implemented the ViewCommand where it would display each individual category amount.
This was implemented via the options available to the user which are `-e`, `-l`, and `-s`.
The initial thought was to only allow the view of each category but based on the feedback received, I decided to
enhance the feature to display all the amount of with each category in a single command, introducing the 4th option `-a`.

#### HelpCommand
Since our product is similar to that of a CLI, I have decided to implement the help feature to display similarly to
the man page of a CLI. The initial thought was to allow the target user to have a sense of CLI environment. However,
after receiving some feedbacks on having aa less detailed help feature, I decided to allow the mixture of both
user-friendly and CLI-based help feature. The more user-friendly approach will display the name, brief description, and
format of the command while the CLI-based will display the name, synopsis, and description of the option and argument.

---

### Contributions to UG
I have provided the team with a UG skeleton based on everyone User Guide from previous project. Apart from the skeleton,
I have also documented the section on view feature and help feature.

Other contributions to the UG are:
* Hyperlinking and standardizing the table of contents
* Formatting for section 2
* Frequently Asked Questions

---

### Contributions to the DG
I have documented the section on ParserHandler Component, ListCommand and ViewCommand Implementation. This includes 
the UML class, object and sequence diagrams.

Other contributions to the DG are:
* Appendix A consisting of Target User Profile and Value Proposition
