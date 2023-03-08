public class ghost {
    public class ghost extends Actor
    {
        private static final int DIRECTION_RIGHT=0;
        private static final int DIRECTION_LEFT=1;
        private static final int DIRECTION_UP=2;
        private static final int DIRECTION_DOWN=3;
        private static final int OFFSET=5;
        private int direction= DIRECTION_RIGHT;

        public void act()
        {
            moveGhost();
        }

        private void moveGhost(){
            wall wall = (wall)getOneIntersectingObject(wall.class);
            switch(direction){
                case DIRECTION_RIGHT:
                    if(wall==null){
                        setLocation(getX() + OFFSET, getY());
                    }else{
                        setLocation(getX() - 4, getY());
                        direction=DIRECTION_UP;
                    }
                    break;

                case DIRECTION_LEFT:
                    if(wall==null){
                        setLocation(getX() - OFFSET, getY());
                    }else{
                        setLocation(getX() + 4, getY());
                        direction=DIRECTION_DOWN;
                    }
                    break;

                case DIRECTION_UP:
                    if(wall==null){
                        setLocation(getX() , getY() - OFFSET);
                    }else{
                        setLocation(getX(), getY()+4);
                        direction=DIRECTION_LEFT;
                    }
                    break;

                case DIRECTION_DOWN:
                    if(wall==null){
                        setLocation(getX() , getY() + OFFSET);
                    }else{
                        setLocation(getX(), getY()-4);
                        direction=DIRECTION_RIGHT;
                    }
                    break;
            }
        }
    }

}
