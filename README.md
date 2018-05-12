# This is a work in progress.
Building out a Reddit themed Java Spring Boot application to save the Reddit we all love before it turns into a 
mainstream social network. We are in the early days (as you can see from the source,) but progressing steadily.

Follow our development progress on the public test server at [https://ureddit.org](https://ureddit.org). The server is
periodically updated with working builds. Feel free to use it, but the data gets wiped each time the db model is changed
(almost daily at this stage.)

# Help Wanted
We need java devs of all skill levels (from undergrad to retired engineer) to join the project. Spring Boot, REST,
thymeleaf, Python (for connectors,) cassandra, redis, UX, UI, security, JavaScript, etc., are all skills we can use. If 
you're looking for somewhere to take a crack at working on a big project, this is your chance.

Only collaborators (eh, comrades?) can contribute, but anyone who asks will be added. No proof of experience needed, 
and no shame in screwing something up--we all had to learn somewhere and a repo can always be rolled back.

email: [jharkema@protonmail.com](mailto:jharkema@protonmail.com) and introduce yourself. Look under "Issues" in the menu 
to see what's being worked on right now to see if our current tasks are something you're interested in. Feel free to 
make feature suggestions, but be prepared to implement them yourself. 

### Fundamental Principles:
1) Privacy first. Built in comment / post / account purge with auto-scheduler features. Once it's gone, it's gone for
good.
2) API first. The front-end website is ancillary to the REST controller. RESTful everything.
3) Anonymity guaranteed. No tracking allowed; the only exception to this is session tracking for user authentication on
the OAuth REST server and HTTP front end. 
4) No gimmicks. Basic features and a lightweight front-end are our guiding design principles. We're not building a 
social network, there are too many of those as it is. The purpose of this site is to retain the features and
functionality of Reddit c. 2017--the only 'new' additions are the increased privacy measures and the 'live' post page
websockets. 
5) User built, user funded, user focused. &micro;reddit is not a for profit venture.
6) Anonymous, but accountable. User's can post anonymously (from a registered account,) but anonymous user's can still
be banned from a sub. All user's must provide a verified email address for the sole purpose of preventing API abuse 
(i.e. the state of Reddit today.) Anonymity is important, but integrity of discourse is equally important. It will
require a lot of fine tuning to get this right.
7) Mod tools are king. Good mods make good subs, good subs make good content, good content is why we're building this
thing in the first place.

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

### Project management methodology:

Right now, as there are very few people working on the project, we're using Kanban (i.e. GitHub issues.) If you want to
take something on, email Josh and assign yourself to the issue. _Please make sure anything you commit actually builds, I
know this is strange to have to explicitly state, but I've had some issues in the past._ Testing will be done with JUnit
(of course) so make sure your unit tests are in the 'test' package and use JUnit 4.12--if you build with Maven the
included version of JUnit is valid.

### Note:

Right now the UI is bootstrap based, this is because I (Josh) am not a web designer and I am only comfortable with 
bootstrap (I always use it for WebMVC protos). I _strongly_ encourage someone to completely replace the front end
design. If you want this project, take it, I can't write CSS. It will probably be easiest if you just use the same CSS
tags as bootstrap does so you don't also have to rewrite all the Thymeleaf templates, but it's really your call. 
