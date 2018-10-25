Assumptions
=========================

1) The bot can only move through white spaces
2) The maze outer edges are composed just by blocked paths, so, the bot cannot run away
3) The output will be presented through System library
4) The bot only can move in one axis per movement (no diagonal movements allowed)
5) The bot will choose randomly which direction to take when more than one available
6) The bot can move backwards if it reaches an endpoint (backwards is the only possible path)

Improvements
============
**Note:** I didn't want to delay more the assessment, more time could be useful for:
* Improve tests coverage
* Create at least two movement strategies
* I usually write self documented code but I admit that when we have to work with arrays, the code looks messy with or without comments. So, some comments could help understanding the algorithms.
* Add movement symbols like ^,>,<,v to the path choose by the bot