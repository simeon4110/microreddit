# This is a work in progress.
Building out a Reddit themed Java Spring Boot application to save the Reddit we all love before it turns into a 
mainstream social network. We are in the early days (as you can see from the source,) but progressing steadily.

# Help Wanted
We need java devs of all skill levels (from undergrad to retired engineer) to join the project. Spring Boot, REST,
thymeleaf, Python (for connectors,) cassandra, redis, UX, UI, security, etc., are all the skills you need. If you're
looking for somewhere to take a crack at working on a big project, this is you're chance.

Only collaborators (eh, comrades?) can contribute, but anyone who asks will be added. No proof of experience needed, 
and no shame in screwing something up.

email: jharkema@protonmail.com and introduce yourself. Look under "Issues" in the menu to see what's being worked on
right now to see if our current tasks are something you're interested in. Feel free to make feature suggestions, but
be prepared to implement them yourself. 

### Fundamental Principles
1) Privacy first. Built in comment / post / account purge with auto-scheduler features.
2) API first. The front-end website is ancillary to the REST controller. RESTful everything.
3) Anonymity guaranteed. No tracking allowed; the only exception to this is session tracking for user authentication on
the OAuth REST server and HTTP front end. 
4) No gimmicks. Basic features and a lightweight front-end are our guiding design principles. We're not building a 
social network, there are too many of those as it is. The purpose of this site is to retain the features and
functionality of Reddit c. 2017--the only 'new' additions are the increased privacy measures.
5) User built, user funded, user focused. &micro;reddit is not a for profit venture.
6) Anonymous, but accountable. User's can post anonymously (from a registered account,) but anonymous user's can still
be banned from a sub. All user's must provide a verified email address for the sole purpose of preventing API abuse 
(i.e. the state of Reddit today.) Anonymity is important, but integrity of discourse is equally important. It will
require a lot of fine tuning to get this right.

*The Golden Rule* - Everything is subject to change, if something doesn't work, we will change it.

### Basic guidelines:
- Cheap. Everything must run at the lowest possible CPU/RAM use. We are designing everything to run in a 'serverless' 
arch.
- Everything FOSS. Java is a bit of a mess for this right now, but we will figure it out later. (AFAIK right now none
of the project dependencies are not FOSS).
- Don't do anything someone else has already done. We're using existing tech (RabbitMQ, Spring, Jaxon, etc.) instead of 
building everything from scratch.
- Java for everything critical, Python for everything else. Java is fast, stable, and good for large projects with many 
devs. Python can be used for 'glue' or other misc needs.
- We will build it and then we will figure out the finer rules of the platform. Josh has some ideas on how this is going 
to work, but we must build something before we can devolve into arguing about how 'x' type of conflict will be resolved 
or how 'x' type of sub will be moderated.

### Things we could use help with right now:
- Front-end GUI design (views).
- People with API testing experience.
- Cassandra engineers.
- Online champions for our cause.

### Things we have on the go:
- Building a working and fast Cassandra DB model in Spring Boot (about 75% prototyped).
- Building a working API interface to interact with the DB (about 25% prototyped).

We have received a pretty substantial hosting 'allowance' to launch the initial site. Details around funding will be 
solved after we have something to cost out.
