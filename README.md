# Publisher-Subscriber-Model
I designed a program that follows the Publisher-Subscriber model and its characteristics. The Publisher-Subscriber model is centered around the core principles of object oriented programming. The main requirements of this project was to design a program where data is strongly encapsulated, there is minimal abstraction or overlap between classes and their functions, and there be intermediate classes such as an event bus that carries instructions to other classes. The program is designed to mimic a simplified version of how a user might subscribe/unsubscribe to podcasts and how they would be alerted when the podcast station posts new content. The main requirement is that there is no overlap between the publishers and the subscribers and that the event bus does most of the work while keeping the actual data in other classes organized and encapsulated.

#Example
**Program Input:**
```
Subscribe,Keenan,Space News
Subscribe,Jared,Politics
Publish,PBS,Space News,Space Time e.12
Publish,NASA,Space News,nasa public
Publish,CSPAN,Politics,White House Briefing
Subscribe,Ville,literature
Subscribe,Kirk,Rock Music
Publish,BBC,literature,The Secret Life of Books
Publish,CNN,Documentary,High Profits
Publish,VH1,Rock Music,Behind the Music
Unsubscribe,Keenan,Space News
Publish,BBC,Space News,Wonders of the Solar System
```

**Program Output**
```
keenan notified of space time e.12 from pbs
keenan notified of nasa public from nasa
jared notified of white house briefing from cspan
ville notified of the secret life of books from bbc
kirk notified of behind the music from vh1
```
The event bus in this instance is in use when notify is called. When notify is declared the event bus goes to the publisher class and collects the required information. Afterwards the event bus parses the subscriber list and prints all matches between subscribers who are subscribed to the specific topic stored on the event bus. The data is still encapsulated within their respective classes.
