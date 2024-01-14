package model;

public enum Color {
    Blue{
        @Override
        public int direaction() {
            return 1;
        }

        @Override
        public boolean isBlue() {
            return true;
        }

        @Override
        public boolean isYellow() {
            return false;
        }

        @Override
        public int oppositeDirection() {
            return -1;
        }
    },    
    Yellow {
        @Override
        public int direaction() {
            return -1;
        }

        @Override
        public boolean isBlue() {
            return false;
        }

        @Override
        public boolean isYellow() {
            return true;
        }

        @Override
        public int oppositeDirection() {
            return 1;
        }
    };

    public abstract int direaction();
    public abstract int oppositeDirection();
    public abstract boolean isBlue();
    public abstract boolean isYellow();
}
