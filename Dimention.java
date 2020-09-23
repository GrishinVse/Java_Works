package com.company;

class Dimention {

    static class Shape{
        public double volume;

        public Shape(double volume){
            this.volume = volume;
        }

        public double getVolume() {
            return this.volume;
        }
    }
    // Четырехугольная пирамида
    static class Pyramid extends Shape{
        private double height;
        private double side;

        public Pyramid(double height, double side, double volume) {
            super(volume);
            this.height = height;
            this.side = side;
        }

        @Override
        public double getVolume() {
            this.volume = (this.height * Math.pow(this.side, 2)/3);
            return this.volume;
        }
    }
    // Прямоугольный параллелепипед
    static class Piped extends Shape{
        private double len;
        private double width;
        private double height;


        public Piped(double len, double width, double height, double volume) {
            super(volume);
            this.len = len;
            this.width = width;
            this.height = height;
        }

        @Override
        public double getVolume() {
            this.volume = (this.len * this.width * this.height);
            return this.volume;
        }
    }

    // Фигуры с радиусом
    // Цилиндр
    static class Cylinder extends Shape{
        private double height;
        private double rad_osn; // радиус основания

        public Cylinder(double height, double rad_osn, double volume) {
            super(volume);
            this.height = height;
            this.rad_osn = rad_osn;
        }

        @Override
        public double getVolume() {
            this.volume = (Math.pow(this.rad_osn, 2) * Math.PI * this.height);
            return this.volume;
        }
    }
    // Шар
    static class Ball extends Shape{
        private double rad; // радиус основания

        public Ball(double rad, double volume) {
            super(volume);
            this.rad = rad;
        }

        @Override
        public double getVolume() {
            this.volume = (4 * Math.pow(this.rad, 3) * Math.PI/3);
            return this.volume;
        }
    }
    // Конус
    static class Cone extends Shape{
        private double height;
        private double rad_vp; // радиус вписанной окружности

        public Cone(double height, double rad_vp, double volume) {
            super(volume);
            this.height = height;
            this.rad_vp = rad_vp;
        }

        @Override
        public double getVolume() {
            this.volume = (Math.pow(this.rad_vp, 2) * Math.PI * this.height/3);
            return this.volume;
        }
    }

    static class Box extends Shape{
        public double volume;
        public double filled;

        public Box(double volume, double filled){
            super(volume);
            this.filled = filled;
        }

        public double getFilled() {
            return this.filled;
        }
        public void setFilled(double x) {
            this.filled = x;
        }

        public Boolean add(Box box, Shape obj){
            Boolean mode = true;
            System.out.println(box.getFilled());
            if ((box.getVolume() - box.getFilled()) > obj.getVolume()){
                mode = true;
                box.setFilled(box.getFilled() + obj.getVolume());
            } else {
                mode = false;
            }
            return mode;
        }
    }

    public static void main(String[] args) {
        Pyramid pum = new Pyramid(10, 4, 1);
        //System.out.println(pum.height);
        //System.out.println(pum.side);
        //System.out.println(pum.height * Math.pow(pum.side, 2)/3);
        System.out.println("Объем пирамиды = " + pum.getVolume());

        Piped parpiped = new Piped(23, 14, 8, 1);
        System.out.println("Объем прям. парал-да = " + parpiped.getVolume());

        Cylinder cyl = new Cylinder(12, 5,1);
        System.out.println("Объем цилиндра = " + cyl.getVolume());

        Ball ballon = new Ball(2, 1);
        System.out.println("Объем шара = " + ballon.getVolume());

        Cone conus = new Cone(15, 10, 1);
        System.out.println("Объем конуса = " + conus.getVolume());

        Box box = new Box(1000,0);
        System.out.println("Объем контейнера = " + box.getVolume() + " | " + box.filled + " /|\\ " + (box.getVolume() - box.filled));

        String[] main_out = new String[] {Boolean.toString(box.add(box, pum)), Boolean.toString(box.add(box, parpiped)),
                Boolean.toString(box.add(box, cyl)), Boolean.toString(box.add(box, ballon)), Boolean.toString(box.add(box, conus))};
        //System.out.println(Boolean.toString(box.add(pum)));
        //main_out = (box.add(pum), box.add(parpiped), box.add(cyl), box.add(ballon), box.add(conus));
        System.out.print("| ");
        for (int i = 0; i < main_out.length; i++) {
            System.out.print(main_out[i] + " | ");
        }

        //box.add(pum); box.add(parpiped); box.add(cyl); box.add(ballon); box.add(conus);
    }

}


