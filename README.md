# This is a work in progress.
Building out a Reddit themed Java Spring Boot application to save the Reddit we all love before it turns into a mainstream social network. We are in the early days (as you can see from the source,) but progressing steadily.

### Basic guidelines:
- Cheap. Everything must run at the lowest possible CPU/RAM use. We are designing everything to run in a 'serverless' arch.
- Everything FOSS. Java is a bit of a mess for this right now, but we will figure it out later.
- Don't do anything someone else has already done. We're using existing tech (RabbitMQ, Spring, Jaxon, etc.) instead of building everything from scratch.
- Java for everything critical, Python for everything else. Java is fast, stable, and good for large projects with many devs. Python can be used for 'glue' or other misc needs.
- We will build it and then we will figure out the finer rules of the platform. Josh has some ideas on how this is going to work, but we must build something before we can devolve into arguing about how 'x' type of conflict will be resolved or how 'x' type of sub will be moderated.

### Things we could use help with right now:
- Front-end GUI design (views).
- People with API testing experience.
- Cassandra engineers.
- Online champions for our cause.

### Things we have on the go:
- Building a working and fast Cassandra DB model in Spring Boot (about 75% prototyped).
- Building a working API interface to interact with the DB (about 25% prototyped).

We have received a pretty substantial hosting 'allowance' to launch the initial site. Details around funding will be solved after we have something to cost out.

Please contact jharkema@protonmail.com if you're interested in joining the project. We are not in a position to manage forks and pulls, please get in touch before you take something on; it's early days and we need to firm up a structure before anyone gets too crazy with it.