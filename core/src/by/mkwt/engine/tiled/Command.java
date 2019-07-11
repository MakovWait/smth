package by.mkwt.tiled;

public enum Command {
    tremors {
        @Override
        public void run() {
        }
    },
    wake {
        @Override
        public void run() {
        }
    },
    exit {
        @Override
        public void run() {
        }
    },
    blackscreen {
        @Override
        public void run() {
        }
    };

    public abstract void run();
}
