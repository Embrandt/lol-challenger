# lol-challenger
JavaFX app to challenge your buddies from League of Legends to compete with you over a series of games
to see who can come up with the better stats it uses the API of the client so show your buddy list, send invites to your friends and determine who you are

## How it works
- Start the League of Legends client
- start the lol-challenger app
- if it is the first time starting the app, select the lockfile in the League of Legends client folder
![Choose Lockfile](/doc/img/chooseLockFile.jpg?raw=true)
- search your buddy list for that one friend, that you always wanted to compete with
![Search buddies](/doc/img/searchBuddies.jpg?raw=true)
- play games and see your proress updated automatically ![Update progress](/doc/img/progress.jpg?raw=true)

## This application is still unfinished as it was for the Riot API challenge 2018 and we needed to meet the deadline
Plans to improve
- implement a "tilt-saver" a feature to compete with yourself and get a warning, when you drop way below your average stats
- implement server for the calls to the RiotApi to keep the key protected
- finetune the interface and fix some bugs like missing stats
