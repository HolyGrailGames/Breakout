# Breakout
Tölvugrafík Breakout v1.0

# Controls
- 	"Left / Right" arrow keys to control paddle
-	"Space" to start game / Retry after game over
-	"F" to cheat, instantly clears a level and starts the next one
-   "P" to show the points on the circle used for collision calculations and draw where the pHits happen.
-   "O" clear the list of pHits being drawn (won't see anything happening unless you've pressed P before).

# Levels
-	Level 1 (8 rows 10 columns of blocks) 
- 	Level 2 (Leonardo TMNT)
- 	Level 3 (Red Pacman Ghost)

# Collisions
Our collisions are calculated using 8 points on the ball, one point for every 45 degrees on the ball. We check each point for a collision against every object in the game and choose the collision that will happen first. We calculate the reflection vector, move the ball to the point of collision (or very close to it) and check if there are any more collisions happening in the remainder of the frame time. All of this is done in our Collisions class.