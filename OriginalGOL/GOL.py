import pygame
import random
import time
from pygame.locals import *

from pygame.locals import (
    K_ESCAPE,
    KEYDOWN,
)

def boardInit():
    liveCells = []
    board = []
    for i in range(0,BWIDTH):
        board.append([])
        for j in range(0,BHEIGHT):
            board[i].append(0)
        
    for i in range(0,SEED):
        randX = random.randint(1,len(board)-1) #boundary will always be dead cells (0, and 11)
        randY = random.randint(1,len(board[0])-1) #boundary will always be dead cells (0, and 11)
        if board[randX][randY] == 0:
            liveCells.append([randX,randY])
            board[randX][randY] = 1
    return board,liveCells 

def neighborhood(x,y,board):
    liveNeibhors=0
    #print("Checking Neighbors of ",x,'_',y)
    
    #Checking upper right
    if board[x+1][y+1] == 1:
        #print(x+1,"_",y+1)
        liveNeibhors += 1
    
    #Checking even right
    if board[x+1][y] == 1:
        #print(x+1,"_",y)
        liveNeibhors += 1
    
    #Checking bottom right 
    if board[x+1][y-1] == 1:
        #print(x+1,"_",y-1)
        liveNeibhors += 1

    #Checking even bottom
    if board[x][y-1] == 1:
        #print(x,"_",y-1)
        liveNeibhors += 1
    
    #Checking bottom left
    if board[x-1][y-1] == 1:
        #print(x-1,"_",y-1)
        liveNeibhors += 1
    
    #Checking left even
    if board[x-1][y] == 1:
        #print(x-1,"_",y)
        liveNeibhors += 1

    #Checking Upper left
    if board[x-1][y+1] == 1:
        #print(x-1,"_",y+1)
        liveNeibhors += 1

    #Checking even upper
    if board[x][y+1] == 1:
        #print(x,"_",y+1)
        liveNeibhors += 1
    return liveNeibhors

def printBoard(board,x=0,y=0,Zeros=' ',Ones='*'):
    print(x,"_",y,"=",board[x][y])
    for i in range(0, len(board)):
        print('|',end="")
        for j in range(0,len(board[i])):
            if board[i][j]==1:
                if x == i and y == j:
                    print("#",end="")
                else:
                    print(Ones,end="")
            else:
                print(Zeros,end="")
        print('|',end="")
        print()
    print("The board is ",len(board),'x',len(board[0]))

def updateBoard(board):
    neighbors=0
    for i in range(1, len(board)-1):
        for j in range(1,len(board[i])-1):
            neighbors =neighborhood(i,j,board)
            if(board[i][j] == 0  and neighbors == 3):
                board[i][j] = 1
            elif(board[i][j] == 1  and (neighbors == 3 or neighbors == 2)):
                board[i][j] = 1
            else:
                board[i][j] = 0


                
    


# Define Constants
SCREEN_WIDTH = 1000
SCREEN_HEIGHT = 1000
BWIDTH = int(input("Width: "))+2
BHEIGHT = int(input("Height: "))+2
SEED = int(input("Seed Population: "))

#initialize
board,liveCells = boardInit()
pygame.init()

# Set up the drawing window
screen = pygame.display.set_mode([SCREEN_WIDTH,SCREEN_HEIGHT])
screen.fill((255, 255, 255))
pygame.display.flip()

# Create a surface and pass in a tuple containing its length and width
surf = pygame.Surface((50, 50))

# Give the surface a color to separate it from the background
surf.fill((0, 0, 0))
rect = surf.get_rect()

screen.blit(surf, (SCREEN_WIDTH/2, SCREEN_HEIGHT/2))
pygame.display.flip()
# Run until the user asks to quit
running = True
while running:

    # Did the user click the window close button?
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    
    updateBoard(board)
    pixelHeight = int(SCREEN_WIDTH/(BWIDTH))
    pixelWidth = int(SCREEN_WIDTH/(BHEIGHT))
    for i in range(0, len(board)):
        for j in range(0,len(board[i])):
            if board[i][j]==1:
                surf = pygame.Surface((pixelHeight,pixelWidth))
                surf.fill((255,255,255))
                screen.blit(surf,(pixelHeight*i, pixelWidth*j))
            else:
                surf = pygame.Surface((pixelHeight,pixelWidth))
                surf.fill((0,0,0))
                screen.blit(surf, (pixelHeight*i, pixelWidth*j))
    pygame.display.flip()

# Done! Time to quit.
pygame.quit()

