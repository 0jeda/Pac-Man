public class pacman {
    public class Pacman extends Actor
    {
        private static final int COUNT_DOWN_MOUTH=15;
        private static final int DIRECTION_RIGHT=0;
        private static final int DIRECTION_LEFT=1;
        private static final int DIRECTION_UP=2;
        private static final int DIRECTION_DOWN=3;
        private static final int OFFSET=2;
        private int previus_move;

        private int mouthDelay=COUNT_DOWN_MOUTH;
        public static int score=0;

        private String [][]images;
        private int imagesIndex;
        private int direction= DIRECTION_RIGHT;

        public Pacman(){
            images = new String[4][];

            score=0;
            images[DIRECTION_RIGHT]=new String[]{
                    "images/pacman-close.png",
                    "images/pacman-open.png"
            };

            images[DIRECTION_LEFT]=new String[]{
                    "images/pacman-close-left.png",
                    "images/pacman-open-left.png"
            };

            images[DIRECTION_UP]=new String[]{
                    "images/pacman-close-up.png",
                    "images/pacman-open-up.png"
            };

            images[DIRECTION_DOWN]=new String[]{
                    "images/pacman-close-down.png",
                    "images/pacman-open-down.png"
            };


            setImage(images[DIRECTION_RIGHT][0]);
        }

        public void act()
        {

            movePacman();
            handleKey();
            handleImages();
            countScore();
            verificationResult();
        }

        private void verificationResult(){
            ghost ghost=(ghost)getOneIntersectingObject(ghost.class);
            if(ghost!=null){
                getWorld().removeObjects(getWorld().getObjects(items.class));
                setLocation(300, 200);
                setImage(new GreenfootImage("You Lost! :(", 50, Color.YELLOW, Color.BLACK));
                Greenfoot.stop();
            }
            if(score==475){
                setLocation(300, 200);
                setImage(new GreenfootImage("You Win! :)", 50, Color.YELLOW, Color.BLACK));
                Greenfoot.stop();
            }
        }

        private void countScore(){
            World world = getWorld();
            world.showText("Score: "+score, world.getWidth()-50,10);

            items item=(items)getOneIntersectingObject(items.class);
            apple apple=(apple)getOneIntersectingObject(apple.class);
            smallItem sItem=(smallItem)getOneIntersectingObject(smallItem.class);
            bigItem bItem=(bigItem)getOneIntersectingObject(bigItem.class);
            cherry cherry=(cherry)getOneIntersectingObject(cherry.class);
            if(item!=null){
                getWorld().removeObject(item);

                if(item==sItem){
                    score= score+5;
                }else if(item==apple){
                    score= score+50;
                }else if(item==bItem){
                    score= score+15;
                }else if(item==cherry){
                    score= score+100;
                }


            }
        }

        private void blockMove(){
            switch(direction){
                case DIRECTION_RIGHT:
                    setLocation(getX() - OFFSET, getY());
                    break;

                case DIRECTION_LEFT:
                    setLocation(getX() + OFFSET, getY());
                    break;

                case DIRECTION_UP:
                    setLocation(getX() , getY() + OFFSET);
                    break;

                case DIRECTION_DOWN:
                    setLocation(getX() , getY() - OFFSET);
                    break;
            }
        }

        private void movePacman(){
            wall wall = (wall)getOneIntersectingObject(wall.class);
            switch(direction){
                case DIRECTION_RIGHT:
                    if(wall==null){
                        setLocation(getX() + OFFSET, getY());
                        previus_move=DIRECTION_RIGHT;
                    }
                    break;

                case DIRECTION_LEFT:
                    if(wall==null){
                        setLocation(getX() - OFFSET, getY());
                        previus_move=DIRECTION_LEFT;
                    }
                    break;

                case DIRECTION_UP:
                    if(wall==null){
                        setLocation(getX() , getY() - OFFSET);
                        previus_move=DIRECTION_UP;
                    }
                    break;

                case DIRECTION_DOWN:
                    if(wall==null){
                        setLocation(getX() , getY() + OFFSET);
                        previus_move=DIRECTION_DOWN;
                    }
                    break;
            }
        }

        private void handleKey(){
            wall wall = (wall)getOneIntersectingObject(wall.class);
            if(wall!=null ){
                blockMove();
            }

            if(Greenfoot.isKeyDown("left")||Greenfoot.isKeyDown("a")){
                direction=DIRECTION_LEFT;
                mouthDelay=1;
                setLocation(getX() - 1, getY());
            }else if(Greenfoot.isKeyDown("right")){
                direction=DIRECTION_RIGHT;
                mouthDelay=1;
                setLocation(getX() + 1, getY());
            }else if(Greenfoot.isKeyDown("up")){
                direction=DIRECTION_UP;
                mouthDelay=1;
                setLocation(getX() , getY() - 1);
            }else if(Greenfoot.isKeyDown("down")){
                direction=DIRECTION_DOWN;
                mouthDelay=1;
                setLocation(getX(), getY() + 1);
            }
        }

        private void handleImages(){
            mouthDelay--;
            if(mouthDelay==0){
                imagesIndex = (imagesIndex + 1) % images[direction].length;
                setImage(images[direction][imagesIndex]);
                mouthDelay=COUNT_DOWN_MOUTH;
            }
        }
    }
}
